package com.kevin.lottery.infrastructure.repository;

import com.kevin.common.Constance;
import com.kevin.domain.activity.model.req.PartakeReq;
import com.kevin.domain.activity.model.vo.*;
import com.kevin.domain.activity.reporisitory.IActivityRepository;
import com.kevin.lottery.infrastructure.dao.*;
import com.kevin.lottery.infrastructure.po.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wang
 * @create 2023-2023-05-21:00
 */
@Component
public class ActivityRepository implements IActivityRepository{

    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private AwardMapper awardMapper;

    @Resource
    private StrategyMapper strategyMapper;

    @Resource
    private StrategyDetailMapper strategyDetailMapper;

    @Resource
    private UserTakeActivityCountMapper userTakeActivityCountMapper;
    @Override
    public void addAcitivity(AcitivityVo acitivityVo) {
        if(acitivityVo == null){
            return;
        }
        Activity activity = new Activity(acitivityVo.getAcitivityId(),acitivityVo.getActivityName(),acitivityVo.getActivityDesc(),
                acitivityVo.getBeginTime(),acitivityVo.getEndTime(),acitivityVo.getStockCount(), acitivityVo.getTakeCount(),
                acitivityVo.getState(),acitivityVo.getCreator());
        activityMapper.insert(activity);
    }

    @Override
    public void addAward(List<AwardVo> awardVos) {
        if(awardVos == null || awardVos.isEmpty()){
            return;
        }
        List<Award> awards = awardVos.stream().map(awardVo -> new Award(awardVo.getAwardId(),awardVo.getAwardType(),awardVo.getAwardName(),awardVo.getAwardContent())).collect(Collectors.toList());
        awardMapper.insertBatch(awards);
    }

    @Override
    public void addStrategy(StrategyVo strategyVo) {
        if(strategyVo == null){
            return;
        }
        Strategy strategy = new Strategy(strategyVo.getStrategyId(),strategyVo.getStrategyDesc(),
                strategyVo.getStrategyMode(),strategyVo.getGrantType(),strategyVo.getGrantDate(),strategyVo.getExtendInfo());
        strategyMapper.insert(strategy);
    }

    @Override
    public void addStrategyDetailList(List<StrategyDetailVo> strategyDetailVos) {
        if (strategyDetailVos == null || strategyDetailVos.isEmpty()) {
            return;
        }
        List<StrategyDetail> strategyDetails = strategyDetailVos.stream().map(strategyDetailVo -> new StrategyDetail(strategyDetailVo.getStrategyId(),strategyDetailVo.getAwardId(),strategyDetailVo.getAwardCount(),strategyDetailVo.getAwardRate(),strategyDetailVo.getAwardSurplusCount(),strategyDetailVo.getAwardName())).collect(Collectors.toList());
        strategyDetailMapper.insertBatch(strategyDetails);
    }

    @Override
    public boolean alterStatus(Long acitivityId, Enum<Constance.ActivityState> beforeState, Enum<Constance.ActivityState> afterState) {
        AlterStateVo alterStateVo = new AlterStateVo(acitivityId, ((Constance.ActivityState)beforeState).getCode(),((Constance.ActivityState)afterState).getCode());

        return activityMapper.alterStatus(alterStateVo) == 1;
    }

    @Override
    public ActivityBilVO queryActivityBill(PartakeReq req) {
        if(req == null){
            return null;
        }
        //获取活动的参与次数
        UserTakeActivityCount userTakeActivityCountReq = new UserTakeActivityCount();
        userTakeActivityCountReq.setActivityid(req.getActivityId());
        userTakeActivityCountReq.setUid(req.getUid());
        UserTakeActivityCount userTakeActivityCount = userTakeActivityCountMapper.queryUserTakeActivityCount(userTakeActivityCountReq);

        // 获取活动的参与信息
        Activity activity = activityMapper.queryById(req.getActivityId());
        ActivityBilVO activityBilVO = new ActivityBilVO();
        activityBilVO.setActivityId(activity.getActivityId())
                .setActivityName(activity.getActivityName())
                .setState(activity.getState())
                .setTakeCount(activity.getTakeCount()).setUid(activityBilVO.getUid())
                .setStrategyId(activityBilVO.getStrategyId())
                .setStockSurplusCount(activity.getStocksurpluscount())
                .setBeginDateTime(activity.getBeginDateTime())
                .setEndDateTime(activity.getEndDateTime())
                .setStrategyId(activityBilVO.getStrategyId())
                .setUid(req.getUid())
                .setUserTakeLeftCount(null == userTakeActivityCount? null : userTakeActivityCount.getLeftcount());

        return activityBilVO;
    }

    @Override
    public int subtractionActivityStock(Long activityId) {
        return activityMapper.subtractionActivityStock(activityId);
    }
}
