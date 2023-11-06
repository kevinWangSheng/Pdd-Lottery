package com.kevin.lottery.interfaces;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kevin.common.Result;
import com.kevin.lottery.infrastructure.dao.ActivityMapper;
import com.kevin.lottery.infrastructure.po.Activity;
import com.kevin.lottery.rpc.IActivityBooth;
import com.kevin.lottery.rpc.dto.ActivityDto;
import com.kevin.lottery.rpc.req.ActivityReq;
import com.kevin.lottery.rpc.resp.ActivityResp;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;

/**
* @author wang sheng hui
* @description 针对表【activity(活动配置)】的数据库操作Service实现
* @createDate 2023-11-03 18:20:41
*/
@DubboService
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity>
    implements IActivityBooth {

    @Override
    public ActivityResp queryById(ActivityReq req) {
        if(req == null){
            return null;
        }
        QueryWrapper<Activity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Activity::getId, req.getActivityId()).
                select(Activity::getActivityName,Activity::getActivityDesc,Activity::getActivityId,Activity::getBeginDateTime,Activity::getEndDateTime,
                        Activity::getStockCount,Activity::getTakeCount,Activity::getState);
        Activity activity = getOne(wrapper);
        if(activity == null){
            return new ActivityResp(Result.buildFailResult("活动状态变更失败"),null);
        }
        ActivityDto activityDto = new ActivityDto();
        BeanUtils.copyProperties(activity,activityDto);

        return new ActivityResp(Result.buildSuccessResult(),activityDto);
    }
}




