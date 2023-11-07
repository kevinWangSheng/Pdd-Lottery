package com.kevin.lottery.application.process.impl;

import com.kevin.common.Constance;
import com.kevin.domain.activity.model.req.PartakeReq;
import com.kevin.domain.activity.model.resp.PartakeResult;
import com.kevin.domain.activity.model.vo.DrawOrderVO;
import com.kevin.domain.activity.service.partake.IActivityPartake;
import com.kevin.domain.strategy.model.req.DrawReq;
import com.kevin.domain.strategy.model.resp.DrawResp;
import com.kevin.domain.strategy.model.vo.DrawAwardInfo;
import com.kevin.domain.strategy.service.draw.IDrawExec;
import com.kevin.domain.support.ids.IIDGenerate;
import com.kevin.lottery.application.process.IActivityProcess;
import com.kevin.lottery.application.process.req.DrawProcessReq;
import com.kevin.lottery.application.process.resp.DrawProcessResp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @author wang
 * @create 2023-11-07-18:51
 */
@Service
public class ActivityProcessImpl implements IActivityProcess {

    @Resource
    private IActivityPartake activityPartake;

    @Resource
    private IDrawExec drawExec;

    @Resource
    private Map<Constance.Ids, IIDGenerate> idlGeneratorMap;
    @Override
    public DrawProcessResp doDrawProcess(DrawProcessReq req) {
        if(req == null){
            return new DrawProcessResp(Constance.ResponseCode.PARAMERROR);
        }
        // 领取任务
        PartakeResult partakeResult = activityPartake.doPartake(new PartakeReq(req.getUId(), req.getActivityId(),new Date()));
        if(Constance.ResponseCode.SUCCESSFUL.getCode() != partakeResult.getCode()){
            return new DrawProcessResp(partakeResult.getCode(),partakeResult.getInfo());
        }

        // 执行抽奖
        DrawResp drawResp = drawExec.doDrawExec(new DrawReq(req.getUId(), req.getActivityId(),String.valueOf(partakeResult.getTakeId())));
        if(Constance.DrawState.FAIL.getCode().equals(drawResp.getDrawState())){
            return new DrawProcessResp(Constance.ResponseCode.LOSING_AWARD.getCode(),Constance.ResponseCode.LOSING_AWARD.getDesc());
        }

        // 结果落库
        activityPartake.recordDrawOrder(buildDrawOrderVO(req,partakeResult.getStrategyId(),partakeResult.getTakeId(),drawResp.getDrawAwardInfo()));

        //发送MQ,触发抽奖流程

        //返回结果
        return new DrawProcessResp(Constance.ResponseCode.SUCCESSFUL.getCode(), Constance.ResponseCode.SUCCESSFUL.getDesc(),drawResp.getDrawAwardInfo());
    }

    private DrawOrderVO buildDrawOrderVO(DrawProcessReq req, Long strategyId, Long takeId, DrawAwardInfo drawAwardInfo) {
        Long orderId = idlGeneratorMap.get(Constance.Ids.SnowFlake).nextId();
        DrawOrderVO drawOrderVO = new DrawOrderVO();
        drawOrderVO.setActivityId(req.getActivityId());
        drawOrderVO.setOrderId(orderId);
        drawOrderVO.setAwardType(drawAwardInfo.getAwardType());
        drawOrderVO.setAwardId(drawAwardInfo.getAwardId());
        drawOrderVO.setAwardName(drawAwardInfo.getAwardName());
        drawOrderVO.setAwardContent(drawAwardInfo.getAwardContent());
        drawOrderVO.setGrantDate(drawAwardInfo.getGrantDate());
        drawOrderVO.setGrantType(drawAwardInfo.getGrantType());
        drawOrderVO.setStrategyId(strategyId);
        drawOrderVO.setUId(req.getUId());
        drawOrderVO.setGrantState(Constance.GrantState.INIT.getCode());
        drawOrderVO.setStrategyMode(drawAwardInfo.getStrategyMode());
        drawOrderVO.setTakeId(takeId);

        return drawOrderVO;
    }
}
