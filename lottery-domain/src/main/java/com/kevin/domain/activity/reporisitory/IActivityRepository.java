package com.kevin.domain.activity.reporisitory;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kevin.common.Constance;
import com.kevin.domain.activity.model.vo.AcitivityVo;
import com.kevin.domain.activity.model.vo.AwardVo;
import com.kevin.domain.activity.model.vo.StrategyDetailVo;
import com.kevin.domain.activity.model.vo.StrategyVo;


import java.util.List;

/**
 * @author wang
 * @create 2023-2023-05-19:33
 */
public interface IActivityRepository {
    void addAcitivity(AcitivityVo acitivityVo);

    void addAward(List<AwardVo> awardVos);

    void addStrategy(StrategyVo strategyVo);

    void addStrategyDetailList(List<StrategyDetailVo> strategyDetailVos);

    boolean alterStatus(Long acitivity, Enum<Constance .ActivityState> beforeState,Enum<Constance.ActivityState> afterState);
}
