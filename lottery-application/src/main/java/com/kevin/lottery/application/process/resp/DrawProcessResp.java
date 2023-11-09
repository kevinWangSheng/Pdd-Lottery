package com.kevin.lottery.application.process.resp;

import com.kevin.common.Constance;
import com.kevin.common.Result;
import com.kevin.domain.strategy.model.vo.DrawAwardVO;
import lombok.Data;

/**
 * @author wang
 * @create 2023-11-07-18:49
 */
@Data
public class DrawProcessResp extends Result {
    private DrawAwardVO drawAwardVO;

    public DrawProcessResp() {
    }

    public DrawProcessResp(DrawAwardVO drawAwardVO) {
        this.drawAwardVO = drawAwardVO;
    }

    public DrawProcessResp(Integer code, String info) {
        super(code, info);
    }

    public DrawProcessResp(Integer code, String info, DrawAwardVO drawAwardVO) {
        super(code, info);
        this.drawAwardVO = drawAwardVO;
    }

    public DrawProcessResp(Constance.ResponseCode resp) {
        super(resp.getCode(),resp.getDesc());
    }
}
