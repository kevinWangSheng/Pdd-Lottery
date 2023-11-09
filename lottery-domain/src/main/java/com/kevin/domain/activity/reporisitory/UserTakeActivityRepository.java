package com.kevin.domain.activity.reporisitory;


import com.kevin.domain.activity.model.vo.DrawOrderVO;

import java.util.Date;

/**
* @author wang sheng hui
* @description 针对表【user_take_activity(用户参与活动记录表)】的数据库操作Service
* @createDate 2023-11-06 21:14:11
*/
public interface UserTakeActivityRepository {
    /**
     * 扣减个人活动参与次数
     * @param activityId 活动id
     * @param activityName 活动名称
     * @param takeCount 参与次数
     * @param userTakeLeftCount 剩余次数
     * @param uId 用户id
     * @param partakeDate 领取时间
     * @return 更新结果
     */
    int subtractionLeftCount(Long activityId, String activityName, Integer takeCount, Integer userTakeLeftCount, String uId, Date partakeDate);

    /**
     * 活动领取
     * @param activityId 活动id
     * @param activityName 活动名称
     * @param takeCount 活动个人可领取次数
     * @param userTakeLeftCount 活动个人剩余领取次数
     * @param uId 用户id
     * @param takeDate 领取时间
     * @param takeId 领取id
     */
    void takeActivity(Long activityId, String activityName, Integer takeCount, Integer userTakeLeftCount, String uId, Date takeDate, Long takeId);

    int lockTracActivity(String uId, Long strategyId, Long takeId);

    void saveUserStrategyExport(DrawOrderVO drawOrderVO);

    void updateInvoiceMqState(String uId, Long orderId, Integer mqState);
}
