package com.kevin.domain.strategy.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kevin.domain.strategy.model.aggregates.StrategyRich;
import com.kevin.domain.strategy.repository.AwardRepository;
import com.kevin.domain.strategy.repository.StrategyDetailRepository;
import com.kevin.domain.strategy.repository.StrategyRepository;
import com.kevin.lottery.infrastructure.dao.StrategyMapper;
import com.kevin.lottery.infrastructure.po.Award;
import com.kevin.lottery.infrastructure.po.Strategy;
import com.kevin.lottery.infrastructure.po.StrategyDetail;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author wang sheng hui
* @description 针对表【strategy(策略配置)】的数据库操作Service实现
* @createDate 2023-11-04 16:07:32
*/
@Service
public class StrategyRepositoryImpl extends ServiceImpl<StrategyMapper, Strategy>
    implements StrategyRepository {
    @Resource
    private StrategyDetailRepository strategyDetailRepository;

    @Resource
    private AwardRepository awardRepository;

    /**
     * 根据策略id查询抽奖策略，以及策略细节
     * @param strategyId
     * @return
     */
    @Override
    public StrategyRich queryStrategyRich(Long strategyId) {
        if(strategyId == null){
            return null;
        }
        QueryWrapper<Strategy> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Strategy::getStrategyId,strategyId);
        Strategy strategy = getOne(wrapper);
        List<StrategyDetail> strategyDetails = strategyDetailRepository.queryStrategyDetailList(strategyId);

        return new StrategyRich(strategyId,strategy,strategyDetails);
    }

    @Override
    public Award queryAward(String awardId) {
        return awardRepository.getByAwardId(awardId);
    }
}




