package com.kevin.domain.activity.model.req;

import lombok.Data;

import java.util.Date;

/**
 * @author wang
 * @create 2023-11-07-13:06
 */
@Data
public class PartakeReq {
    private String uid;
    private Long activityId;

    private Date partakeDate;


    public PartakeReq(String uid, Long activityId, Date partakeDate) {
        this.uid = uid;
        this.activityId = activityId;
        this.partakeDate = partakeDate;
    }

    public PartakeReq(String uid, Long activityId) {
        this.uid = uid;
        this.activityId = activityId;
    }



    public PartakeReq() {
    }
}
