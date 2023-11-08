package com.kevin.domain.rule.service.logic;

import com.kevin.common.Constance;
import com.kevin.domain.rule.model.vo.TreeNodeLineVo;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author wang
 * @create 2023-11-08-11:41
 */
public abstract class LogicBasic implements LogicFilter {
    @Override
    public Long filter(String matterValue, List<TreeNodeLineVo> treeNodeLineVoList) {
        if(treeNodeLineVoList == null ){
            return Constance.Global.TREE_NULL_NODE;
        }
        for(TreeNodeLineVo treeNodeLineVo:treeNodeLineVoList){
            if(decisionLogic(matterValue,treeNodeLineVo)){
                return treeNodeLineVo.getNodeIdTo();
            }
        }
        return Constance.Global.TREE_NULL_NODE;
    }

    private boolean decisionLogic(String matterValue, TreeNodeLineVo nodeLine) {
        if(StringUtils.isBlank(matterValue) || nodeLine == null ){
            return false;
        }
        switch (nodeLine.getRuleLimitType()) {
            case Constance.RuleLimitType.EQUAL:
                return matterValue.equals(nodeLine.getRuleLimitValue());
            case Constance.RuleLimitType.GT:
                return Double.parseDouble(matterValue) > Double.parseDouble(nodeLine.getRuleLimitValue());
            case Constance.RuleLimitType.LT:
                return Double.parseDouble(matterValue) < Double.parseDouble(nodeLine.getRuleLimitValue());
            case Constance.RuleLimitType.GE:
                return Double.parseDouble(matterValue) >= Double.parseDouble(nodeLine.getRuleLimitValue());
            case Constance.RuleLimitType.LE:
                return Double.parseDouble(matterValue) <= Double.parseDouble(nodeLine.getRuleLimitValue());
            default:
                return false;
        }
    }
}
