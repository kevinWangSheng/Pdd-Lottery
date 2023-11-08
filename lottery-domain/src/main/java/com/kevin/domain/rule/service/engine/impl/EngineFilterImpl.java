package com.kevin.domain.rule.service.engine.impl;

import com.kevin.common.Constance;
import com.kevin.domain.rule.model.aggregates.TreeRuleRich;
import com.kevin.domain.rule.model.req.DecisionMatterReq;
import com.kevin.domain.rule.model.rsep.EngineResult;
import com.kevin.domain.rule.model.vo.TreeNodeVo;
import com.kevin.domain.rule.repository.IRuleRespository;
import com.kevin.domain.rule.service.engine.EngineBasic;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wang
 * @create 2023-11-08-11:39
 */
@Service
public class EngineFilterImpl extends EngineBasic{

    @Resource
    private IRuleRespository ruleRespository;
    @Override
    public EngineResult process(DecisionMatterReq req) {
        if(req == null){
            return new EngineResult(Constance.ResponseCode.PARAMERROR.getCode(),Constance.ResponseCode.PARAMERROR.getDesc());
        }
        TreeRuleRich treeRuleRich = ruleRespository.queryTreeRuleRich(req.getTreeId());
        if(treeRuleRich == null){
            return new EngineResult(Constance.ResponseCode.PARAMERROR.getCode(),Constance.ResponseCode.PARAMERROR.getDesc());
        }
        // 决策节点
        TreeNodeVo treeNodeVo = engineDecisionMaker(treeRuleRich, req);
        if(treeNodeVo == null){
            return new EngineResult(Constance.ResponseCode.UNLL_ERROR.getCode(), Constance.ResponseCode.UNLL_ERROR.getDesc());
        }

        return new EngineResult(true,req.getUserId(),treeNodeVo.getTreeId(),treeNodeVo.getTreeNodeId(),treeNodeVo.getNodeValue());
    }
}
