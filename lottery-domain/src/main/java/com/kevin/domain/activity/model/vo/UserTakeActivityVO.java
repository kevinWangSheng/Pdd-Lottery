package com.kevin.domain.activity.model.vo;

import lombok.Data;

/**
 * @author wang
 * @create 2023-11-07-19:34
 */
@Data
public class UserTakeActivityVO {
    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 活动领取ID
     */
    private Long takeId;

    /**
     * 策略ID
     */
    private Long strategyId;

    /**
     * 活动单使用状态 0未使用、1已使用
     * Constants.TaskState
     */
    private Integer state;
}
