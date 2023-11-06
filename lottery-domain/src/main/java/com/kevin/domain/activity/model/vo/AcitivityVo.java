package com.kevin.domain.activity.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author wang
 * @create 2023-2023-05-19:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AcitivityVo {
    // 活动id
    private Long acitivityId;
    // 活动名称
    private String activityName;
    // 活动描述
    private String activityDesc;
    // 开始时间
    private Date beginTime;
    // 结束时间
    private Date endTime;
    // 活动状态
    private Integer state;

    // 活动库存
    private Integer stockCount;
    // 活动可以参与次数
    private Integer takeCount;

    // 创建人
    private String creator;
}
