package com.kevin.domain.activity.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author wang
 * @create 2023-2023-05-19:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class StrategyDetailVo {
    // 策略id
    private Long strategyId;

    // 策略描述
    private String strategyDesc;
    // 奖品id
    private String awardId;
    // 奖品名称
    private String awardName;

    // 库存信息
    private Integer awardCount;
    // 剩余奖品库存
    private Integer awardSurplusCount;
    // 中奖概率
    private BigDecimal awardRate;

}
