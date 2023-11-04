package com.kevin.lottery.rpc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author wang
 * @create 2023-2023-03-18:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ActivityDto {
    private Long activityId;

    private String activityName;

    private String activityDesc;

    private Date beginDateTime;

    private Date endDateTime;

    private Integer stockCount;

    private Integer takeCount;

    private Integer state;
}
