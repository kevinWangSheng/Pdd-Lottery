package com.kevin.draw;

import com.alibaba.fastjson.JSON;
import com.kevin.common.Constance;
import com.kevin.domain.activity.model.aggregates.ActivityConfigRich;
import com.kevin.domain.activity.model.req.ActivityConfigReq;
import com.kevin.domain.activity.model.vo.AcitivityVo;
import com.kevin.domain.activity.model.vo.AwardVo;
import com.kevin.domain.activity.model.vo.StrategyDetailVo;
import com.kevin.domain.activity.model.vo.StrategyVo;
import com.kevin.domain.activity.service.deploy.IActivityDeploy;
import com.kevin.domain.activity.service.stateflow.IStateHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wang
 * @create 2023-2023-06-10:03
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ActivityTest {
    private final Logger logger = LoggerFactory.getLogger(ActivityTest.class);
    @Resource
    private IActivityDeploy activityDeploy;

    @Resource
    private IStateHandler stateHandler;


    private ActivityConfigRich activityConfigRich;

    private Long activityId = 120981321L;
    @Before
    public void init() {
        AcitivityVo activity = new AcitivityVo();
        activity.setAcitivityId(activityId);
        activity.setActivityName("测试活动");
        activity.setActivityDesc("测试活动描述");
        activity.setBeginTime(new Date());
        activity.setEndTime(new Date());
        activity.setStockCount(100);
        activity.setTakeCount(10);
        activity.setState(Constance.ActivityState.EDIT.getCode());
        activity.setCreator("xiaofuge");

        StrategyVo strategy = new StrategyVo();
        strategy.setStrategyId(10002L);
        strategy.setStrategyDesc("抽奖策略");
        strategy.setStrategyMode(Constance.StrategyMode.SINGLE.getCode());
        strategy.setGrantType(1);
        strategy.setGrantDate(new Date());
        strategy.setExtendInfo("");

        StrategyDetailVo strategyDetail_01 = new StrategyDetailVo();
        strategyDetail_01.setStrategyId(strategy.getStrategyId());
        strategyDetail_01.setAwardId("101");
        strategyDetail_01.setAwardName("一等奖");
        strategyDetail_01.setAwardCount(10);
        strategyDetail_01.setAwardSurplusCount(10);
        strategyDetail_01.setAwardRate(new BigDecimal("0.05"));

        StrategyDetailVo strategyDetail_02 = new StrategyDetailVo();
        strategyDetail_02.setStrategyId(strategy.getStrategyId());
        strategyDetail_02.setAwardId("102");
        strategyDetail_02.setAwardName("二等奖");
        strategyDetail_02.setAwardCount(20);
        strategyDetail_02.setAwardSurplusCount(20);
        strategyDetail_02.setAwardRate(new BigDecimal("0.15"));

        StrategyDetailVo strategyDetail_03 = new StrategyDetailVo();
        strategyDetail_03.setStrategyId(strategy.getStrategyId());
        strategyDetail_03.setAwardId("103");
        strategyDetail_03.setAwardName("三等奖");
        strategyDetail_03.setAwardCount(50);
        strategyDetail_03.setAwardSurplusCount(50);
        strategyDetail_03.setAwardRate(new BigDecimal("0.20"));

        StrategyDetailVo strategyDetail_04 = new StrategyDetailVo();
        strategyDetail_04.setStrategyId(strategy.getStrategyId());
        strategyDetail_04.setAwardId("104");
        strategyDetail_04.setAwardName("四等奖");
        strategyDetail_04.setAwardCount(100);
        strategyDetail_04.setAwardSurplusCount(100);
        strategyDetail_04.setAwardRate(new BigDecimal("0.25"));

        StrategyDetailVo strategyDetail_05 = new StrategyDetailVo();
        strategyDetail_05.setStrategyId(strategy.getStrategyId());
        strategyDetail_05.setAwardId("104");
        strategyDetail_05.setAwardName("五等奖");
        strategyDetail_05.setAwardCount(500);
        strategyDetail_05.setAwardSurplusCount(500);
        strategyDetail_05.setAwardRate(new BigDecimal("0.35"));


        List<StrategyDetailVo> strategyDetailList = new ArrayList<>();
        strategyDetailList.add(strategyDetail_01);
        strategyDetailList.add(strategyDetail_02);
        strategyDetailList.add(strategyDetail_03);
        strategyDetailList.add(strategyDetail_04);
        strategyDetailList.add(strategyDetail_05);

        strategy.setDetailVoList(strategyDetailList);

        AwardVo award_01 = new AwardVo();
        award_01.setAwardId("101");
        award_01.setAwardType(Constance.AwardType.Desc.getCode());
        award_01.setAwardName("电脑");
        award_01.setAwardContent("请联系活动组织者 fustack");

        AwardVo award_02 = new AwardVo();
        award_02.setAwardId("102");
        award_02.setAwardType(Constance.AwardType.Desc.getCode());
        award_02.setAwardName("手机");
        award_02.setAwardContent("请联系活动组织者 fustack");

        AwardVo award_03 = new AwardVo();
        award_03.setAwardId("103");
        award_03.setAwardType(Constance.AwardType.Desc.getCode());
        award_03.setAwardName("平板");
        award_03.setAwardContent("请联系活动组织者 fustack");

        AwardVo award_04 = new AwardVo();
        award_04.setAwardId("104");
        award_04.setAwardType(Constance.AwardType.Desc.getCode());
        award_04.setAwardName("耳机");
        award_04.setAwardContent("请联系活动组织者 fustack");

        AwardVo award_05 = new AwardVo();
        award_05.setAwardId("105");
        award_05.setAwardType(Constance.AwardType.Desc.getCode());
        award_05.setAwardName("数据线");
        award_05.setAwardContent("请联系活动组织者 fustack");

        List<AwardVo> awardList = new ArrayList<>();
        awardList.add(award_01);
        awardList.add(award_02);
        awardList.add(award_03);
        awardList.add(award_04);
        awardList.add(award_05);

        activityConfigRich = new ActivityConfigRich(activity, strategy, awardList);
    }
    @Test
    public void test_createAcitivity(){
        activityDeploy.addActivity(new ActivityConfigReq(activityId,activityConfigRich));
    }

    @Test
    public void test_alterState(){
        logger.info("提交审核，测试：{}", JSON.toJSONString(stateHandler.arraignment(100001L, Constance.ActivityState.EDIT)));
        logger.info("审核通过，测试：{}", JSON.toJSONString(stateHandler.checkPass(100001L, Constance.ActivityState.ARRAIGNMENT)));
        logger.info("运行活动，测试：{}", JSON.toJSONString(stateHandler.doing(100001L, Constance.ActivityState.PASS)));
        logger.info("二次提审，测试：{}", JSON.toJSONString(stateHandler.checkPass(100001L, Constance.ActivityState.EDIT)));
    }
}
