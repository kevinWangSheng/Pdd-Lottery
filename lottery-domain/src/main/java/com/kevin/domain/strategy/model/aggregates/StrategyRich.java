package com.kevin.domain.strategy.model.aggregates;

import com.kevin.lottery.infrastructure.po.Strategy;
import com.kevin.lottery.infrastructure.po.StrategyDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wang
 * @create 2023-2023-04-16:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StrategyRich {
    private Long strategyId;

    private Strategy strategy;

    private List<StrategyDetail> strategyDetails;


}
