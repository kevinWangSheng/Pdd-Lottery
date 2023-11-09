package com.kevin.lottery.rpc.req;

import java.util.Map;

/** 量化抽奖人群请求
 * @author wang
 * @create 2023-11-09-13:11
 */
public class QuantificationDrawReq {
    /** 用户ID */
    private String uId;
    /** 规则树ID */
    private Long treeId;
    /** 决策值 */
    private Map<String, Object> valMap;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public Map<String, Object> getValMap() {
        return valMap;
    }

    public void setValMap(Map<String, Object> valMap) {
        this.valMap = valMap;
    }

    public QuantificationDrawReq() {
    }

    public QuantificationDrawReq(String uId, Long treeId, Map<String, Object> valMap) {
        this.uId = uId;
        this.treeId = treeId;
        this.valMap = valMap;
    }
}
