package com.kevin.domain.rule.model.rsep;

import com.kevin.common.Result;
import lombok.Data;

/** 规则引擎返回结果
 * @author wang
 * @create 2023-11-08-11:28
 */
@Data
public class EngineResult extends Result {
    /** 执行结果 */
    private boolean isSuccess;
    /** 用户ID */
    private String userId;
    /** 规则树ID */
    private Long treeId;
    /** 果实节点ID */
    private Long nodeId;
    /** 果实节点值 */
    private String nodeValue;

    public EngineResult(boolean isSuccess, String userId, Long treeId, Long nodeId, String nodeValue) {
        this.isSuccess = isSuccess;
        this.userId = userId;
        this.treeId = treeId;
        this.nodeId = nodeId;
        this.nodeValue = nodeValue;
    }

    public EngineResult() {
    }

    public EngineResult(Integer code, String info) {
        super(code, info);
    }
}
