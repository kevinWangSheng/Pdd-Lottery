package com.kevin.domain.award.service.goods;

import com.kevin.domain.award.repository.AwardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @author wang
 * @create 2023-2023-05-15:47
 */
public class DistributionBase {
    protected final Logger logger = LoggerFactory.getLogger(DistributionBase.class);
    @Resource
    private AwardRepository awardRepository;

    public void updateUserAwardState(String userId, String awardId, Integer awardState,String orderId,String awardStateInfo){
        // TODO 后期添加更新分库分表中，用户个人的抽奖记录表中奖品发奖状态
        logger.info("TODO 后期添加更新分库分表中，用户个人的抽奖记录表中奖品发奖状态 uId：{}", userId);

    }
}
