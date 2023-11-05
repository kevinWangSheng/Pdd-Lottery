package com.kevin.domain.strategy.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.kevin.domain.strategy.repository.StrategyDetailRepository;
import com.kevin.lottery.infrastructure.dao.StrategyDetailMapper;
import com.kevin.lottery.infrastructure.po.StrategyDetail;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<String> queryNoStockStrategyAwardList(Long strategyId) {
        if(strategyId == null){
            return new ArrayList<>();
        }
        QueryWrapper<StrategyDetail> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(StrategyDetail::getStrategyId,strategyId).select(StrategyDetail::getAwardId).eq(StrategyDetail::getAwardSurplusCount,0);
        List<StrategyDetail> list = list(wrapper);
        if(list == null){
            return new ArrayList<>();
        }
        return list.stream().map(detail->detail.getAwardId()).collect(Collectors.toList());
    }

    @Override
    public boolean deducStock(Long strategyId, String awardId) {
        if(strategyId == null || awardId == null){
            return false;
        }
        UpdateWrapper<StrategyDetail> wrapper = new UpdateWrapper<>();
        wrapper.setSql("award_surplus_count = award_surplus_count -1");
        wrapper.lambda().eq(StrategyDetail::getStrategyId,strategyId).eq(StrategyDetail::getAwardId,awardId).gt(StrategyDetail::getAwardSurplusCount,0);
        return update(wrapper);
    }
}




