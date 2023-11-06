package com.kevin.domain.activity.service.stateflow;

import com.kevin.common.Constance;
import com.kevin.common.Result;

/**状态处理接口
 * @author wang
 * @create 2023-2023-06-0:57
 */
public interface IStateHandler {
    /**
     * 提审
     * @param acitivityId
     * @param currentState
     * @return
     */
    Result arraignment(Long acitivityId, Enum<Constance.ActivityState> currentState);

    /**
     * 审核通过
     * @param acitivityId
     * @param currentState
     * @return
     */
    Result checkPass(Long acitivityId, Enum<Constance.ActivityState> currentState);

    /**
     * 审核拒绝
     * @param acitivityId
     * @param currentState
     * @return
     */
    Result checkRefuse(Long acitivityId, Enum<Constance.ActivityState> currentState);

    /**
     * 审核撤销
     * @param acitivityId
     * @param currentState
     * @return
     */
    Result checkRevoke(Long acitivityId, Enum<Constance.ActivityState> currentState);

    /**
     * 关闭活动
     * @param acitivityId
     * @param currentState
     * @return
     */
    Result close(Long acitivityId, Enum<Constance.ActivityState> currentState);

    /**
     * 开启活动
     * @param acitivityId
     * @param currentState
     * @return
     */
    Result open(Long acitivityId, Enum<Constance.ActivityState> currentState);

    /**
     *活动进行中
     * @param acitivityId
     * @param currentState
     * @return
     */
    Result doing(Long acitivityId, Enum<Constance.ActivityState> currentState);

}
