package com.kevin.domain.rule.service.logic.impl;

import com.kevin.common.Constance;
import com.kevin.domain.rule.model.req.DecisionMatterReq;
import com.kevin.domain.rule.model.vo.TreeNodeLineVo;
import com.kevin.domain.rule.service.logic.LogicBasic;
import org.springframework.stereotype.Service;

import java.util.List;

/** 年龄逻辑过滤器
 * @author wang
 * @create 2023-11-08-11:41
 */
@Service
public class AgeLogicFilter extends LogicBasic {


    @Override
    public String matterValue(DecisionMatterReq req) {
        return req.getValueMap().get(Constance.Matter.AGE).toString();
    }
}
