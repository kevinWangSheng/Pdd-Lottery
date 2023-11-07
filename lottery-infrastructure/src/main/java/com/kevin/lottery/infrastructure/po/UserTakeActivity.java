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
 * 用户参与活动记录表
 * @TableName user_take_activity
 */
@TableName(value ="user_take_activity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTakeActivity implements Serializable {
    /**
     * 自增id
     */
    private Long id;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 加入id
     */
    private Long takeid;

    /**
     * 活动id
     */
    private Long activityid;

    /**
     * 活动名称
     */
    private String activityname;

    /**
     * 参加日期
     */
    private Date takedate;

    /**
     * 参加数量
     */
    private Integer takecount;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    private Integer state;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public UserTakeActivity(Long id, String uid, Long takeid, Long activityid, String activityname, Date takedate, Integer takecount, String uuid) {
        this.id = id;
        this.uid = uid;
        this.takeid = takeid;
        this.activityid = activityid;
        this.activityname = activityname;
        this.takedate = takedate;
        this.takecount = takecount;
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
        UserTakeActivity other = (UserTakeActivity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getTakeid() == null ? other.getTakeid() == null : this.getTakeid().equals(other.getTakeid()))
            && (this.getActivityid() == null ? other.getActivityid() == null : this.getActivityid().equals(other.getActivityid()))
            && (this.getActivityname() == null ? other.getActivityname() == null : this.getActivityname().equals(other.getActivityname()))
            && (this.getTakedate() == null ? other.getTakedate() == null : this.getTakedate().equals(other.getTakedate()))
            && (this.getTakecount() == null ? other.getTakecount() == null : this.getTakecount().equals(other.getTakecount()))
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
        result = prime * result + ((getTakeid() == null) ? 0 : getTakeid().hashCode());
        result = prime * result + ((getActivityid() == null) ? 0 : getActivityid().hashCode());
        result = prime * result + ((getActivityname() == null) ? 0 : getActivityname().hashCode());
        result = prime * result + ((getTakedate() == null) ? 0 : getTakedate().hashCode());
        result = prime * result + ((getTakecount() == null) ? 0 : getTakecount().hashCode());
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
        sb.append(", takeid=").append(takeid);
        sb.append(", activityid=").append(activityid);
        sb.append(", activityname=").append(activityname);
        sb.append(", takedate=").append(takedate);
        sb.append(", takecount=").append(takecount);
        sb.append(", uuid=").append(uuid);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}