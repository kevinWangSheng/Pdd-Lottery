package com.kevin.domain.activity.service.stateflow.event;

import com.kevin.common.Constance;
import com.kevin.common.Result;
import com.kevin.domain.activity.reporisitory.IActivityRepository;
import com.kevin.domain.activity.service.stateflow.AbstractState;
import org.springframework.stereotype.Component;

/**提审状态
 * @author wang
 * @create 2023-2023-06-0:59
 */
@Component
public class ArraignmentState extends AbstractState {

    @Override
    public Result arraignment(Long activityId, Enum<Constance.ActivityState> currentState) {
        return Result.buildResult(Constance.ResponseCode.UN_ERROR, "待审核状态不可重复提审");
    }

    @Override
    public Result checkPass(Long activityId, Enum<Constance.ActivityState> currentState) {
        boolean success = activityRepository.alterStatus(activityId, currentState, Constance.ActivityState.PASS);
        return success ? Result.buildResult(Constance.ResponseCode.SUCCESSFUL, "活动审核通过") : Result.buildResult(Constance.ResponseCode.UN_ERROR, "活动状态变更失败");
    }

    @Override
    public Result checkRefuse(Long activityId, Enum<Constance.ActivityState> currentState) {
        boolean success = activityRepository.alterStatus(activityId, currentState, Constance.ActivityState.REFUSE);
        return success ? Result.buildResult(Constance.ResponseCode.UN_ERROR, "活动审核拒绝完成") : Result.buildResult(Constance.ResponseCode.UN_ERROR, "活动状态变更失败");
    }

    @Override
    public Result checkRevoke(Long activityId, Enum<Constance.ActivityState> currentState) {
        boolean success = activityRepository.alterStatus(activityId, currentState, Constance.ActivityState.REVOKE);
        return success ? Result.buildResult(Constance.ResponseCode.UN_ERROR, "活动审核撤回到编辑中") : Result.buildResult(Constance.ResponseCode.UN_ERROR, "活动状态变更失败");
    }

    @Override
    public Result close(Long activityId, Enum<Constance.ActivityState> currentState) {
        boolean success = activityRepository.alterStatus(activityId, currentState, Constance.ActivityState.CLOSE);
        return success ? Result.buildResult(Constance.ResponseCode.UN_ERROR, "活动审核关闭完成") : Result.buildResult(Constance.ResponseCode.UN_ERROR, "活动状态变更失败");
    }

    @Override
    public Result open(Long activityId, Enum<Constance.ActivityState> currentState) {
        return Result.buildResult(Constance.ResponseCode.UN_ERROR, "非关闭活动不可开启");
    }

    @Override
    public Result doing(Long activityId, Enum<Constance.ActivityState> currentState) {
        return Result.buildResult(Constance.ResponseCode.UN_ERROR, "待审核活动不可执行活动中变更");
    }
}
