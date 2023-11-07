package com.kevin.lottery.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kevin.domain.strategy.model.aggregates.StrategyRich;
import com.kevin.domain.award.repository.AwardRepository;
import com.kevin.domain.strategy.model.vo.AwardBriefVo;
import com.kevin.domain.strategy.model.vo.StrategyBriefVo;
import com.kevin.domain.strategy.model.vo.StrategyDetailBriefVo;
import com.kevin.domain.strategy.repository.StrategyDetailRepository;
import com.kevin.domain.strategy.repository.StrategyRepository;
import com.kevin.lottery.infrastructure.dao.AwardMapper;
import com.kevin.lottery.infrastructure.dao.StrategyDetailMapper;
import com.kevin.lottery.infrastructure.dao.StrategyMapper;
import com.kevin.lottery.infrastructure.po.Award;
import com.kevin.lottery.infrastructure.po.Strategy;
import com.kevin.lottery.infrastructure.po.StrategyDetail;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author wang sheng hui
* @description 针对表【strategy(策略配置)】的数据库操作Service实现
* @createDate 2023-11-04 16:07:32
*/
@Component
public class StrategyRepositoryImpl extends ServiceImpl<StrategyMapper, Strategy>
    implements StrategyRepository {
    @Resource
    private StrategyDetailRepository strategyDetailRepository;

    @Resource
    private AwardRepository awardRepository;

    @Resource
    private StrategyDetailMapper strategyDetailMapper;

    @Resource
    private AwardMapper awardMapper;

    @Resource
    private StrategyMapper strategyMapper;



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
        Strategy strategy = strategyMapper.queryStrategy(strategyId);
        if(strategy == null){
            return null;
        }
        List<StrategyDetail> strategyDetails = strategyDetailMapper.queryStrategyDetailList(strategyId);


        StrategyBriefVo strategyBriefVo = new StrategyBriefVo(strategy.getStrategyId(),strategy.getStrategyDesc(),strategy.getStrategyMode(),strategy.getGrantType(),strategy.getGrantDate(),strategy.getExtendInfo());
        List<StrategyDetailBriefVo> strategyDetailBriefVoList = null;
        if(strategyDetails != null) {
            strategyDetailBriefVoList = strategyDetails.stream().map(detail -> new StrategyDetailBriefVo(detail.getStrategyId(), detail.getAwardId(), detail.getAwardName(), detail.getAwardCount(), detail.getAwardSurplusCount(), detail.getAwardRate())).collect(Collectors.toList());
        }

        return new StrategyRich(strategyId,strategyBriefVo,strategyDetailBriefVoList);
    }

    @Override
    public AwardBriefVo queryAward(String awardId) {
        Award award = awardMapper.queryByAwardId(awardId);

        if(award == null){
            return  null;
        }
        AwardBriefVo awardBriefVo = new AwardBriefVo();
        awardBriefVo.setAwardDesc(award.getAwardContent())
                .setAwardName(award.getAwardName())
                .setAwardId(awardId)
                .setAwardType(award.getAwardType())
                .setAwardDesc(award.getAwardContent());
        return awardBriefVo;
    }

    @Override
    public List<String> queryNoStockStrategyAwardList(Long strategyId) {
        // 获取没有库存的奖品id
        return strategyDetailRepository.queryNoStockStrategyAwardList(strategyId);
    }

    @Override
    public boolean deducStock(Long strategyId, String awardId) {
//        return strategyDetailRepository.deducStock(strategyId,awardId);
        StrategyDetail strategyDetail = new StrategyDetail();
        strategyDetail.setStrategyId(strategyId);
        strategyDetail.setAwardId(awardId);
        // 扣减库存
        int count = strategyDetailMapper.deducStock(strategyDetail);
        return count == 1;
    }
}




