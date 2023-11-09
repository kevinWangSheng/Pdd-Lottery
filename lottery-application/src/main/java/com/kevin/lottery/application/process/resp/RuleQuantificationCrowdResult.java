package com.kevin.lottery.application.process.resp;

import com.kevin.common.Result;

/**
 * @author wang
 * @create 2023-11-09-14:44
 */
public class RuleQuantificationCrowdResult extends Result {

    private Long activityId;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public RuleQuantificationCrowdResult(Integer code, String info, Long activityId) {
        super(code, info);
        this.activityId = activityId;
    }

    public RuleQuantificationCrowdResult(Long activityId) {
        this.activityId = activityId;
    }

    public RuleQuantificationCrowdResult(Integer code, String info) {
        super(code, info);
    }

    public RuleQuantificationCrowdResult() {
    }
}
