package com.kevin.domain.activity.service.stateflow.event;

import com.kevin.common.Constance;
import com.kevin.common.Result;
import com.kevin.domain.activity.service.stateflow.AbstractState;
import org.springframework.stereotype.Component;

/**关闭状态
 * @author wang
 * @create 2023-2023-06-0:59
 */
@Component
public class CloseState extends AbstractState {
    @Override
    public Result arraignment(Long activityId, Enum<Constance.ActivityState> currentState) {
        return Result.buildResult(Constance.ResponseCode.UN_ERROR, "活动关闭不可提审");
    }

    @Override
    public Result checkPass(Long activityId, Enum<Constance.ActivityState> currentState) {
        return Result.buildResult(Constance.ResponseCode.UN_ERROR, "活动关闭不可审核通过");
    }

    @Override
    public Result checkRefuse(Long activityId, Enum<Constance.ActivityState> currentState) {
        return Result.buildResult(Constance.ResponseCode.UN_ERROR, "活动关闭不可审核拒绝");
    }

    @Override
    public Result checkRevoke(Long activityId, Enum<Constance.ActivityState> currentState) {
        return Result.buildResult(Constance.ResponseCode.UN_ERROR, "活动关闭不可撤销审核");
    }

    @Override
    public Result close(Long activityId, Enum<Constance.ActivityState> currentState) {
        return Result.buildResult(Constance.ResponseCode.UN_ERROR, "活动关闭不可重复关闭");
    }

    @Override
    public Result open(Long activityId, Enum<Constance.ActivityState> currentState) {
        boolean isSuccess = activityRepository.alterStatus(activityId, currentState, Constance.ActivityState.OPEN);
        return isSuccess ? Result.buildResult(Constance.ResponseCode.SUCCESSFUL, "活动开启完成") : Result.buildFailResult("活动状态变更失败");
    }

    @Override
    public Result doing(Long activityId, Enum<Constance.ActivityState> currentState) {
        return Result.buildResult(Constance.ResponseCode.UN_ERROR, "活动关闭不可变更活动中");
    }
}
