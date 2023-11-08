package com.kevin.domain.rule.model.aggregates;

import com.kevin.domain.rule.model.vo.TreeNodeVo;
import com.kevin.domain.rule.model.vo.TreeRootVo;
import lombok.Data;

import java.util.Map;

/**
 * @author wang
 * @create 2023-11-08-11:27
 */
@Data
public class TreeRuleRich {

    /**
     * 树根信息
     */
    private TreeRootVo treeRootVo;

    /**
     * 子节点信息
     */
    private Map<Long, TreeNodeVo> treeNodeMap;

    public TreeRuleRich(TreeRootVo treeRootVo, Map<Long, TreeNodeVo> treeNodeMap) {
        this.treeRootVo = treeRootVo;
        this.treeNodeMap = treeNodeMap;
    }

    public TreeRuleRich() {
    }
}
