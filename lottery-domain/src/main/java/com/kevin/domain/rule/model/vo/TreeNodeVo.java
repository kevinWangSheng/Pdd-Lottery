package com.kevin.domain.rule.model.vo;

import lombok.Data;

import java.util.List;

/**规则树节点信息
 * @author wang
 * @create 2023-11-08-11:28
 */
@Data
public class TreeNodeVo {
    /** 规则树ID */
    private Long treeId;
    /** 规则树节点ID */
    private Long treeNodeId;
    /** 节点类型；1子叶、2果实 */
    private Integer nodeType;
    /** 节点值[nodeType=2]；果实值 */
    private String nodeValue;
    /** 规则Key */
    private String ruleKey;
    /** 规则描述 */
    private String ruleDesc;
    /** 节点链路 */
    private List<TreeNodeLineVo> treeNodeLineInfoList;

    public TreeNodeVo() {
    }

    public TreeNodeVo(Long treeId, Long treeNodeId, Integer nodeType, String nodeValue, String ruleKey, String ruleDesc, List<TreeNodeLineVo> treeNodeLineInfoList) {
        this.treeId = treeId;
        this.treeNodeId = treeNodeId;
        this.nodeType = nodeType;
        this.nodeValue = nodeValue;
        this.ruleKey = ruleKey;
        this.ruleDesc = ruleDesc;
        this.treeNodeLineInfoList = treeNodeLineInfoList;
    }
}
