package com.kevin.domain.strategy.repository;


import com.baomidou.mybatisplus.extension.service.IService;
import com.kevin.lottery.infrastructure.po.StrategyDetail;

import java.util.List;

/**
* @author wang sheng hui
* @description 针对表【strategy_detail(策略明细)】的数据库操作Service
* @createDate 2023-11-04 16:07:32
*/
public interface StrategyDetailRepository extends IService<StrategyDetail> {

    List<StrategyDetail> queryStrategyDetailList(Long strategyId);
}
