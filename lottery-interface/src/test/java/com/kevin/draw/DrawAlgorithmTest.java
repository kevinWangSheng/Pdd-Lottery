package com.kevin.draw;

import cn.hutool.json.JSONUtil;
import com.kevin.common.Constance;
import com.kevin.domain.award.model.req.GoodReq;
import com.kevin.domain.award.model.resp.DistributionRes;
import com.kevin.domain.award.service.goods.DistributionGoods;
import com.kevin.domain.award.service.goods.factory.DistributionGoodsFactory;
import com.kevin.domain.strategy.model.req.DrawReq;
import com.kevin.domain.strategy.model.resp.DrawResp;
import com.kevin.domain.strategy.model.vo.DrawAwardVO;
import com.kevin.domain.strategy.service.draw.IDrawExec;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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



    @Resource
    private DistributionGoodsFactory distributionGoodsFactory;

    private final Logger logger = LoggerFactory.getLogger(DistributionTest.class);
    @Test
    public void Drawtest() {
        DrawResp drawResp = drawExec.doDrawExec(new DrawReq("wanglaowu", 10001l));

        Integer drawState = drawResp.getDrawState();

        if(Constance.AwardState.FAILURE.equals(drawState)) {
            logger.info("抽奖失败");
            return;
        }
        DrawAwardVO drawAwardVO = drawResp.getDrawAwardVO();
        DistributionGoods distributionGoods = distributionGoodsFactory.getDistributionGoods(drawAwardVO.getAwardType());
        DistributionRes distributionRes = distributionGoods.doDistribution(new GoodReq(drawResp.getUid(), "111111", drawAwardVO.getAwardId(), drawAwardVO.getAwardName(), drawAwardVO.getAwardContent()));
        logger.info("分发结果：{}", JSONUtil.toJsonStr(distributionRes));
    }
}
