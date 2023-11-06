package com.kevin.domain.strategy.repository;


import com.kevin.domain.strategy.model.vo.StrategyDetailBriefVo;

import java.util.List;

/**
* @author wang sheng hui
* @description 针对表【strategy_detail(策略明细)】的数据库操作Service
* @createDate 2023-11-04 16:07:32
*/
public interface StrategyDetailRepository{

    List<StrategyDetailBriefVo> queryStrategyDetailList(Long strategyId);

    List<String> queryNoStockStrategyAwardList(Long strategyId);

    boolean deducStock(Long strategyId, String awardId);
}
