package com.kevin.domain.strategy.model.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wang
 * @create 2023-2023-04-16:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrawReq {
    // 用户id
    private String userId;
    // 策略id，抽奖使用
    private Long strategyId;

}
