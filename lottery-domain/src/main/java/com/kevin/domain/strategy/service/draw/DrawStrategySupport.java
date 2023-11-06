package com.kevin.domain.strategy.service.draw;

import com.kevin.domain.strategy.model.aggregates.StrategyRich;
import com.kevin.domain.strategy.model.vo.AwardBriefVo;
import com.kevin.domain.strategy.repository.StrategyRepository;


import javax.annotation.Resource;

/**抽奖活动支持,提供给抽奖活动使用，这里使用的是一个模板模式，需要定义清楚每一个类具体是什么功能。
 * @author wang
 * @create 2023-2023-05-0:44
 */
public class DrawStrategySupport extends DrawConfig{
    @Resource
    protected StrategyRepository strategyRepository;
    public StrategyRich queryStrategyByStrategyId(Long strategyId) {
        return strategyRepository.queryStrategyRich(strategyId);
    }

    public AwardBriefVo queryAward(String awardId) {
        return strategyRepository.queryAward(awardId);
    }
}
