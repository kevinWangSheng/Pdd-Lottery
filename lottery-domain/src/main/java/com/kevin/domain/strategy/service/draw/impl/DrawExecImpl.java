package com.kevin.domain.strategy.service.draw.impl;

import cn.hutool.json.JSONUtil;
import com.kevin.domain.strategy.service.algorithm.IDrawAlgorithm;
import com.kevin.domain.strategy.service.draw.AbstractBaseDraw;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wang
 * @create 2023-2023-04-18:47
 */
@Service("drawExec")
public class DrawExecImpl extends AbstractBaseDraw {
    private Logger logger = LoggerFactory.getLogger(DrawExecImpl.class);

    @Override
    protected String drawAlgorithm(Long strategyId, IDrawAlgorithm drawAlgorithm, List<String> exculdStrategyDetails) {
        String awardId = drawAlgorithm.randomDraw(strategyId, exculdStrategyDetails);
        //没有抽到奖品
        if(StringUtils.isBlank(awardId)){
            return null;
        }
        /*
         * 扣减库存，暂时采用数据库行级锁的方式进行扣减库存，后续优化为 Redis 分布式锁扣减 decr/incr
         * 注意：通常数据库直接锁行记录的方式并不能支撑较大体量的并发，但此种方式需要了解，因为在分库分表下的正常数据流量下的个人数据记录中，是可以使用行级锁的，因为他只影响到自己的记录，不会影响到其他人
         */
        boolean isSuccess = strategyRepository.deducStock(strategyId, awardId);
        // 返回结果，库存扣减成功返回奖品ID，否则返回NULL 「在实际的业务场景中，如果中奖奖品库存为空，则会发送兜底奖品，比如各类券」
        return isSuccess ? awardId : null;
    }

    @Override
    protected List<String> queryExeculdAwardIds(Long strategyId) {

        List<String> exculdAwardIdList = strategyRepository.queryNoStockStrategyAwardList(strategyId);
        logger.info("执行抽奖策略 strategyId：{}，无库存排除奖品列表ID集合 awardList：{}",strategyId, JSONUtil.toJsonStr(exculdAwardIdList));
        return exculdAwardIdList;
    }
}
