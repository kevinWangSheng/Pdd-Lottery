package com.kevin.draw;

import com.alibaba.fastjson.JSON;
import com.kevin.lottery.rpc.ILotteryActivityBooth;
import com.kevin.lottery.rpc.req.DrawReq;
import com.kevin.lottery.rpc.req.QuantificationDrawReq;
import com.kevin.lottery.rpc.resp.DrawResp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author wang
 * @create 2023-11-09-15:06
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MappingTest {

    private final Logger logger = LoggerFactory.getLogger(MappingTest.class);
    @Resource
    private ILotteryActivityBooth lotteryActivityBooth;
    @Test
    public void test_doDraw() {
        DrawReq drawReq = new DrawReq();
        drawReq.setuId("fustack");
        drawReq.setActivityId(100001L);
        DrawResp drawRes = lotteryActivityBooth.doDraw(drawReq);
        logger.info("请求参数：{}", JSON.toJSONString(drawReq));
        logger.info("测试结果：{}", JSON.toJSONString(drawRes));
    }

    @Test
    public void test_doQuantificationDraw() {
        QuantificationDrawReq req = new QuantificationDrawReq();
        req.setuId("fustack");
        req.setTreeId(2110081902L);
        req.setValMap(new HashMap<String, Object>() {{
            put("gender", "man");
            put("age", "18");
        }});

        DrawResp drawRes = lotteryActivityBooth.doQuantificationDraw(req);
        logger.info("请求参数：{}", JSON.toJSONString(req));
        logger.info("测试结果：{}", JSON.toJSONString(drawRes));

    }
}
