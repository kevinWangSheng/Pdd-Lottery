package com.kevin.lottery.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kevin.domain.activity.model.vo.DrawOrderVO;
import com.kevin.domain.activity.reporisitory.UserTakeActivityRepository;
import com.kevin.lottery.infrastructure.dao.UserStrategyExportMapper;
import com.kevin.lottery.infrastructure.dao.UserTakeActivityCountMapper;
import com.kevin.lottery.infrastructure.dao.UserTakeActivityMapper;
import com.kevin.lottery.infrastructure.po.UserStrategyExport;
import com.kevin.lottery.infrastructure.po.UserTakeActivity;
import com.kevin.lottery.infrastructure.po.UserTakeActivityCount;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
* @author wang sheng hui
* @description 针对表【user_take_activity(用户参与活动记录表)】的数据库操作Service实现
* @createDate 2023-11-06 21:14:11
*/
@Service
public class UserTakeActivityRepositoryImpl extends ServiceImpl<UserTakeActivityMapper, UserTakeActivity>
    implements UserTakeActivityRepository {

    @Resource
    private UserTakeActivityMapper userTakeActivityMapper;

    @Resource
    private UserTakeActivityCountMapper userTakeActivityCountMapper;

    @Resource
    private UserStrategyExportMapper userStrategyExportMapper;

    @Override
    public int subtractionLeftCount(Long activityId, String activityName, Integer takeCount, Integer userTakeLeftCount, String uId, Date partakeDate) {
        if(userTakeLeftCount == null){
            UserTakeActivityCount userTakeActivityCount = new UserTakeActivityCount();
            userTakeActivityCount.setUid(uId);
            userTakeActivityCount.setActivityid(activityId);
            userTakeActivityCount.setLeftcount(takeCount -1);
            userTakeActivityCount.setTotalcount(takeCount);
            userTakeActivityCountMapper.insertOne(userTakeActivityCount);
            return 1;
        }else{
            UserTakeActivityCount userTakeActivityCount = new UserTakeActivityCount();
            userTakeActivityCount.setUid(uId);
            userTakeActivityCount.setActivityid(activityId);
            return userTakeActivityCountMapper.updateLeftCount(userTakeActivityCount);
        }
    }

    @Override
    public void takeActivity(Long activityId, String activityName, Integer takeCount, Integer userTakeLeftCount, String uId, Date takeDate, Long takeId) {
        UserTakeActivity userTakeActivity = new UserTakeActivity();
        userTakeActivity.setUid(uId);
        userTakeActivity.setTakeid(takeId);
        userTakeActivity.setActivityid(activityId);
        userTakeActivity.setActivityname(activityName);
        userTakeActivity.setTakedate(takeDate);
        if (null == userTakeLeftCount) {
            userTakeActivity.setTakecount(1);
        } else {
            userTakeActivity.setTakecount(takeCount - userTakeLeftCount);
        }
        String uuid = uId + "_" + activityId + "_" + userTakeActivity.getTakecount();
        userTakeActivity.setUuid(uuid);

        userTakeActivityMapper.insertOne(userTakeActivity);
    }

    @Override
    public int lockTracActivity(String uId, Long activityId, Long takeId) {
        UserTakeActivity userTakeActivity = new UserTakeActivity();

        userTakeActivity.setTakeid(takeId);
        userTakeActivity.setUid(uId);
        userTakeActivity.setActivityid(activityId);

        return userTakeActivityMapper.lockTackActivity(userTakeActivity);
    }

    @Override
    public void saveUserStrategyExport(DrawOrderVO drawOrder) {
        UserStrategyExport userStrategyExport = new UserStrategyExport();
        userStrategyExport.setUid(drawOrder.getUId());
        userStrategyExport.setActivityid(drawOrder.getActivityId());
        userStrategyExport.setOrderid(drawOrder.getOrderId());
        userStrategyExport.setStrategyid(drawOrder.getStrategyId());
        userStrategyExport.setStrategyMode(drawOrder.getStrategyMode());
        userStrategyExport.setGranttype(drawOrder.getGrantType());
        userStrategyExport.setGrantdate(drawOrder.getGrantDate());
        userStrategyExport.setGrantstate(drawOrder.getGrantState());
        userStrategyExport.setAwardid(drawOrder.getAwardId());
        userStrategyExport.setAwardtype(drawOrder.getAwardType());
        userStrategyExport.setAwardname(drawOrder.getAwardName());
        userStrategyExport.setAwardcontent(drawOrder.getAwardContent());
        userStrategyExport.setUuid(String.valueOf(drawOrder.getOrderId()));

        userStrategyExportMapper.insertSelective(userStrategyExport);
    }

    @Override
    public void updateInvoiceMqState(String uId, Long orderId, Integer mqState) {
        UserStrategyExport userStrategyExport = new UserStrategyExport();
        userStrategyExport.setUid(uId);
        userStrategyExport.setOrderid(orderId);
        userStrategyExport.setMqState(mqState);
        userStrategyExportMapper.updateInvoiceMqState(userStrategyExport);
    }
}




