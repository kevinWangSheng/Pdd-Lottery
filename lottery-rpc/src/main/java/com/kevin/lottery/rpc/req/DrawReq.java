package com.kevin.lottery.rpc.req;

/**抽奖请求
 * @author wang
 * @create 2023-11-09-13:10
 */
public class DrawReq {
    /** 用户id */
    private String uId;
    /** 活动ID */
    private Long activityId;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public DrawReq() {
    }

    public DrawReq(String uId, Long activityId) {
        this.uId = uId;
        this.activityId = activityId;
    }
}
