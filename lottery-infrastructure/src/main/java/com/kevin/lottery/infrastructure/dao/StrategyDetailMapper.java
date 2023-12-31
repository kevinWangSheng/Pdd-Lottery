package com.kevin.lottery.infrastructure.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kevin.lottery.infrastructure.po.StrategyDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author wang sheng hui
* @description 针对表【strategy_detail(策略明细)】的数据库操作Mapper
* @createDate 2023-11-04 16:07:32
* @Entity generator.domain.StrategyDetail
*/
@Mapper
public interface StrategyDetailMapper extends BaseMapper<StrategyDetail> {

    int deducStock(StrategyDetail strategyDetail);

    List<StrategyDetail> queryStrategyDetailList(@Param("strategyId")Long strategyId);

    void insertBatch(List<StrategyDetail> list);
}




