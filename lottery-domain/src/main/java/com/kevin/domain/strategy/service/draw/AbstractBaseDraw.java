package com.kevin.domain.strategy.service.draw;

import com.kevin.common.Constance;
import com.kevin.domain.strategy.model.aggregates.StrategyRich;
import com.kevin.domain.strategy.model.req.DrawReq;
import com.kevin.domain.strategy.model.resp.DrawResp;
import com.kevin.domain.strategy.model.vo.*;
import com.kevin.domain.strategy.service.algorithm.IDrawAlgorithm;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wang
 * @create 2023-04-18:36
 */
public abstract class AbstractBaseDraw extends DrawStrategySupport implements IDrawExec{

    private final Logger logger = LoggerFactory.getLogger(AbstractBaseDraw.class);


    public void checkAndInitRateData(Long stragetryId, Integer strategryMode, List<StrategyDetailBriefVo> strategyDetailList){
        // 如果是单项抽奖概率就不需要加载到缓存中，他的数据库里面的数据就是他的概率
//        if(!Constance.StrategyMode.SINGLE.getCode().equals(strategryMode)){
//            return;
//        }

        IDrawAlgorithm drawAlgorithm = drawAlgorithmMap.get(strategryMode);
        // 已经初始化过的数据不需要在进行初始化操作
        if (drawAlgorithm.isExistRateTuple(stragetryId)) {
            return;
        }
        // 对中奖的信息进行提取，然后进行后面的抽奖概率初始化
        List<AwardRateInfo> awardRateInfos = new ArrayList<>();
        for(StrategyDetailBriefVo strategyDetail:strategyDetailList){
            awardRateInfos.add(new AwardRateInfo(strategyDetail.getAwardId(),strategyDetail.getAwardRate()));
        }
        // 进行对应的初始化操作
        drawAlgorithm.initRateTuple(stragetryId,awardRateInfos);
    }

    @Override
    public DrawResp doDrawExec(DrawReq drawreq) {
        StrategyRich strategyRich = super.queryStrategyByStrategyId(drawreq.getStrategyId());
        if (strategyRich == null) {
            return new DrawResp(drawreq.getUserId(),drawreq.getStrategyId(), Constance.DrawState.FAIL.getCode());
        }
        // 1.获取进行策略
        StrategyBriefVo strategy = strategyRich.getStrategy();
        IDrawAlgorithm drawAlgorithm = drawAlgorithmMap.get(strategy.getStrategyMode());
        // 2.检验抽奖策略是否已经加载到内存
        checkAndInitRateData(strategy.getStrategyId(),strategy.getStrategyMode(),strategyRich.getStrategyDetails());
        // 3.获取排除的抽奖列表
        List<String> exculdStrategyDetails =this.queryExeculdAwardIds(drawreq.getStrategyId());
        // 4.执行抽奖算法
        String awardId = this.drawAlgorithm(drawreq.getStrategyId(),drawAlgorithm,exculdStrategyDetails);
        // 5.包装抽奖结果
        return buildDrawResult(drawreq.getUserId(),drawreq.getStrategyId(),awardId,strategy);
    }

    private  DrawResp buildDrawResult(String userId, Long strategyId, String awardId,StrategyBriefVo strategyBriefVo){
        if(StringUtils.isBlank(awardId)){
            logger.info("执行抽奖策略完成，【未中奖】，用户id:{},策略id:{}",userId,strategyId);
            return new DrawResp(userId,strategyId, Constance.DrawState.FAIL.getCode());
        }
        AwardBriefVo award = super.queryAward(awardId);
        logger.info("执行策略抽奖完成【已中奖】，用户：{} 策略ID：{} 奖品ID：{} 奖品名称：{}", userId, strategyId, awardId, award.getAwardName());
        DrawAwardVO drawAwardVO = new DrawAwardVO(awardId,award.getAwardName(),award.getAwardDesc(),award.getAwardType(),strategyBriefVo.getStrategyMode(),strategyBriefVo.getGrantType(),strategyBriefVo.getGrantDate());
        return new DrawResp(userId, strategyId, Constance.DrawState.SUCCESS.getCode(), drawAwardVO);
    }

    protected abstract String drawAlgorithm(Long strategyId, IDrawAlgorithm drawAlgorithm, List<String> exculdStrategyDetails);

    protected abstract List<String> queryExeculdAwardIds(Long strategyId);
}
