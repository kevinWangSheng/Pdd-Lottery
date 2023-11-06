package com.kevin.draw;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.ddl.history.IDdlGenerator;
import com.kevin.common.Constance;
import com.kevin.domain.support.ids.IDContext;
import com.kevin.domain.support.ids.IIDGenerate;
import com.kevin.lottery.infrastructure.dao.UserStrategyExportMapper;
import com.kevin.lottery.infrastructure.dao.UserTakeActivityMapper;
import com.kevin.lottery.infrastructure.po.UserStrategyExport;
import com.kevin.lottery.infrastructure.po.UserTakeActivity;
import org.apache.dubbo.common.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @author wang
 * @create 2023-11-06-21:49
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DbTest {
    private final Logger logger = LoggerFactory.getLogger(DbTest.class);

    @Resource
    private UserTakeActivityMapper userTakeActivityMapper;

    @Resource
    private Map<Constance.Ids, IIDGenerate> idGenerateMap;

    @Resource
    private UserStrategyExportMapper strategyExportMapper;
    @Test
    public void activityTest(){
//        UserTakeActivity activity = new UserTakeActivity(idGenerateMap.get(Constance.Ids.RandomNumeric).nextId(),"Umdgkw766120d",121019889411L,100002L,"测试活动",new Date(),10,"Uhdgkw766120d");
//        userTakeActivityMapper.insertOne(activity);
        logger.info("the result is :{}",userTakeActivityMapper.selectOne("Umdgkw766120d"));
    }

    @Test
    public void strategyTest(){
//        UserStrategyExport userStrategyExport = new UserStrategyExport();
//        userStrategyExport.setId(idGenerateMap.get(Constance.Ids.RandomNumeric).nextId());
//        userStrategyExport.setActivityid(idGenerateMap.get(Constance.Ids.ShortCode).nextId());
//        userStrategyExport.setAwardid(idGenerateMap.get(Constance.Ids.ShortCode).nextId());
//        userStrategyExport.setOrderid(idGenerateMap.get(Constance.Ids.RandomNumeric).nextId());
//        userStrategyExport.setAwardtype(Constance.AwardType.Coupon.getCode());
//        userStrategyExport.setGranttype(1);
//        userStrategyExport.setAwardcontent("奖品描述");
//        userStrategyExport.setUuid(String.valueOf(userStrategyExport.getOrderid()));
//        userStrategyExport.setAwardname("IMac");
//        userStrategyExport.setGrantdate(new Date());
//        userStrategyExport.setStrategytype(Constance.StrategyMode.SINGLE.getCode());
//        userStrategyExport.setUid("Uhdgkw766120d");
//
//        userStrategyExport.setAwardname("测试活动");
//
//        strategyExportMapper.insertSelective(userStrategyExport);
        logger.info("split table and get one by uid is :{}", JSONUtil.toJsonStr(strategyExportMapper.selectByUserId("Uhdgkw766120d")));
    }
}
