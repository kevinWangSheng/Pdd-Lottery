package com.kevin.draw;

import com.alibaba.fastjson.JSON;
import com.kevin.domain.rule.model.req.DecisionMatterReq;
import com.kevin.domain.rule.model.rsep.EngineResult;
import com.kevin.domain.rule.service.engine.EngineFilter;
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
 * @create 2023-11-08-16:18
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RuleTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Resource
    private EngineFilter engineFilter;
    @Test
    public void test_process() {
        DecisionMatterReq req = new DecisionMatterReq();
        req.setTreeId(2110081902L);
        req.setUserId("fustack");
        req.setValueMap(new HashMap<String, Object>() {{
            put("gender", "man");
            put("age", "25");
        }});

        EngineResult res = engineFilter.process(req);

        logger.info("请求参数：{}", JSON.toJSONString(req));
        logger.info("测试结果：{}", JSON.toJSONString(res));
    }
}
