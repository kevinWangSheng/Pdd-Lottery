package com.kevin.domain.activity.service.stateflow.event;

import com.kevin.common.Constance;
import com.kevin.common.Result;
import com.kevin.domain.activity.service.stateflow.AbstractState;
import com.kevin.domain.activity.service.stateflow.IStateHandler;
import org.springframework.stereotype.Component;

/**活动进行状态
 * @author wang
 * @create 2023-2023-06-1:00
 */
@Component
public class DoingState extends AbstractState {
    @Override
    public Result arraignment(Long activityId, Enum<Constance.ActivityState> currentState) {
        return Result.buildResult(Constance.ResponseCode.UN_ERROR, "活动中不可提审");
    }

    @Override
    public Result checkPass(Long activityId, Enum<Constance.ActivityState> currentState) {
        return Result.buildResult(Constance.ResponseCode.UN_ERROR, "活动中不可审核通过");
    }

    @Override
    public Result checkRefuse(Long activityId, Enum<Constance.ActivityState> currentState) {
        return Result.buildResult(Constance.ResponseCode.UN_ERROR, "活动中不可审核拒绝");
    }

    @Override
    public Result checkRevoke(Long activityId, Enum<Constance.ActivityState> currentState) {
        return Result.buildResult(Constance.ResponseCode.UN_ERROR, "活动中不可撤销审核");
    }

    @Override
    public Result close(Long activityId, Enum<Constance.ActivityState> currentState) {
        boolean isSuccess = activityRepository.alterStatus(activityId, currentState, Constance.ActivityState.CLOSE);
        return isSuccess ? Result.buildResult(Constance.ResponseCode.SUCCESSFUL, "活动关闭成功") : Result.buildFailResult("活动状态变更失败");
    }

    @Override
    public Result open(Long activityId, Enum<Constance.ActivityState> currentState) {
        return Result.buildResult(Constance.ResponseCode.UN_ERROR, "活动中不可开启");
    }

    @Override
    public Result doing(Long activityId, Enum<Constance.ActivityState> currentState) {
        return Result.buildResult(Constance.ResponseCode.UN_ERROR, "活动中不可重复执行");
    }
}
