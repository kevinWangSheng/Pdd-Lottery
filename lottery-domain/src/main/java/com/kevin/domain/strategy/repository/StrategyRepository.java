package com.kevin.domain.strategy.repository;


import com.baomidou.mybatisplus.extension.service.IService;
import com.kevin.domain.strategy.model.aggregates.StrategyRich;
import com.kevin.lottery.infrastructure.po.Award;
import com.kevin.lottery.infrastructure.po.Strategy;

import java.util.List;

/**
* @author wang sheng hui
* @description 针对表【strategy(策略配置)】的数据库操作Service
* @createDate 2023-11-04 16:07:32
*/
public interface StrategyRepository extends IService<Strategy> {
    StrategyRich queryStrategyRich(Long strategyId);

    Award  queryAward(String awardId);

    List<String> queryNoStockStrategyAwardList(Long strategyId);

    boolean deducStock(Long strategyId, String awardId);
}
