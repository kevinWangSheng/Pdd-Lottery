package com.kevin.domain.activity.reporisitory;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kevin.common.Constance;
import com.kevin.domain.activity.model.req.PartakeReq;
import com.kevin.domain.activity.model.vo.*;


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


    /**
     *变更活动状态
     * @param acitivity 活动id
     * @param beforeState 修改前状态
     * @param afterState 修改后状态
     * @return 修改是否成功
     */
    boolean alterStatus(Long acitivity, Enum<Constance .ActivityState> beforeState,Enum<Constance.ActivityState> afterState);

    /**
     * 查询活动账单信息【库存、状态、日期、个人参与次数
     * @param req 参与活动请求参数
     * @return 活动账单信息
     */
    ActivityBilVO queryActivityBill(PartakeReq req);

    /**
     * 扣减库存活动
     * @param activityId 活动id
     * @return 扣减结果
     */
    int subtractionActivityStock(Long activityId);
}
