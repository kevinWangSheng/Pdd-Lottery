package com.kevin.domain.strategy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author wang
 * @create 2023-2023-05-20:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class StrategyDetailBriefVo {
    private Long strategyId;

    private String awardId;

    private String awardName;


    private Integer awardCount;

    private Integer awardSurplusCount;

    private BigDecimal awardRate;
}
