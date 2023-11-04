package com.kevin.domain.strategy.service.algorithm;

import com.kevin.domain.strategy.model.vo.AwardRateInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wang
 * @create 2023-2023-04-17:12
 */
public abstract class BaseAlgorithm implements IDrawAlgorithm{

    private final int HASH_INCREMENT = 0x61c88647;

    private final int RATE_TUPLE_LENGTH = 128;

    //策略id和对应的斐波那锲散列值，散列值对应的下标是他的奖品信息id
    protected final Map<Long,String[]> rateTupleMap = new ConcurrentHashMap<>();

    protected final Map<Long, List<AwardRateInfo>> awardRateInfoMap = new ConcurrentHashMap<>();

    public void initRateTuple(Long strategyId,List<AwardRateInfo> awardRateInfoList){
        awardRateInfoMap.put(strategyId,awardRateInfoList);
        // 对策略的散列概率进行重新计算，如果不存在的话就把他重新加入进去
        String[] tupleRate = rateTupleMap.computeIfAbsent(strategyId,k->new String[RATE_TUPLE_LENGTH]);
        int cursorVal = 0;

        for(AwardRateInfo rateInfo:awardRateInfoList){
            //获取对应的概率
            int rateVal = rateInfo.getAwardRate().multiply(new BigDecimal(100)).intValue();
            for(int i = cursorVal;i<(cursorVal+rateVal);i++){
                tupleRate[hashIdx(i)] = rateInfo.getAwardId();
            }
            cursorVal += rateVal;
        }
    }

    @Override
    public boolean isExistRateTuple(Long strategyId) {
        return rateTupleMap.containsKey(strategyId);
    }



    // 返回使用散列值计算以后的哈希槽
    protected int hashIdx(int val){
        return (val * HASH_INCREMENT+HASH_INCREMENT) & (RATE_TUPLE_LENGTH - 1);
    }
}
