package com.kevin.domain.activity.model.req;

import com.kevin.domain.activity.model.aggregates.ActivityConfigRich;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wang
 * @create 2023-2023-05-19:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityConfigReq {
    // 活动id
    private Long acitivityId;
    // 活动配置信息
    private ActivityConfigRich activityConfigRich;
}
