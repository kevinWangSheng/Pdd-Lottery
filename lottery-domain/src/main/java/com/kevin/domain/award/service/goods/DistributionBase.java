package com.kevin.domain.award.service.goods;

import com.kevin.common.Constance;
import com.kevin.domain.award.model.resp.MqStateResp;
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

    public MqStateResp updateUserAwardState(String userId, String awardId, Long orderId, Integer grantState){
        logger.info("开发发奖，更新发奖状态 uId：{}", userId);
        Integer mqState = getMqState(userId, awardId, orderId);
        // 如果该状态已经更新过了，就不需要再次进行消息更改了
        if(Constance.MQState.COMPLETE.getCode().equals(mqState)){
            return new MqStateResp(Constance.MQState.ALREADY_COMPLETE.getCode()); // 如果MQ状态为完成，则不再更新发奖状态，避免重复消费消息，导致MQ状态不更新。
        }
        // 更新发奖状态
        awardRepository.updateUserAwardState(userId,orderId,awardId,grantState);
        return new MqStateResp(Constance.MQState.COMPLETE.getCode());
    }

    public Integer getMqState(String userId,String awardId,Long orderId){

        Integer mqState = awardRepository.getMqState(userId, awardId, orderId);
        logger.info("开发发奖，先获取mq的状态，防止消息重复消费，获取MQ状态 uId：{},对应的mq状态为:mqState:{}", userId,mqState);
        return mqState;
    }
}
