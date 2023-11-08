package com.kevin.lottery.infrastructure.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName rule_tree_node_line
 */
@TableName(value ="rule_tree_node_line")
@Data
public class RuleTreeNodeLine implements Serializable {
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
     * 节点From
     */
    private Long nodeIdFrom;

    /**
     * 节点To
     */
    private Long nodeIdTo;

    /**
     * 限定类型；1:=;2:>;3:<;4:>=;5<=;6:enum[枚举范围];7:果实
     */
    private Integer ruleLimitType;

    /**
     * 限定值
     */
    private String ruleLimitValue;

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
        RuleTreeNodeLine other = (RuleTreeNodeLine) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTreeId() == null ? other.getTreeId() == null : this.getTreeId().equals(other.getTreeId()))
            && (this.getNodeIdFrom() == null ? other.getNodeIdFrom() == null : this.getNodeIdFrom().equals(other.getNodeIdFrom()))
            && (this.getNodeIdTo() == null ? other.getNodeIdTo() == null : this.getNodeIdTo().equals(other.getNodeIdTo()))
            && (this.getRuleLimitType() == null ? other.getRuleLimitType() == null : this.getRuleLimitType().equals(other.getRuleLimitType()))
            && (this.getRuleLimitValue() == null ? other.getRuleLimitValue() == null : this.getRuleLimitValue().equals(other.getRuleLimitValue()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTreeId() == null) ? 0 : getTreeId().hashCode());
        result = prime * result + ((getNodeIdFrom() == null) ? 0 : getNodeIdFrom().hashCode());
        result = prime * result + ((getNodeIdTo() == null) ? 0 : getNodeIdTo().hashCode());
        result = prime * result + ((getRuleLimitType() == null) ? 0 : getRuleLimitType().hashCode());
        result = prime * result + ((getRuleLimitValue() == null) ? 0 : getRuleLimitValue().hashCode());
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
        sb.append(", nodeIdFrom=").append(nodeIdFrom);
        sb.append(", nodeIdTo=").append(nodeIdTo);
        sb.append(", ruleLimitType=").append(ruleLimitType);
        sb.append(", ruleLimitValue=").append(ruleLimitValue);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}