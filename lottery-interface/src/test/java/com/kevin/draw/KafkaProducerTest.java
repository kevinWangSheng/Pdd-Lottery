package com.kevin.draw;

import com.kevin.lottery.application.mq.producer.KafkaProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author wang
 * @create 2023-11-09-20:23
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class KafkaProducerTest {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerTest.class);
    @Resource
    private KafkaProducer kafkaProducer;

    @Test
    public void test_send() throws InterruptedException {
        // 循环发送消息
//        while (true) {
//            kafkaProducer.send("你好，我是Lottery 001");
//            Thread.sleep(3500);
//        }
    }
}
