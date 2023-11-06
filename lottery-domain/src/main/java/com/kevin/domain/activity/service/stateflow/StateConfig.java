package com.kevin.domain.activity.service.stateflow;

import com.kevin.common.Constance;
import com.kevin.domain.activity.service.stateflow.event.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wang
 * @create 2023-2023-06-0:56
 */
public class StateConfig {
    protected final Map<Enum<Constance.ActivityState>,AbstractState> stateMap = new ConcurrentHashMap<>();

    @Resource
    private ArraignmentState arraignmentState;

    @Resource
    private CloseState closeState;

    @Resource
    private DoingState doingState;

    @Resource
    private EditingState editingState;

    @Resource
    private OpenState openState;

    @Resource
    private PassState passState;

    @Resource
    private RefuseState refuseState;

    @PostConstruct
    public void init(){
        stateMap.put(Constance.ActivityState.ARRAIGNMENT,arraignmentState);
        stateMap.put(Constance.ActivityState.CLOSE,closeState);
        stateMap.put(Constance.ActivityState.DOING,doingState);
        stateMap.put(Constance.ActivityState.EDIT,editingState);
        stateMap.put(Constance.ActivityState.OPEN,openState);
        stateMap.put(Constance.ActivityState.PASS,passState);
        stateMap.put(Constance.ActivityState.REFUSE,refuseState);
    }
}
