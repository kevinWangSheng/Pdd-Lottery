package com.kevin.domain.activity.service.deploy.impl;

import cn.bugstack.middleware.db.router.strategy.IDBRouterStrategy;
import cn.hutool.json.JSONUtil;
import com.kevin.domain.activity.model.aggregates.ActivityConfigRich;
import com.kevin.domain.activity.model.req.ActivityConfigReq;
import com.kevin.domain.activity.model.vo.*;
import com.kevin.domain.activity.reporisitory.IActivityRepository;
import com.kevin.domain.activity.service.deploy.IActivityDeploy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wang
 * @create 2023-2023-06-0:54
 */
@Service
public class ActivityDeployImpl implements IActivityDeploy {
    private final Logger logger = LoggerFactory.getLogger(ActivityDeployImpl.class);

    @Resource
    private IActivityRepository activityRepository;

    @Resource
    private IDBRouterStrategy dbRouter;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addActivity(ActivityConfigReq req) {
        logger.info("活动配置开始，活动配置信息：{}",req);
        if(req == null){
            logger.error("活动配置失败，传入的活动配置参数为空");
        }

        try {
            ActivityConfigRich activityConfigRich = req.getActivityConfigRich();
            // 添加活动信息
            AcitivityVo acitivityVo = activityConfigRich.getAcitivityVo();
            activityRepository.addAcitivity(acitivityVo);
            //添加奖品列表
            List<AwardVo> awardVoList = req.getActivityConfigRich().getAwardVoList();
            activityRepository.addAward(awardVoList);
            // 添加策略信息
            StrategyVo strategyVo = activityConfigRich.getStrategyVo();
            activityRepository.addStrategy(strategyVo);
            // 添加策略明细列表
            List<StrategyDetailVo> detailVoList = strategyVo.getDetailVoList();
            activityRepository.addStrategyDetailList(detailVoList);

            logger.info("活动配置成功,activityId:{}",req.getAcitivityId());
        } catch (Exception e) {
            logger.error("活动配置失败,唯一索引冲突 activityId：{} reqJson：{}",req.getAcitivityId(), JSONUtil.toJsonStr(req));
            throw e;
        }

    }

    @Override
    public void updateActivity(ActivityConfigReq req) {
        // TODO 非核心功能后续补充
    }

    @Override
    public List<AcitivityVo> scanToDoActivityList(Long id) {

        return activityRepository.scanToDoActivityList(id);
    }

    @Override
    public List<InvoiceVO> scanInvoiceMqState(int dbIndex, int tbIndex) {
        try {
            dbRouter.setDBKey(dbIndex);
            dbRouter.setTBKey(tbIndex);

            //查询数据
            return activityRepository.scanInvoiceMqState();
        } finally {
            dbRouter.clear();
        }
    }
}
