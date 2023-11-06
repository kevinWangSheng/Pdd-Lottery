package com.kevin.domain.activity.service.stateflow.event;

import com.kevin.common.Constance;
import com.kevin.common.Result;
import com.kevin.domain.activity.service.stateflow.AbstractState;
import org.springframework.stereotype.Component;

/**活动拒绝状态
 * @author wang
 * @create 2023-2023-06-1:01
 */
@Component
public class RefuseState extends AbstractState {
    @Override
    public Result arraignment(Long activityId, Enum<Constance.ActivityState> currentState) {
        return Result.buildResult(Constance.ResponseCode.UN_ERROR, "已审核状态不可重复提审");
    }

    @Override
    public Result checkPass(Long activityId, Enum<Constance.ActivityState> currentState) {
        return Result.buildResult(Constance.ResponseCode.UN_ERROR, "已审核状态不可重复审核");
    }

    @Override
    public Result checkRefuse(Long activityId, Enum<Constance.ActivityState> currentState) {
        return Result.buildResult(Constance.ResponseCode.UN_ERROR, "活动审核拒绝不可重复审核");
    }

    @Override
    public Result checkRevoke(Long activityId, Enum<Constance.ActivityState> currentState) {
        boolean isSuccess = activityRepository.alterStatus(activityId, currentState, Constance.ActivityState.EDIT);
        return isSuccess ? Result.buildResult(Constance.ResponseCode.SUCCESSFUL, "撤销审核完成") : Result.buildFailResult("活动状态变更失败");
    }

    @Override
    public Result close(Long activityId, Enum<Constance.ActivityState> currentState) {
        boolean isSuccess = activityRepository.alterStatus(activityId, currentState, Constance.ActivityState.CLOSE);
        return isSuccess ? Result.buildResult(Constance.ResponseCode.SUCCESSFUL, "活动审核关闭完成") : Result.buildFailResult("活动状态变更失败");
    }

    @Override
    public Result open(Long activityId, Enum<Constance.ActivityState> currentState) {
        return Result.buildResult(Constance.ResponseCode.UN_ERROR, "非关闭活动不可开启");
    }

    @Override
    public Result doing(Long activityId, Enum<Constance.ActivityState> currentState) {
        return Result.buildResult(Constance.ResponseCode.UN_ERROR, "审核拒绝不可执行活动为进行中");
    }
}
