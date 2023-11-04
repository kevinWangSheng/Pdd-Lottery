package com.kevin.domain.strategy.service.draw;

import com.kevin.domain.strategy.model.enums.DrawRateRandomDrawAlgorithmEnum;
import com.kevin.domain.strategy.model.vo.AwardRateInfo;
import com.kevin.domain.strategy.service.algorithm.IDrawAlgorithm;
import com.kevin.lottery.infrastructure.po.StrategyDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wang
 * @create 2023-2023-04-18:36
 */
public class BaseDraw extends DrawConfig{

    public void checkAndInitRateData(Long stragetryId, Integer strategryMode, List<StrategyDetail> strategyDetailList){
        if(DrawRateRandomDrawAlgorithmEnum.DefaultRateRandomDrawAlgorithm.getCode() != strategryMode) return;

        IDrawAlgorithm drawAlgorithm = drawAlgorithmMap.get(strategryMode);
        if (drawAlgorithm.isExistRateTuple(stragetryId)) return ;
        // 对中奖的信息进行提取，然后进行后面的抽奖概率初始化
        List<AwardRateInfo> awardRateInfos = new ArrayList<>();
        for(StrategyDetail strategyDetail:strategyDetailList){
            awardRateInfos.add(new AwardRateInfo(strategyDetail.getAwardId(),strategyDetail.getAwardRate()));
        }
        // 进行对应的初始化操作
        drawAlgorithm.initRateTuple(stragetryId,awardRateInfos);
    }
}
