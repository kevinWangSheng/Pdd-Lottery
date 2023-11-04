package com.kevin.draw;

import com.kevin.domain.strategy.model.req.DrawReq;
import com.kevin.domain.strategy.service.draw.IDrawExec;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author wang
 * @create 2023-2023-04-19:36
 */
@SpringBootTest

public class DrawAlgoTest {
    @Resource
    IDrawExec drawExec;
    @Test
    public void test() {
        drawExec.doDrawExec(new DrawReq("1",1l));
    }
}
