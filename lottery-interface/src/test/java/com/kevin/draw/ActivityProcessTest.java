package com.kevin.draw;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
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
 * @create 2023-11-07-20:42
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ActivityProcessTest {
    private final Logger logger = LoggerFactory.getLogger(ActivityProcessTest.class);
    @Resource
    private IActivityProcess activityProcess;

    @Test
    public void test_doDrawProcess() {
        DrawProcessReq req = new DrawProcessReq();
        req.setUId("fustack");
        req.setActivityId(100001L);
        DrawProcessResp drawProcessResult = activityProcess.doDrawProcess(req);

        logger.info("请求入参：{}", JSON.toJSONString(req));
        logger.info("测试结果：{}", JSON.toJSONString(drawProcessResult));
    }

}
