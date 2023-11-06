package com.kevin.domain.activity.service.stateflow;

import com.kevin.common.Constance;
import com.kevin.common.Result;
import com.kevin.domain.activity.reporisitory.IActivityRepository;

import javax.annotation.Resource;

/**
 * @author wang
 * @create 2023-2023-06-0:57
 */
public abstract class AbstractState {
    @Resource
    protected IActivityRepository activityRepository;

    /**
     * 审核活动
     * @param activityId
     * @param currentState
     * @return
     */
    public abstract Result arraignment(Long activityId, Enum<Constance.ActivityState> currentState);

    /**
     * 审核通过
     * @param activityId
     * @param currentState
     * @return
     */
    public abstract Result checkPass(Long activityId, Enum<Constance.ActivityState> currentState);

    /**
     * 审核拒绝
     * @param activityId
     * @param currentState
     * @return
     */
    public abstract Result checkRefuse(Long activityId, Enum<Constance.ActivityState> currentState);

    /**
     * 撤销审核
     * @param activityId
     * @param currentState
     * @return
     */
    public abstract Result checkRevoke(Long activityId, Enum<Constance.ActivityState> currentState);

    /**
     * 关闭活动
     * @param activityId
     * @param currentState
     * @return
     */
    public abstract Result close(Long activityId, Enum<Constance.ActivityState> currentState);

    /**
     * 开启活动
     * @param activityId
     * @param currentState
     * @return
     */
    public abstract Result open(Long activityId, Enum<Constance.ActivityState> currentState);

    /**
     * 活动进行中
     * @param activityId
     * @param currentState
     * @return
     */
    public abstract Result doing(Long activityId, Enum<Constance.ActivityState> currentState);

}
