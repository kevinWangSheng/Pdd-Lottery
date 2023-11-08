package com.kevin.domain.rule.service.engine;

import com.kevin.common.Constance;
import com.kevin.domain.rule.service.logic.LogicFilter;
import com.kevin.domain.rule.service.logic.impl.AgeLogicFilter;
import com.kevin.domain.rule.service.logic.impl.GenderLogicFilter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wang
 * @create 2023-11-08-11:38
 */
public class EngineConfig {
    @Resource
    private AgeLogicFilter ageLogicFilter;

    @Resource
    private GenderLogicFilter genderLogicFilter;

    protected Map<String, LogicFilter> logicFilterMap = new ConcurrentHashMap();

    
    @PostConstruct
    public void init(){
        logicFilterMap.put(Constance.Matter.USER_AGE,ageLogicFilter);
        logicFilterMap.put(Constance.Matter.USER_GENDER,genderLogicFilter);

    }
}
