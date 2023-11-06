package com.kevin.domain.strategy.repository;


import com.kevin.domain.strategy.model.aggregates.StrategyRich;
import com.kevin.domain.strategy.model.vo.AwardBriefVo;

import java.util.List;

/**
* @author wang sheng hui
* @description 针对表【strategy(策略配置)】的数据库操作Service
* @createDate 2023-11-04 16:07:32
*/
public interface StrategyRepository   {
    StrategyRich queryStrategyRich(Long strategyId);

    AwardBriefVo queryAward(String awardId);

    List<String> queryNoStockStrategyAwardList(Long strategyId);

    boolean deducStock(Long strategyId, String awardId);
}
