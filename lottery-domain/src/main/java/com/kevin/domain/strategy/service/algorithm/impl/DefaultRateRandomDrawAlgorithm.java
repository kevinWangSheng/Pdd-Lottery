package com.kevin.domain.strategy.service.algorithm.impl;

import com.kevin.domain.strategy.model.vo.AwardRateInfo;
import com.kevin.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**用随机概率的方式获取对应的概率的奖品id。
 * 同时排除掉已经没有的奖品，然后重新计算剩余的概率，在使用剩余的概率进行重新概率，然后遍历概率加和，发现随机的概率小于就直接返回对应的奖品id即可
 * @author wang
 * @create 2023-2023-04-17:30
 */
@Component("defaultRateRandomDrawAlgorithm")
public class DefaultRateRandomDrawAlgorithm extends BaseAlgorithm {
    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {
        List<AwardRateInfo> differenceAwardRateList = new ArrayList<>();
        List<AwardRateInfo> awardRateInfos = awardRateInfoMap.get(strategyId);
        BigDecimal differenceDenominator = BigDecimal.ZERO;
        // 排除掉已经没有的奖品
        for(AwardRateInfo awardRateInfo:awardRateInfos){
            if(excludeAwardIds.contains(awardRateInfo.getAwardId())) continue;
            differenceAwardRateList.add(awardRateInfo);
            differenceDenominator = differenceDenominator.add(awardRateInfo.getAwardRate());
        }

        if(differenceAwardRateList.size()==0) return "";
        if(differenceAwardRateList.size() ==1) return differenceAwardRateList.get(0).getAwardId();
        
        SecureRandom secureRandom = new SecureRandom();
        int randomVal = secureRandom.nextInt(100) + 1;

        int courseVal = 0;
        String awardId = "";
        for(AwardRateInfo awardRateInfo:differenceAwardRateList){
            int rateVal = awardRateInfo.getAwardRate().divide(differenceDenominator, 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue();
            if(randomVal<=(courseVal+rateVal)){
                awardId = awardRateInfo.getAwardId();
                break;
            }
            courseVal += rateVal;
        }
        return awardId;
    }
}
