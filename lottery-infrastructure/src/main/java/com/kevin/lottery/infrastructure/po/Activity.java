package com.kevin.lottery.infrastructure.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 活动配置
 * @TableName activity
 */
@TableName(value ="activity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity implements Serializable {
    /**
     * 自增id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 活动描述
     */
    private String activityDesc;

    /**
     * 活动开始时间
     */
    private Date beginDateTime;

    /**
     * 活动结束时间
     */
    private Date endDateTime;

    /**
     * 库存
     */
    private Integer stockCount;

    /**
     * 每次活动可以参加人数
     */
    private Integer takeCount;

    /**
     * 活动状态：1编辑、2提审、3撤审、4通过、5运行(审核通过后worker扫描状态)、6拒绝、7关闭、8开启
     */
    private Integer state;

    private Long strategyId;

    private Integer stocksurpluscount;
    public Activity(Long activityId, String activityName, String activityDesc, Date beginDateTime, Date endDateTime, Integer stockCount, Integer takeCount, Integer state, String creator) {
        this.activityId = activityId;
        this.activityName = activityName;
        this.activityDesc = activityDesc;
        this.beginDateTime = beginDateTime;
        this.endDateTime = endDateTime;
        this.stockCount = stockCount;
        this.takeCount = takeCount;
        this.state = state;
        this.creator = creator;
    }

    public Activity(Long activityId, String activityName, String activityDesc, Date beginDateTime, Date endDateTime, Integer stockCount, Integer takeCount, Integer state, String creator,Long strategyId,Integer stocksurpluscount) {
        this.activityId = activityId;
        this.activityName = activityName;
        this.activityDesc = activityDesc;
        this.beginDateTime = beginDateTime;
        this.endDateTime = endDateTime;
        this.stockCount = stockCount;
        this.takeCount = takeCount;
        this.state = state;
        this.creator = creator;
        this.strategyId = strategyId;
        this.stocksurpluscount = stocksurpluscount;
    }


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String creator;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Activity other = (Activity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getActivityId() == null ? other.getActivityId() == null : this.getActivityId().equals(other.getActivityId()))
            && (this.getActivityName() == null ? other.getActivityName() == null : this.getActivityName().equals(other.getActivityName()))
            && (this.getActivityDesc() == null ? other.getActivityDesc() == null : this.getActivityDesc().equals(other.getActivityDesc()))
            && (this.getBeginDateTime() == null ? other.getBeginDateTime() == null : this.getBeginDateTime().equals(other.getBeginDateTime()))
            && (this.getEndDateTime() == null ? other.getEndDateTime() == null : this.getEndDateTime().equals(other.getEndDateTime()))
            && (this.getStockCount() == null ? other.getStockCount() == null : this.getStockCount().equals(other.getStockCount()))
            && (this.getTakeCount() == null ? other.getTakeCount() == null : this.getTakeCount().equals(other.getTakeCount()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()));
    }

    public Activity(Long activityId, String activityName, String activityDesc, Date beginDateTime, Date endDateTime, Integer stockCount, Integer takeCount, Integer state, Date createTime, Date updateTime, String creator) {
        this.activityId = activityId;
        this.activityName = activityName;
        this.activityDesc = activityDesc;
        this.beginDateTime = beginDateTime;
        this.endDateTime = endDateTime;
        this.stockCount = stockCount;
        this.takeCount = takeCount;
        this.state = state;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.creator = creator;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getActivityId() == null) ? 0 : getActivityId().hashCode());
        result = prime * result + ((getActivityName() == null) ? 0 : getActivityName().hashCode());
        result = prime * result + ((getActivityDesc() == null) ? 0 : getActivityDesc().hashCode());
        result = prime * result + ((getBeginDateTime() == null) ? 0 : getBeginDateTime().hashCode());
        result = prime * result + ((getEndDateTime() == null) ? 0 : getEndDateTime().hashCode());
        result = prime * result + ((getStockCount() == null) ? 0 : getStockCount().hashCode());
        result = prime * result + ((getTakeCount() == null) ? 0 : getTakeCount().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", activityId=").append(activityId);
        sb.append(", activityName=").append(activityName);
        sb.append(", activityDesc=").append(activityDesc);
        sb.append(", beginDateTime=").append(beginDateTime);
        sb.append(", endDateTime=").append(endDateTime);
        sb.append(", stockCount=").append(stockCount);
        sb.append(", takeCount=").append(takeCount);
        sb.append(", state=").append(state);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", creator=").append(creator);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}