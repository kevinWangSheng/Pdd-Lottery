package com.kevin.domain.rule.model.vo;

import lombok.Data;

/**
 * @author wang
 * @create 2023-11-08-11:28
 */
@Data
public class TreeNodeLineVo {
    /** 节点From */
    private Long nodeIdFrom;
    /** 节点To */
    private Long nodeIdTo;
    /** 限定类型；1:=;2:>;3:<;4:>=;5<=;6:enum[枚举范围] */
    private Integer ruleLimitType;
    /** 限定值 */
    private String ruleLimitValue;

    public TreeNodeLineVo(Long nodeIdFrom, Long nodeIdTo, Integer ruleLimitType, String ruleLimitValue) {
        this.nodeIdFrom = nodeIdFrom;
        this.nodeIdTo = nodeIdTo;
        this.ruleLimitType = ruleLimitType;
        this.ruleLimitValue = ruleLimitValue;
    }

    public TreeNodeLineVo() {
    }
}
