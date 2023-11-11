package com.kevin.domain.activity.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**活动账单【库存、状态、日期、个人参与次数】
 * @author wang
 * @create 2023-11-07-12:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ActivityBilVO {
    /**
     * 用户id
     */
    private String uid;
    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 活动名称
     */
    private String activityName;
    /**
     * 活动开始时间
     */
    private Date beginDateTime;
    /**
     * 活动结束时间
     */
    private Date endDateTime;
    /**
     * 库存剩余
     */
    private Integer stockSurplusCount;
    /**
     * 活动状态：1编辑、2提审、3撤审、4通过、5运行(审核通过后worker扫描状态)、6拒绝、7关闭、8开启 Constants.ActivityState
     */
    private Integer state;

    private Long strategyId;
    /** 库存 */
    private Integer stockCount;
    /**
     * 每个人可以参与次数
     */
    private Integer takeCount;
    /**
     * 剩余参与次数
     */
    private Integer userTakeLeftCount;
}
