package com.kevin.draw;

import com.alibaba.fastjson.JSON;
import com.kevin.domain.activity.model.req.PartakeReq;
import com.kevin.domain.activity.model.resp.PartakeResult;
import com.kevin.domain.activity.service.partake.IActivityPartake;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author wang
 * @create 2023-11-07-15:31
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionTest {
    private final Logger logger = LoggerFactory.getLogger(TransactionTest.class);

    @Resource
    private IActivityPartake activityPartake;
    @Test
    public void test_activityPartake() {
        PartakeReq req = new PartakeReq("Uhdgkw766120d", 100001L);
        req.setPartakeDate(new Date());
        PartakeResult res = activityPartake.doPartake(req);
        logger.info("请求参数：{}", JSON.toJSONString(req));
        logger.info("测试结果：{}", JSON.toJSONString(res));
    }

}
