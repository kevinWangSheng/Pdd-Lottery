package com.kevin.domain.strategy.service.draw;

import com.kevin.domain.strategy.model.enums.DrawRateRandomDrawAlgorithmEnum;
import com.kevin.domain.strategy.service.algorithm.IDrawAlgorithm;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wang
 * @create 2023-2023-04-18:28
 */

public class DrawConfig {
    @Resource
    private IDrawAlgorithm defaultRateRandomDrawAlgorithm;

    @Resource
    private IDrawAlgorithm singleRateRandomDrawAlgorithm;

    protected static Map<Integer,IDrawAlgorithm> drawAlgorithmMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init(){
        drawAlgorithmMap.put(DrawRateRandomDrawAlgorithmEnum.DefaultRateRandomDrawAlgorithm.getCode(),defaultRateRandomDrawAlgorithm);
        drawAlgorithmMap.put(DrawRateRandomDrawAlgorithmEnum.SingleRateRandomDrawAlgorithm.getCode(), singleRateRandomDrawAlgorithm);
    }
}
