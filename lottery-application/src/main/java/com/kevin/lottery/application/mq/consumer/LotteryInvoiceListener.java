package com.kevin.lottery.application.mq.consumer;

import cn.hutool.json.JSONUtil;
import com.kevin.common.Constance;
import com.kevin.domain.activity.model.vo.InvoiceVO;
import com.kevin.domain.award.model.req.GoodReq;
import com.kevin.domain.award.model.resp.DistributionRes;
import com.kevin.domain.award.service.goods.DistributionGoods;
import com.kevin.domain.award.service.goods.factory.DistributionGoodsFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author wang
 * @create 2023-11-09-16:47
 */
@Component
public class LotteryInvoiceListener {


    private final Logger logger = LoggerFactory.getLogger(LotteryInvoiceListener.class);

    @Resource
    private DistributionGoodsFactory distributionGoodsFactory;


    @KafkaListener(topics = Constance.KAFKA.LOTTY_INVOICE,groupId = Constance.KAFKA.LOTTERY_CONSUMER_GROUP)
    public void testTopic(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){
        Optional<?> message = Optional.ofNullable(record.value());

        try {
            if(message.isPresent()){
                // 进行消息的对象转化
                InvoiceVO invoiceVO = JSONUtil.toBean((String) message.get(), InvoiceVO.class);
                // 从工厂中获取对应 配送类
                DistributionGoods distributionGoods = distributionGoodsFactory.getDistributionGoods(invoiceVO.getAwardType());
                DistributionRes distributionRes = distributionGoods.doDistribution(new GoodReq(invoiceVO.getuId(), invoiceVO.getOrderId(), invoiceVO.getAwardId(), invoiceVO.getAwardName(), invoiceVO.getAwardContent()));
                //检查消息是否重复发送
                if(Constance.AwardState.DUPLICATE.getCode().equals(distributionRes.getCode())){
                    logger.info("消息重复发送了：topic{},message:{},uid:{}",topic,message.get(),invoiceVO.getuId());
                    //对消息进行确认
                    ack.acknowledge();
                    return;
                }
                // 检验配送结果
                Assert.isTrue(Constance.AwardState.SUCCESS.getCode() == distributionRes.getCode(),distributionRes.getInfo());
                //打印日志
                logger.info("消费MQ消息，完成topic：{}，bizId:{}, 发奖结果:{}",topic,invoiceVO.getuId(),JSONUtil.toJsonStr(distributionRes));
                //进行确认
                ack.acknowledge();

            }
        } catch (Exception e) {
            logger.error("消费MQ消息失败：topic:{}, message:{}",topic,message.get());
            logger.error(e.getMessage());
        }

    }

}
