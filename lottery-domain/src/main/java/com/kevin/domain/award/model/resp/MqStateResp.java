package com.kevin.domain.award.model.resp;

import com.kevin.common.Result;

/**
 * @author wang
 * @create 2023-11-10-11:34
 */
public class MqStateResp extends Result {
    private Integer mqState;

    public MqStateResp() {
    }

    public MqStateResp(Integer code, String info, Integer mqState) {
        super(code, info);
        this.mqState = mqState;
    }

    public MqStateResp(Integer mqState) {
        this.mqState = mqState;
    }

    public Integer getMqState() {
        return mqState;
    }

    public void setMqState(Integer mqState) {
        this.mqState = mqState;
    }
}
