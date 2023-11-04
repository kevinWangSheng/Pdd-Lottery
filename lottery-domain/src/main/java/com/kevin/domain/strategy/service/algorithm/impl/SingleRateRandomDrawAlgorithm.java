package com.kevin.domain.strategy.service.algorithm.impl;

import com.kevin.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.List;

/**单项随机概率抽奖，抽到一个已经排掉的奖品则未中奖
 * @author wang
 * @create 2023-2023-04-17:31
 */
@Component("singleRateRandomDrawAlgorithm")
public class SingleRateRandomDrawAlgorithm extends BaseAlgorithm {
    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {
        String[] tuple = super.rateTupleMap.get(strategyId);

        assert tuple != null;

        int randomVal = new SecureRandom().nextInt(100) + 1;

        int idx = super.hashIdx(randomVal);
        String awardId = tuple[idx];
        if(excludeAwardIds.contains(awardId)){
            return "未中奖";
        }
        return awardId;
    }
}
