package com.kevin.domain.rule.model.vo;

import lombok.Data;

/**规则树根配置
 * @author wang
 * @create 2023-11-08-11:29
 */
@Data
public class TreeRootVo {
    /** 规则树ID */
    private Long treeId;
    /** 规则树根ID */
    private Long treeRootNodeId;
    /** 规则树名称 */
    private String treeName;

    public TreeRootVo(Long treeId, Long treeRootNodeId, String treeName) {
        this.treeId = treeId;
        this.treeRootNodeId = treeRootNodeId;
        this.treeName = treeName;
    }

    public TreeRootVo() {
    }
}
