package com.kevin.lottery.facade;

import cn.hutool.json.JSONUtil;
import com.kevin.common.Constance;
import com.kevin.domain.rule.model.req.DecisionMatterReq;
import com.kevin.domain.strategy.model.vo.DrawAwardVO;
import com.kevin.lottery.application.process.IActivityProcess;
import com.kevin.lottery.application.process.req.DrawProcessReq;
import com.kevin.lottery.application.process.resp.DrawProcessResp;
import com.kevin.lottery.application.process.resp.RuleQuantificationCrowdResult;
import com.kevin.lottery.assembler.AwardMapping;
import com.kevin.lottery.rpc.ILotteryActivityBooth;
import com.kevin.lottery.rpc.dto.AwardDto;
import com.kevin.lottery.rpc.req.DrawReq;
import com.kevin.lottery.rpc.req.QuantificationDrawReq;
import com.kevin.lottery.rpc.resp.DrawResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wang
 * @create 2023-11-09-13:17
 */
@Service
public class LotteryActivityBooth implements ILotteryActivityBooth {

    private final Logger logger = LoggerFactory.getLogger(LotteryActivityBooth.class);

    @Resource
    private IActivityProcess activityProcess;

    @Resource
    private AwardMapping awardMapping;
    @Override
    public DrawResp doDraw(DrawReq req) {
        try {
            logger.info("抽奖开始执行,抽奖用户：uid:{},抽奖活动编号:{}",req.getuId(),req.getActivityId());
            // 执行抽奖策略
            DrawProcessResp drawProcessResp = activityProcess.doDrawProcess(new DrawProcessReq(req.getuId(), req.getActivityId()));

            if(Constance.ResponseCode.SUCCESSFUL.getCode() != drawProcessResp.getCode()){
                logger.error("uid:{}，该用户执行抽奖策略 activityId :{}出错:",req.getuId(),req.getActivityId());
                return new DrawResp(Constance.ResponseCode.LOSING_AWARD.getCode(),Constance.ResponseCode.LOSING_AWARD.getDesc());
            }
            // 转化数据
            DrawAwardVO drawAwardVO = drawProcessResp.getDrawAwardVO();
            AwardDto awardDto = awardMapping.sourceToTarget(drawAwardVO);
            awardDto.setActivityId(req.getActivityId());

            // 封装数据返回结果
            DrawResp drawResp = new DrawResp(Constance.ResponseCode.SUCCESSFUL.getCode(), Constance.ResponseCode.SUCCESSFUL.getDesc(), awardDto);
            logger.info("抽奖完成,uid:{},抽奖活动编号:{},抽奖结果:{}",req.getuId(),req.getActivityId(), JSONUtil.toJsonStr(drawResp));
            return drawResp;
        } catch (Exception e) {
            logger.error("抽奖失败，uid:{},activityId:{}",req.getuId(),req.getActivityId());
            return new DrawResp(Constance.ResponseCode.LOSING_AWARD.getCode(),Constance.ResponseCode.LOSING_AWARD.getDesc());
        }
    }

    @Override
    public DrawResp doQuantificationDraw(QuantificationDrawReq req) {
        if(req == null){
            logger.error("请求参数为空");
            return new DrawResp(Constance.ResponseCode.PARAMERROR.getCode(),Constance.ResponseCode.PARAMERROR.getDesc());
        }
        try {
            logger.info("抽奖之前的人员量化");
            // 执行抽奖量化策略
            RuleQuantificationCrowdResult ruleQuantificationCrowdResult = activityProcess.doRuleQuantificationCrowd(new DecisionMatterReq(req.getTreeId(),req.getuId(), req.getValMap()));

            //执行抽奖策略
            logger.info("开始抽奖了，用户id ：{},抽奖id:{}",req.getuId(),ruleQuantificationCrowdResult.getActivityId());
            DrawProcessResp drawProcessResp = activityProcess.doDrawProcess(new DrawProcessReq(req.getuId(), ruleQuantificationCrowdResult.getActivityId()));

            // 封装对象
            AwardDto awardDto = awardMapping.sourceToTarget(drawProcessResp.getDrawAwardVO());
            awardDto.setActivityId(ruleQuantificationCrowdResult.getActivityId());

            // 封装结果
            DrawResp drawResp = new DrawResp(Constance.ResponseCode.SUCCESSFUL.getCode(), Constance.ResponseCode.SUCCESSFUL.getDesc(), awardDto);
            logger.info("抽奖结果:{}",JSONUtil.toJsonStr(drawResp));
            return drawResp;
        } catch (Exception e) {
            logger.error("人员量化、抽奖失败，uid:{},activityId:{}",req.getuId(),req.getTreeId());
            return new DrawResp(Constance.ResponseCode.LOSING_AWARD.getCode(),Constance.ResponseCode.LOSING_AWARD.getDesc());
        }
    }
}
