package com.kevin.domain.activity.model.vo;

/**
 * @author wang
 * @create 2023-11-11-20:33
 */
public class ActivityPartakeRecordVO {
    /** 用户ID */
    private String uId;
    /** activity 活动ID */
    private Long activityId;
    /** 库存 */
    private Integer stockCount;
    /** activity 库存剩余 */
    private Integer stockSurplusCount;

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

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Integer getStockSurplusCount() {
        return stockSurplusCount;
    }

    public void setStockSurplusCount(Integer stockSurplusCount) {
        this.stockSurplusCount = stockSurplusCount;
    }

    public ActivityPartakeRecordVO() {
    }

    public ActivityPartakeRecordVO(String uId, Long activityId, Integer stockCount, Integer stockSurplusCount) {
        this.uId = uId;
        this.activityId = activityId;
        this.stockCount = stockCount;
        this.stockSurplusCount = stockSurplusCount;
    }
}
