package com.kevin.draw;

import com.kevin.domain.strategy.model.req.DrawReq;
import com.kevin.domain.strategy.service.draw.IDrawExec;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author wang
 * @create 2023-2023-04-20:11
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DrawAlgorithmTest {


    @Resource
    private IDrawExec drawExec;

    @Test
    public void algoTest(){
        drawExec.doDrawExec(new DrawReq("小傅哥", 10001L));
        drawExec.doDrawExec(new DrawReq("小佳佳", 10001L));
        drawExec.doDrawExec(new DrawReq("小蜗牛", 10001L));
        drawExec.doDrawExec(new DrawReq("八杯水", 10001L));

    }
}
