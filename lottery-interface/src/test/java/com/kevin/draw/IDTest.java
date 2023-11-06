package com.kevin.draw;

import com.kevin.common.Constance;
import com.kevin.domain.support.ids.IDContext;
import com.kevin.domain.support.ids.IIDGenerate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author wang
 * @create 2023-11-06-14:56
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class IDTest {

    private final Logger logger = LoggerFactory.getLogger(IDTest.class);
    @Resource
    private Map<Constance.Ids, IIDGenerate> idGenerateMap;
    @Test
    public void test(){
        logger.info("the snow id is :{}",idGenerateMap.get(Constance.Ids.SnowFlake).nextId());
        logger.info("the short id is :{}",idGenerateMap.get(Constance.Ids.ShortCode).nextId());
        logger.info("the random numics id is :{}",idGenerateMap.get(Constance.Ids.RandomNumeric).nextId());
    }
}
