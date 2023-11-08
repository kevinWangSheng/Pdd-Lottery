package com.kevin.lottery.infrastructure.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName rule_tree_node
 */
@TableName(value ="rule_tree_node")
@Data
public class RuleTreeNode implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 规则树ID
     */
    private Long treeId;

    /**
     * 节点类型；1子叶、2果实
     */
    private Integer nodeType;

    /**
     * 节点值[nodeType=2]；果实值
     */
    private String nodeValue;

    /**
     * 规则Key
     */
    private String ruleKey;

    /**
     * 规则描述
     */
    private String ruleDesc;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        RuleTreeNode other = (RuleTreeNode) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTreeId() == null ? other.getTreeId() == null : this.getTreeId().equals(other.getTreeId()))
            && (this.getNodeType() == null ? other.getNodeType() == null : this.getNodeType().equals(other.getNodeType()))
            && (this.getNodeValue() == null ? other.getNodeValue() == null : this.getNodeValue().equals(other.getNodeValue()))
            && (this.getRuleKey() == null ? other.getRuleKey() == null : this.getRuleKey().equals(other.getRuleKey()))
            && (this.getRuleDesc() == null ? other.getRuleDesc() == null : this.getRuleDesc().equals(other.getRuleDesc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTreeId() == null) ? 0 : getTreeId().hashCode());
        result = prime * result + ((getNodeType() == null) ? 0 : getNodeType().hashCode());
        result = prime * result + ((getNodeValue() == null) ? 0 : getNodeValue().hashCode());
        result = prime * result + ((getRuleKey() == null) ? 0 : getRuleKey().hashCode());
        result = prime * result + ((getRuleDesc() == null) ? 0 : getRuleDesc().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", treeId=").append(treeId);
        sb.append(", nodeType=").append(nodeType);
        sb.append(", nodeValue=").append(nodeValue);
        sb.append(", ruleKey=").append(ruleKey);
        sb.append(", ruleDesc=").append(ruleDesc);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}