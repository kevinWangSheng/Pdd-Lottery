package com.kevin.draw;

import cn.hutool.json.JSONUtil;
import com.kevin.common.Constance;
import com.kevin.domain.activity.model.vo.InvoiceVO;
import com.kevin.lottery.application.mq.producer.KafkaProducer;
import com.kevin.lottery.application.process.IActivityProcess;
import com.kevin.lottery.application.process.req.DrawProcessReq;
import com.kevin.lottery.application.process.resp.DrawProcessResp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


/**
 * @author wang
 * @create 2023-11-09-23:58
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AddMqSendTest {

    private Logger logger = LoggerFactory.getLogger(AddMqSendTest.class);
    @Resource
    private IActivityProcess activityProcess;

    @Resource
    private KafkaProducer kafkaProducer;

    @Test
    public void test_send() throws InterruptedException {
        InvoiceVO invoice = new InvoiceVO();
        invoice.setuId("fustack");
        invoice.setOrderId(1444540456057864192L);
        invoice.setAwardId("3");
        invoice.setAwardType(Constance.AwardType.Desc.getCode());
        invoice.setAwardName("Code");
        invoice.setAwardContent("苹果电脑");
        invoice.setShippingAddress(null);
        invoice.setExtInfo(null);
        kafkaProducer.send(invoice);

        while (true){
            Thread.sleep(10000);
        }
    }

    @Test
    public void test_doDrawProcess() {
        DrawProcessReq req = new DrawProcessReq();
        req.setUId("wanglaowu");
        req.setActivityId(100001L);
        DrawProcessResp drawProcessResult = activityProcess.doDrawProcess(req);
        logger.info("请求入参：{}", JSONUtil.toJsonStr(req));
        logger.info("测试结果：{}", JSONUtil.toJsonStr(drawProcessResult));
        try{ Thread.sleep(100000);} catch(InterruptedException e){ e.printStackTrace();}
    }


}
