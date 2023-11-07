package com.kevin.lottery.application.process.req;

import com.kevin.common.Result;
import lombok.Data;

/**
 * @author wang
 * @create 2023-11-07-18:48
 */
@Data
public class DrawProcessReq {

    private String uId;

    private Long activityId;

    public DrawProcessReq() {
    }

    public DrawProcessReq(String uId, Long activityId) {
        this.uId = uId;
        this.activityId = activityId;
    }
}
