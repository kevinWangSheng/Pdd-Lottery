package com.kevin.lottery.application.process.resp;

import com.kevin.common.Constance;
import com.kevin.common.Result;
import com.kevin.domain.strategy.model.vo.DrawAwardInfo;
import lombok.Data;

/**
 * @author wang
 * @create 2023-11-07-18:49
 */
@Data
public class DrawProcessResp extends Result {
    private DrawAwardInfo drawAwardInfo;

    public DrawProcessResp() {
    }

    public DrawProcessResp(DrawAwardInfo drawAwardInfo) {
        this.drawAwardInfo = drawAwardInfo;
    }

    public DrawProcessResp(Integer code, String info) {
        super(code, info);
    }

    public DrawProcessResp(Integer code, String info, DrawAwardInfo drawAwardInfo) {
        super(code, info);
        this.drawAwardInfo = drawAwardInfo;
    }

    public DrawProcessResp(Constance.ResponseCode resp) {
        super(resp.getCode(),resp.getDesc());
    }
}
