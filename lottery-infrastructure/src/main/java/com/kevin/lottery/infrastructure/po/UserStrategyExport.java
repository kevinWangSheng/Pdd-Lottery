package com.kevin.lottery.infrastructure.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户策略计算结果表
 * @TableName user_strategy_export_001
 */
@TableName(value ="user_strategy_export_001")
@Data
public class UserStrategyExport implements Serializable {


    private Long id;

    /**
     * 
     */
    private String uid;

    /**
     * 
     */
    private Long activityid;

    /**
     * 
     */
    private Long orderid;

    /**
     * 
     */
    private Long strategyid;

    /**
     * 
     */
    private Integer strategytype;

    /**
     * 
     */
    private Integer granttype;

    /**
     * 
     */
    private Date grantdate;

    /**
     * 
     */
    private Integer grantstate;

    /**
     * 
     */
    private Long awardid;

    /**
     * 
     */
    private Integer awardtype;

    /**
     * 
     */
    private String awardname;

    /**
     * 
     */
    private String awardcontent;

    /**
     * 
     */
    private String uuid;

    /**
     * 
     */
    private Date createtime;

    /**
     * 
     */
    private Date updatetime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public UserStrategyExport() {
    }

    public UserStrategyExport(Long id, String uid, Long activityid, Long orderid, Long strategyid, Integer strategytype, Integer granttype, Date grantdate, Integer grantstate, Long awardid, Integer awardtype, String awardname, String awardcontent, String uuid) {
        this.id = id;
        this.uid = uid;
        this.activityid = activityid;
        this.orderid = orderid;
        this.strategyid = strategyid;
        this.strategytype = strategytype;
        this.granttype = granttype;
        this.grantdate = grantdate;
        this.grantstate = grantstate;
        this.awardid = awardid;
        this.awardtype = awardtype;
        this.awardname = awardname;
        this.awardcontent = awardcontent;
        this.uuid = uuid;
    }

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
        UserStrategyExport other = (UserStrategyExport) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getActivityid() == null ? other.getActivityid() == null : this.getActivityid().equals(other.getActivityid()))
            && (this.getOrderid() == null ? other.getOrderid() == null : this.getOrderid().equals(other.getOrderid()))
            && (this.getStrategyid() == null ? other.getStrategyid() == null : this.getStrategyid().equals(other.getStrategyid()))
            && (this.getStrategytype() == null ? other.getStrategytype() == null : this.getStrategytype().equals(other.getStrategytype()))
            && (this.getGranttype() == null ? other.getGranttype() == null : this.getGranttype().equals(other.getGranttype()))
            && (this.getGrantdate() == null ? other.getGrantdate() == null : this.getGrantdate().equals(other.getGrantdate()))
            && (this.getGrantstate() == null ? other.getGrantstate() == null : this.getGrantstate().equals(other.getGrantstate()))
            && (this.getAwardid() == null ? other.getAwardid() == null : this.getAwardid().equals(other.getAwardid()))
            && (this.getAwardtype() == null ? other.getAwardtype() == null : this.getAwardtype().equals(other.getAwardtype()))
            && (this.getAwardname() == null ? other.getAwardname() == null : this.getAwardname().equals(other.getAwardname()))
            && (this.getAwardcontent() == null ? other.getAwardcontent() == null : this.getAwardcontent().equals(other.getAwardcontent()))
            && (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getActivityid() == null) ? 0 : getActivityid().hashCode());
        result = prime * result + ((getOrderid() == null) ? 0 : getOrderid().hashCode());
        result = prime * result + ((getStrategyid() == null) ? 0 : getStrategyid().hashCode());
        result = prime * result + ((getStrategytype() == null) ? 0 : getStrategytype().hashCode());
        result = prime * result + ((getGranttype() == null) ? 0 : getGranttype().hashCode());
        result = prime * result + ((getGrantdate() == null) ? 0 : getGrantdate().hashCode());
        result = prime * result + ((getGrantstate() == null) ? 0 : getGrantstate().hashCode());
        result = prime * result + ((getAwardid() == null) ? 0 : getAwardid().hashCode());
        result = prime * result + ((getAwardtype() == null) ? 0 : getAwardtype().hashCode());
        result = prime * result + ((getAwardname() == null) ? 0 : getAwardname().hashCode());
        result = prime * result + ((getAwardcontent() == null) ? 0 : getAwardcontent().hashCode());
        result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uid=").append(uid);
        sb.append(", activityid=").append(activityid);
        sb.append(", orderid=").append(orderid);
        sb.append(", strategyid=").append(strategyid);
        sb.append(", strategytype=").append(strategytype);
        sb.append(", granttype=").append(granttype);
        sb.append(", grantdate=").append(grantdate);
        sb.append(", grantstate=").append(grantstate);
        sb.append(", awardid=").append(awardid);
        sb.append(", awardtype=").append(awardtype);
        sb.append(", awardname=").append(awardname);
        sb.append(", awardcontent=").append(awardcontent);
        sb.append(", uuid=").append(uuid);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}