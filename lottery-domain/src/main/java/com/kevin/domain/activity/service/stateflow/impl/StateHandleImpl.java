package com.kevin.domain.activity.service.stateflow.impl;

import com.kevin.common.Constance;
import com.kevin.common.Result;
import com.kevin.domain.activity.service.stateflow.IStateHandler;
import com.kevin.domain.activity.service.stateflow.StateConfig;
import org.springframework.stereotype.Service;

/**
 * @author wang
 * @create 2023-2023-06-0:57
 */
@Service
public class StateHandleImpl extends StateConfig implements IStateHandler {
    @Override
    public Result arraignment(Long acitivityId, Enum<Constance.ActivityState> currentState) {
        return stateMap.get(currentState).arraignment(acitivityId,currentState);
    }

    @Override
    public Result checkPass(Long acitivityId, Enum<Constance.ActivityState> currentState) {
        return stateMap.get(currentState).checkPass(acitivityId,currentState);
    }

    @Override
    public Result checkRefuse(Long acitivityId, Enum<Constance.ActivityState> currentState) {
        return stateMap.get(currentState).checkRefuse(acitivityId,currentState);
    }

    @Override
    public Result checkRevoke(Long acitivityId, Enum<Constance.ActivityState> currentState) {
        return stateMap.get(currentState).checkRevoke(acitivityId,currentState);
    }

    @Override
    public Result close(Long acitivityId, Enum<Constance.ActivityState> currentState) {
        return stateMap.get(currentState).close(acitivityId,currentState);
    }

    @Override
    public Result open(Long acitivityId, Enum<Constance.ActivityState> currentState) {
        return stateMap.get(currentState).open(acitivityId,currentState);
    }

    @Override
    public Result doing(Long acitivityId, Enum<Constance.ActivityState> currentState) {
        return stateMap.get(currentState).doing(acitivityId,currentState);
    }
}
