package com.kevin.draw;

import com.kevin.domain.strategy.model.req.DrawReq;
import com.kevin.domain.strategy.service.draw.IDrawExec;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author wang
 * @create 2023-2023-04-20:11
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DrawAlgorithmTest {


    @Resource
    private IDrawExec drawExec;

    @Test
    public void algoTest(){
        drawExec.doDrawExec(new DrawReq("1",1l));
    }
}
