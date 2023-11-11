package com.kevin.lottery.application.process.impl;

import com.kevin.common.Constance;
import com.kevin.domain.activity.model.req.PartakeReq;
import com.kevin.domain.activity.model.resp.PartakeResult;
import com.kevin.domain.activity.model.vo.ActivityPartakeRecordVO;
import com.kevin.domain.activity.model.vo.DrawOrderVO;
import com.kevin.domain.activity.model.vo.InvoiceVO;
import com.kevin.domain.activity.service.partake.IActivityPartake;
import com.kevin.domain.rule.model.req.DecisionMatterReq;
import com.kevin.domain.rule.model.rsep.EngineResult;
import com.kevin.domain.rule.repository.IRuleRespository;
import com.kevin.domain.rule.service.engine.EngineFilter;
import com.kevin.domain.strategy.model.req.DrawReq;
import com.kevin.domain.strategy.model.resp.DrawResp;
import com.kevin.domain.strategy.model.vo.DrawAwardVO;
import com.kevin.domain.strategy.service.draw.IDrawExec;
import com.kevin.domain.support.ids.IIDGenerate;
import com.kevin.lottery.application.mq.producer.KafkaProducer;
import com.kevin.lottery.application.process.IActivityProcess;
import com.kevin.lottery.application.process.req.DrawProcessReq;
import com.kevin.lottery.application.process.resp.DrawProcessResp;
import com.kevin.lottery.application.process.resp.RuleQuantificationCrowdResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @author wang
 * @create 2023-11-07-18:51
 */
@Service
public class ActivityProcessImpl implements IActivityProcess {

    private final Logger logger = LoggerFactory.getLogger(ActivityProcessImpl.class);

    @Resource
    private IActivityPartake activityPartake;

    @Resource
    private IDrawExec drawExec;

    @Resource
    private Map<Constance.Ids, IIDGenerate> idlGeneratorMap;

    @Resource
    private IRuleRespository ruleRespository;

    @Resource
    private KafkaProducer kafkaProducer;

    @Resource
    private EngineFilter engineFilter;
    @Override
    public DrawProcessResp doDrawProcess(DrawProcessReq req) {
        if(req == null){
            return new DrawProcessResp(Constance.ResponseCode.PARAMERROR);
        }
        // 1.领取任务
        PartakeResult partakeResult = activityPartake.doPartake(new PartakeReq(req.getUId(), req.getActivityId(),new Date()));
        if(Constance.ResponseCode.SUCCESSFUL.getCode() != partakeResult.getCode()){
            return new DrawProcessResp(partakeResult.getCode(),partakeResult.getInfo());
        }

        // 2.使用mq异步更新活动的领取数

        ActivityPartakeRecordVO activityPartakeRecordVO = buildActivityPartakeRecordVO(req, partakeResult.getStockCount(), partakeResult.getStockSurplusCount());
        // 使用消息中间件异步去更新对应的库存状态
        kafkaProducer.sendLotteryActivityPartakeRecord(activityPartakeRecordVO);

        // 3.执行抽奖
        DrawResp drawResp = drawExec.doDrawExec(new DrawReq(req.getUId(), req.getActivityId(),String.valueOf(partakeResult.getTakeId())));
        if(Constance.DrawState.FAIL.getCode().equals(drawResp.getDrawState())){
            return new DrawProcessResp(Constance.ResponseCode.LOSING_AWARD.getCode(),Constance.ResponseCode.LOSING_AWARD.getDesc());
        }

        // 4.结果落库
        DrawOrderVO drawOrderVO = buildDrawOrderVO(req, partakeResult.getStrategyId(), partakeResult.getTakeId(), drawResp.getDrawAwardVO());
        activityPartake.recordDrawOrder(drawOrderVO);

        // 5.发送MQ,触发抽奖流程
        InvoiceVO invoiceVO = buildInvoiceVO(drawOrderVO);
        ListenableFuture<SendResult<String, Object>> future = kafkaProducer.sendLotteryInvoice(invoiceVO);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            // 5.1 MQ 消息发送完成，更新数据库表 user_strategy_export.mq_state = 1
            @Override
            public void onFailure(Throwable ex) {
                activityPartake.updateInvoiceMqState(req.getUId(),invoiceVO.getOrderId(),Constance.MQState.FAIL.getCode());
                logger.error("消息发送失败了：uid:{},orderId:{}",req.getUId(),invoiceVO.getOrderId());
            }
            // 5.2 MQ 消息发送失败，更新数据库表 user_strategy_export.mq_state = 2 【等待定时任务扫码补偿MQ消息】
            @Override
            public void onSuccess(SendResult<String, Object> result) {
                activityPartake.updateInvoiceMqState(req.getUId(),invoiceVO.getOrderId(),Constance.MQState.COMPLETE.getCode());
                logger.info("消息发送成功了：uid:{},orderId:{}",req.getUId(),invoiceVO.getOrderId());
            }
        });
        //返回结果
        return new DrawProcessResp(Constance.ResponseCode.SUCCESSFUL.getCode(), Constance.ResponseCode.SUCCESSFUL.getDesc(),drawResp.getDrawAwardVO());
    }

    private ActivityPartakeRecordVO buildActivityPartakeRecordVO(DrawProcessReq req, Integer stockCount, Integer stockSurplusCount) {
        ActivityPartakeRecordVO activityPartakeRecordVO = new ActivityPartakeRecordVO();
        activityPartakeRecordVO.setActivityId(req.getActivityId());
        activityPartakeRecordVO.setuId(req.getUId());
        activityPartakeRecordVO.setStockCount(stockCount);
        activityPartakeRecordVO.setStockSurplusCount(stockSurplusCount);
        return activityPartakeRecordVO;
    }

    @Override
    public RuleQuantificationCrowdResult doRuleQuantificationCrowd(DecisionMatterReq req) {
        if (req == null) {
            return new RuleQuantificationCrowdResult(Constance.ResponseCode.PARAMERROR.getCode(), Constance.ResponseCode.PARAMERROR.getDesc());
        }
        // 执行量化策略
        EngineResult engineResult = engineFilter.process(req);
        if (!engineResult.isSuccess()) {
            return new RuleQuantificationCrowdResult(Constance.ResponseCode.RULE_ERR.getCode(),Constance.ResponseCode.RULE_ERR.getDesc());
        }

        // 返回结果
        return new RuleQuantificationCrowdResult(Constance.ResponseCode.SUCCESSFUL.getCode(), Constance.ResponseCode.SUCCESSFUL.getDesc(),Long.parseLong(engineResult.getNodeValue()));
    }

    private DrawOrderVO buildDrawOrderVO(DrawProcessReq req, Long strategyId, Long takeId, DrawAwardVO drawAwardVO) {
        Long orderId = idlGeneratorMap.get(Constance.Ids.SnowFlake).nextId();
        DrawOrderVO drawOrderVO = new DrawOrderVO();
        drawOrderVO.setActivityId(req.getActivityId());
        drawOrderVO.setOrderId(orderId);
        drawOrderVO.setAwardType(drawAwardVO.getAwardType());
        drawOrderVO.setAwardId(drawAwardVO.getAwardId());
        drawOrderVO.setAwardName(drawAwardVO.getAwardName());
        drawOrderVO.setAwardContent(drawAwardVO.getAwardContent());
        drawOrderVO.setGrantDate(drawAwardVO.getGrantDate());
        drawOrderVO.setGrantType(drawAwardVO.getGrantType());
        drawOrderVO.setStrategyId(strategyId);
        drawOrderVO.setUId(req.getUId());
        drawOrderVO.setGrantState(Constance.GrantState.INIT.getCode());
        drawOrderVO.setStrategyMode(drawAwardVO.getStrategyMode());
        drawOrderVO.setTakeId(takeId);

        return drawOrderVO;
    }

    private InvoiceVO buildInvoiceVO(DrawOrderVO orderVO) {
        InvoiceVO invoiceVO = new InvoiceVO();
        invoiceVO.setAwardContent(orderVO.getAwardContent());
        invoiceVO.setAwardId(orderVO.getAwardId());
        invoiceVO.setuId(orderVO.getUId());
        invoiceVO.setAwardName(orderVO.getAwardName());
        invoiceVO.setAwardType(orderVO.getAwardType());
        invoiceVO.setOrderId(orderVO.getOrderId());
        invoiceVO.setShippingAddress(null);
        invoiceVO.setExtInfo(null);

        return invoiceVO;
    }
}
