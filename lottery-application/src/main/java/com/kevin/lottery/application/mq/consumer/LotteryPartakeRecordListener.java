package com.kevin.lottery.application.mq.consumer;

import cn.hutool.json.JSONUtil;
import com.kevin.common.Constance;
import com.kevin.domain.activity.model.vo.ActivityPartakeRecordVO;
import com.kevin.domain.activity.service.partake.IActivityPartake;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

/** 抽奖活动领取记录监听消息
 * @author wang
 * @create 2023-11-11-20:50
 */
@Component
public class LotteryPartakeRecordListener {

    private final Logger logger = LoggerFactory.getLogger(LotteryPartakeRecordListener.class);

    @Resource
    private IActivityPartake activityPartake;

    @KafkaListener(topics = Constance.KAFKA.TOPIC_ACTIVITY_PARTAKE, groupId = Constance.KAFKA.LOTTERY_CONSUMER_GROUP)
    public void onMessage(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){
        Optional<?> message = Optional.ofNullable(record.value());

        if(message.isPresent()){
            // 进行对象转化
            logger.info("消费MQ消息，异步扣减活动库存 message：{}", message.get());
            ActivityPartakeRecordVO recordVO = JSONUtil.toBean((String) message.get(), ActivityPartakeRecordVO.class);
            // 3. 更新数据库库存【实际场景业务体量较大，可能也会由于MQ消费引起并发，对数据库产生压力，所以如果并发量较大，可以把库存记录缓存中，
            // 并使用定时任务进行处理缓存和数据库库存同步，减少对数据库的操作次数】
            activityPartake.updateActivityStock(recordVO);
        }
    }
}
