package com.kevin.domain.rule.service.logic.impl;

import com.kevin.common.Constance;
import com.kevin.domain.rule.model.req.DecisionMatterReq;
import com.kevin.domain.rule.service.logic.LogicBasic;
import org.springframework.stereotype.Service;

/** 姓名字段过滤器
 * @author wang
 * @create 2023-11-08-11:41
 */
@Service
public class GenderLogicFilter extends LogicBasic {

    @Override
    public String matterValue(DecisionMatterReq req) {
        return req.getValueMap().get(Constance.Matter.GENDER).toString();
    }
}
