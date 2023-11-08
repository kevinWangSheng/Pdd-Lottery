package com.kevin.domain.rule.model.req;

import lombok.Data;

import java.util.Map;

/** 决策物料
 * @author wang
 * @create 2023-11-08-11:27
 */
@Data
public class DecisionMatterReq {

    private Long treeId;

    private String userId;

    private Map<String,Object> valueMap;

    public DecisionMatterReq(Long treeId, String userId, Map<String, Object> valueMap) {
        this.treeId = treeId;
        this.userId = userId;
        this.valueMap = valueMap;
    }

    public DecisionMatterReq() {
    }
}
