package com.kevin.domain.rule.service.engine;

import com.kevin.common.Constance;
import com.kevin.domain.rule.model.aggregates.TreeRuleRich;
import com.kevin.domain.rule.model.req.DecisionMatterReq;
import com.kevin.domain.rule.model.vo.TreeNodeVo;
import com.kevin.domain.rule.model.vo.TreeRootVo;
import com.kevin.domain.rule.service.logic.LogicFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author wang
 * @create 2023-11-08-11:39
 */
public abstract class EngineBasic extends EngineConfig implements EngineFilter {

    private final Logger logger = LoggerFactory.getLogger(EngineBasic.class);

    protected TreeNodeVo engineDecisionMaker(TreeRuleRich treeRuleRich, DecisionMatterReq req){
        TreeRootVo treeRoot = treeRuleRich.getTreeRootVo();
        Map<Long, TreeNodeVo> treeNodeMap = treeRuleRich.getTreeNodeMap();

        Long rootNodeId = treeRoot.getTreeRootNodeId();
        TreeNodeVo treeNode = treeNodeMap.get(rootNodeId);

        if(treeNode == null){
            return null;
        }

        //寻找叶子节点
        while(Constance.NodeType.STEM.equals(treeNode.getNodeType())){
            String ruleKey = treeNode.getRuleKey();
            LogicFilter logicFilter = logicFilterMap.get(ruleKey);
            String matterValue = logicFilter.matterValue(req);
            Long treeId = logicFilter.filter(matterValue, treeNode.getTreeNodeLineInfoList());
            // 获取他的子节点,满足条件的子节点
            treeNode = treeNodeMap.get(treeId);
            logger.info("决策树引擎=>{} userId：{} treeId：{} treeNode：{} ruleKey：{} matterValue：{}", treeRoot.getTreeName(), req.getUserId(), req.getTreeId(), treeNode.getTreeNodeId(), ruleKey, matterValue);
        }
        return treeNode;
    }
}
