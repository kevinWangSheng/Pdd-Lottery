package com.kevin.domain.strategy.model.resp;

import com.kevin.domain.strategy.model.vo.DrawAwardInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wang
 * @create 2023-2023-04-16:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrawResp {
    // 用户id
    private String uid;
    // 策略id
    private Long strategyId;

    private Integer drawState;

    private DrawAwardInfo drawAwardInfo;


    public DrawResp(String uid, Long strategyId, Integer drawState) {
        this.uid = uid;
        this.strategyId = strategyId;
        this.drawState = drawState;
    }
}
