package com.kevin.domain.strategy.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.kevin.domain.strategy.repository.StrategyDetailRepository;
import com.kevin.lottery.infrastructure.dao.StrategyDetailMapper;
import com.kevin.lottery.infrastructure.po.StrategyDetail;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author wang sheng hui
* @description 针对表【strategy_detail(策略明细)】的数据库操作Service实现
* @createDate 2023-11-04 16:07:32
*/
@Service
public class StrategyDetailRepositoryImpl extends ServiceImpl<StrategyDetailMapper, StrategyDetail>
    implements StrategyDetailRepository {

    @Override
    public List<StrategyDetail> queryStrategyDetailList(Long strategyId) {
        if(strategyId == null){
            return new ArrayList<>();
        }
        QueryWrapper<StrategyDetail> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(StrategyDetail::getStrategyId,strategyId);
        return list(wrapper);
    }
}




