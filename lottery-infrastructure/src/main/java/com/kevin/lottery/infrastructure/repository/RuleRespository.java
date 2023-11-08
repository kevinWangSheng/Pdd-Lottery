package com.kevin.lottery.infrastructure.repository;

import com.kevin.domain.rule.model.aggregates.TreeRuleRich;
import com.kevin.domain.rule.model.vo.TreeNodeLineVo;
import com.kevin.domain.rule.model.vo.TreeNodeVo;
import com.kevin.domain.rule.model.vo.TreeRootVo;
import com.kevin.domain.rule.repository.IRuleRespository;
import com.kevin.lottery.infrastructure.dao.RuleTreeMapper;
import com.kevin.lottery.infrastructure.dao.RuleTreeNodeLineMapper;
import com.kevin.lottery.infrastructure.dao.RuleTreeNodeMapper;
import com.kevin.lottery.infrastructure.po.RuleTree;
import com.kevin.lottery.infrastructure.po.RuleTreeNode;
import com.kevin.lottery.infrastructure.po.RuleTreeNodeLine;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wang
 * @create 2023-11-08-15:34
 */
@Service
public class RuleRespository implements IRuleRespository {

    @Resource
    private RuleTreeMapper ruleTreeMapper;

    @Resource
    private RuleTreeNodeMapper ruleTreeNodeMapper;

    @Resource
    private RuleTreeNodeLineMapper ruleTreeNodeLineMapper;
    @Override
    public TreeRuleRich queryTreeRuleRich(Long treeId) {
        if(treeId == null){
            return null;
        }
        // 查询规则树
        RuleTree ruleTree = ruleTreeMapper.queryRuleTreeByTreeId(treeId);
        TreeRootVo treeRootVo = new TreeRootVo(ruleTree.getId(),ruleTree.getTreeRootNodeId(),ruleTree.getTreeName());

        // 将查询的规则树进行子节点的连接
        Map<Long, TreeNodeVo> treeNodeMap = new HashMap<>();
        List<RuleTreeNode> ruleTreeNodes = ruleTreeNodeMapper.queryRuleTreeNodeList(treeId);
        for(RuleTreeNode ruleTreeNode:ruleTreeNodes){
            List<TreeNodeLineVo> treeNodeLineVoList = new ArrayList<>();

            RuleTreeNodeLine ruleTreeNodeLineReq = new RuleTreeNodeLine();
            ruleTreeNodeLineReq.setTreeId(ruleTreeNode.getTreeId());
            ruleTreeNodeLineReq.setNodeIdFrom(ruleTreeNode.getId());

            List<RuleTreeNodeLine> ruleTreeNodeLines = ruleTreeNodeLineMapper.queryRuleTreeNodeLineList(ruleTreeNodeLineReq);
            for(RuleTreeNodeLine ruleTreeNodeLine:ruleTreeNodeLines){
                TreeNodeLineVo treeNodeLineVo = new TreeNodeLineVo();
                treeNodeLineVo.setNodeIdFrom(ruleTreeNodeLineReq.getNodeIdFrom());
                treeNodeLineVo.setRuleLimitType(ruleTreeNodeLine.getRuleLimitType());
                treeNodeLineVo.setRuleLimitValue(ruleTreeNodeLine.getRuleLimitValue());
                treeNodeLineVo.setNodeIdTo(ruleTreeNodeLine.getNodeIdTo());
                treeNodeLineVoList.add(treeNodeLineVo);
            }
            TreeNodeVo treeNodeVo = new TreeNodeVo();
            treeNodeVo.setNodeType(ruleTreeNode.getNodeType());
            treeNodeVo.setNodeValue(ruleTreeNode.getNodeValue());
            treeNodeVo.setTreeNodeId(ruleTreeNode.getId());
            treeNodeVo.setRuleDesc(ruleTreeNode.getRuleDesc());
            treeNodeVo.setRuleKey(ruleTreeNode.getRuleKey());
            treeNodeVo.setTreeNodeLineInfoList(treeNodeLineVoList);

            treeNodeMap.put(ruleTreeNode.getId(),treeNodeVo);
        }
        return new TreeRuleRich(treeRootVo,treeNodeMap);
    }
}
