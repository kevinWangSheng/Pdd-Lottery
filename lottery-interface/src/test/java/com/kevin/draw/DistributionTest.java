package com.kevin.draw;

import cn.hutool.json.JSONUtil;
import com.kevin.common.Constance;
import com.kevin.domain.award.model.req.GoodReq;
import com.kevin.domain.award.model.resp.DistributionRes;
import com.kevin.domain.award.service.goods.DistributionGoods;
import com.kevin.domain.award.service.goods.factory.DistributionGoodsFactory;
import com.kevin.domain.strategy.model.req.DrawReq;
import com.kevin.domain.strategy.model.resp.DrawResp;
import com.kevin.domain.strategy.model.vo.DrawAwardInfo;
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
 * @create 2023-2023-05-16:49
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DistributionTest {
    @Resource
    private IDrawExec drawExec;

    @Resource
    private DistributionGoodsFactory distributionGoodsFactory;

    private final Logger logger = LoggerFactory.getLogger(DistributionTest.class);
    @Test
    public void test() {
        DrawResp drawResp = drawExec.doDrawExec(new DrawReq("wanglaowu", 10001l));

        Integer drawState = drawResp.getDrawState();

        if(Constance.AwardState.FAILURE.equals(drawState)) {
            logger.info("抽奖失败");
            return;
        }
        DrawAwardInfo drawAwardInfo = drawResp.getDrawAwardInfo();
        DistributionGoods distributionGoods = distributionGoodsFactory.getDistributionGoods(drawAwardInfo.getAwardType());
        DistributionRes distributionRes = distributionGoods.doDistribution(new GoodReq(drawResp.getUid(), "111111", drawAwardInfo.getAwardId(), drawAwardInfo.getAwardName(), drawAwardInfo.getAwardContent()));
        logger.info("分发结果：{}", JSONUtil.toJsonStr(distributionRes));
    }
}
