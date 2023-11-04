package com.kevin.domain.strategy.service.draw.impl;

import com.kevin.domain.strategy.model.aggregates.StrategyRich;
import com.kevin.domain.strategy.model.req.DrawReq;
import com.kevin.domain.strategy.model.resp.DrawResp;
import com.kevin.domain.strategy.repository.StrategyRepository;
import com.kevin.domain.strategy.service.algorithm.IDrawAlgorithm;
import com.kevin.domain.strategy.service.draw.BaseDraw;
import com.kevin.domain.strategy.service.draw.IDrawExec;
import com.kevin.lottery.infrastructure.po.Award;
import com.kevin.lottery.infrastructure.po.Strategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author wang
 * @create 2023-2023-04-18:47
 */
@Service("drawExec")
public class DrawExecImpl extends BaseDraw implements IDrawExec {
    private Logger logger = LoggerFactory.getLogger(DrawExecImpl.class);
    @Resource
    private StrategyRepository strategyRepository;
    @Override
    public DrawResp doDrawExec(DrawReq drawreq) {
        if(drawreq == null){
            throw new RuntimeException("请求参数不能为空");
        }
        logger.info("执行抽奖策略开始，strategyId:{}",drawreq.getStrategyId());
        //
        StrategyRich strategyRich = strategyRepository.queryStrategyRich(drawreq.getStrategyId());
        Strategy strategy = strategyRich.getStrategy();
        checkAndInitRateData(strategy.getId(),strategy.getStrategyMode(),strategyRich.getStrategyDetails());
        // 根据抽奖策略进行抽奖
        IDrawAlgorithm drawAlgorithm = drawAlgorithmMap.get(strategy.getStrategyMode());
        // 进行随机抽奖
        String awardId = drawAlgorithm.randomDraw(strategy.getStrategyId(), new ArrayList<>());
        // 根据抽取到的奖品id查询奖品信息
        Award award = strategyRepository.queryAward(awardId);
        logger.info("执行抽奖策略结束，中奖用户:{},奖品id:{},奖品名称:{}",drawreq.getStrategyId(),awardId,award.getAwardName());
        // 返回获取的奖品信息
        return new DrawResp(drawreq.getUserId(),strategy.getStrategyId(),award.getAwardName(),awardId);
    }
}
