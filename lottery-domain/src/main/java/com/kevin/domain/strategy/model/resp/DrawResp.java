package com.kevin.domain.strategy.model.resp;

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
    // 奖品名称
    private String awardName;
    // 奖品id
    private String rewardId;
}
