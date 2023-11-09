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

    public void updateUserAwardState(String userId, String awardId, Long orderId,Integer grantState){
        logger.info("开发发奖，更新发奖状态 uId：{}", userId);
        awardRepository.updateUserAwardState(userId,orderId,awardId,grantState);


    }
}
