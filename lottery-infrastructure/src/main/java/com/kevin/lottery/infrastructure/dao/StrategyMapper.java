package com.kevin.lottery.infrastructure.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kevin.lottery.infrastructure.po.Strategy;
import org.apache.ibatis.annotations.Mapper;

/**
* @author wang sheng hui
* @description 针对表【strategy(策略配置)】的数据库操作Mapper
* @createDate 2023-11-04 16:07:32
* @Entity generator.domain.Strategy
*/
@Mapper
public interface StrategyMapper extends BaseMapper<Strategy> {

    Strategy queryStrategy(Long strategyId);
}




