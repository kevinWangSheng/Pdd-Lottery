package com.kevin.lottery.application.mq.producer;

import cn.hutool.json.JSONUtil;
import com.kevin.common.Constance;
import com.kevin.domain.activity.model.vo.ActivityPartakeRecordVO;
import com.kevin.domain.activity.model.vo.InvoiceVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;

/**
 * @author wang
 * @create 2023-11-09-16:47
 */
@Component
public class KafkaProducer {

    private final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Resource
    private KafkaTemplate<String,Object> kafkaTemplate;

    public ListenableFuture<SendResult<String, Object>> sendLotteryInvoice(InvoiceVO invoiceVO){
        String objJson = JSONUtil.toJsonStr(invoiceVO);
        logger.info("开始发送消息：{}",objJson);
        return kafkaTemplate.send(Constance.KAFKA.LOTTY_INVOICE, objJson);
    }

    public ListenableFuture<SendResult<String, Object>> sendLotteryActivityPartakeRecord(ActivityPartakeRecordVO partakeRecordVO){
        String jsonStr = JSONUtil.toJsonStr(partakeRecordVO);
        logger.info("发送MQ消息(领取活动记录) topic:{},bizId:{}, message:{}",Constance.KAFKA.TOPIC_ACTIVITY_PARTAKE,partakeRecordVO.getuId(),jsonStr);
        return kafkaTemplate.send(Constance.KAFKA.TOPIC_ACTIVITY_PARTAKE, jsonStr);
    }
}
