package com.kevin.draw;

import com.kevin.lottery.application.process.IActivityProcess;
import com.kevin.lottery.application.process.req.DrawProcessReq;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author wang
 * @create 2023-11-11-22:31
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ProcessTest {

    @Resource
    private IActivityProcess activityProcess;

    @Test
    public void test() {
        activityProcess.doDrawProcess(new DrawProcessReq("wanglaowu",100001l));

    }
}
