package com.kevin.lottery.ward;

import com.kevin.domain.strategy.model.req.DrawReq;
import com.kevin.domain.strategy.service.draw.IDrawExec;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wang
 * @create 2023-2023-04-20:09
 */
@Service
public class DrawTest {

    @Resource
    private IDrawExec drawExec;

    public void testDraw(DrawReq drawReq){
        drawExec.doDrawExec(drawReq);
    }
}
