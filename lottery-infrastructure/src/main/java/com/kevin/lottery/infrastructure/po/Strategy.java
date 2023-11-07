package com.kevin.lottery.infrastructure.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 策略配置
 * @TableName strategy
 */
@TableName(value ="strategy")
@Data
@NoArgsConstructor
public class Strategy implements Serializable {
    /**
     * 自增id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 策略id
     */
    private Long strategyId;

    /**
     * 策略描述
     */
    private String strategyDesc;

    /**
     * 策略名称
     */
    private String strategyName;

    /**
     * 策略模式 「1:单项概率、2:总体概率」
     */
    private Integer strategyMode;

    /**
     * 奖品发放方式：[1:即时、2:定时[含活动结束]、3:人工]
     */
    private Integer grantType;

    /**
     * 奖品发放日期
     */
    private Date grantDate;

    /**
     * 扩展信息
     */
    private String extendInfo;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Strategy(Long id, Long strategyId, String strategyDesc, Integer strategyMode, Integer grantType, Date grantDate, String extendInfo, Date createTime, Date updateTime) {
        this.id = id;
        this.strategyId = strategyId;
        this.strategyDesc = strategyDesc;
        this.strategyMode = strategyMode;
        this.grantType = grantType;
        this.grantDate = grantDate;
        this.extendInfo = extendInfo;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

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
        Strategy other = (Strategy) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStrategyId() == null ? other.getStrategyId() == null : this.getStrategyId().equals(other.getStrategyId()))
            && (this.getStrategyDesc() == null ? other.getStrategyDesc() == null : this.getStrategyDesc().equals(other.getStrategyDesc()))
            && (this.getStrategyName() == null ? other.getStrategyName() == null : this.getStrategyName().equals(other.getStrategyName()))
            && (this.getStrategyMode() == null ? other.getStrategyMode() == null : this.getStrategyMode().equals(other.getStrategyMode()))
            && (this.getGrantType() == null ? other.getGrantType() == null : this.getGrantType().equals(other.getGrantType()))
            && (this.getGrantDate() == null ? other.getGrantDate() == null : this.getGrantDate().equals(other.getGrantDate()))
            && (this.getExtendInfo() == null ? other.getExtendInfo() == null : this.getExtendInfo().equals(other.getExtendInfo()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    public Strategy(Long strategyId, String strategyDesc, String strategyName, Integer strategyMode, Integer grantType) {
        this.strategyId = strategyId;
        this.strategyDesc = strategyDesc;
        this.strategyName = strategyName;
        this.strategyMode = strategyMode;
        this.grantType = grantType;
    }

    public Strategy(Long strategyId, String strategyDesc, Integer strategyMode, Integer grantType, String extendInfo) {
        this.strategyId = strategyId;
        this.strategyDesc = strategyDesc;
        this.strategyMode = strategyMode;
        this.grantType = grantType;
        this.extendInfo = extendInfo;
    }

    public Strategy(Long strategyId, String strategyDesc, Integer strategyMode, Integer grantType, Date grantDate, String extendInfo) {
        this.strategyId = strategyId;
        this.strategyDesc = strategyDesc;
        this.strategyMode = strategyMode;
        this.grantType = grantType;
        this.grantDate = grantDate;
        this.extendInfo = extendInfo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStrategyId() == null) ? 0 : getStrategyId().hashCode());
        result = prime * result + ((getStrategyDesc() == null) ? 0 : getStrategyDesc().hashCode());
        result = prime * result + ((getStrategyName() == null) ? 0 : getStrategyName().hashCode());
        result = prime * result + ((getStrategyMode() == null) ? 0 : getStrategyMode().hashCode());
        result = prime * result + ((getGrantType() == null) ? 0 : getGrantType().hashCode());
        result = prime * result + ((getGrantDate() == null) ? 0 : getGrantDate().hashCode());
        result = prime * result + ((getExtendInfo() == null) ? 0 : getExtendInfo().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", strategyId=").append(strategyId);
        sb.append(", strategyDesc=").append(strategyDesc);
        sb.append(", strategyName=").append(strategyName);
        sb.append(", strategyMode=").append(strategyMode);
        sb.append(", grantType=").append(grantType);
        sb.append(", grantDate=").append(grantDate);
        sb.append(", extendInfo=").append(extendInfo);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}