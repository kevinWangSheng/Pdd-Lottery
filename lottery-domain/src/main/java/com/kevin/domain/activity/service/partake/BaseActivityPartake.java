package com.kevin.domain.activity.service.partake;

import com.kevin.common.Constance;
import com.kevin.common.Result;
import com.kevin.domain.activity.model.req.PartakeReq;
import com.kevin.domain.activity.model.resp.PartakeResult;
import com.kevin.domain.activity.model.vo.ActivityBilVO;


public abstract class BaseActivityPartake extends ActivityPartakeSupport implements  IActivityPartake{

    @Override
    public PartakeResult doPartake(PartakeReq req) {
        ActivityBilVO activityBilVO = super.queryActivityBill(req);

        // 活动信息校验处理【活动库存、状态、日期、个人参与次数】
        Result result = this.checkActivityBill(req, activityBilVO);
        if(Constance.ResponseCode.SUCCESSFUL.getCode() != result.getCode()){
            return new PartakeResult(result.getCode(),result.getInfo());
        }

        // 扣减活动库存【目前为直接对配置库中的 lottery.activity 直接操作表扣减库存，后续优化为Redis扣减】
        result = this.subtractionActivityStock(req);
        if(Constance.ResponseCode.SUCCESSFUL.getCode() != result.getCode()){
            return new PartakeResult(result.getCode(),result.getInfo());
        }
        // 领取活动信息【个人用户把活动信息写入到用户表】
        result = grabActivity(req, activityBilVO);
        if(Constance.ResponseCode.SUCCESSFUL.getCode() != result.getCode()){
            return new PartakeResult(result.getCode(),result.getInfo());
        }
        // 封装结果【返回的策略ID，用于继续完成抽奖步骤】
        PartakeResult partakeResult = new PartakeResult(Constance.ResponseCode.SUCCESSFUL.getCode(),Constance.ResponseCode.SUCCESSFUL.getDesc());
        partakeResult.setStrategyId(activityBilVO.getStrategyId());
        return partakeResult;
    }



    private PartakeResult buildPartakeResult(Long strategyId, Long takeId) {
        PartakeResult partakeResult = new PartakeResult(Constance.ResponseCode.SUCCESSFUL.getCode(), Constance.ResponseCode.SUCCESSFUL.getDesc());
        partakeResult.setStrategyId(strategyId);
        partakeResult.setTakeId(takeId);
        return partakeResult;
    }
    /**
     * 活动信息校验处理，把活动库存、状态、日期、个人参与次数
     *
     * @param partake 参与活动请求
     * @param bill    活动账单
     * @return 校验结果
     */
    protected abstract Result checkActivityBill(PartakeReq partake, ActivityBilVO bill);

    /**
     * 扣减活动库存
     *
     * @param req 参与活动请求
     * @return 扣减结果
     */
    protected abstract Result subtractionActivityStock(PartakeReq req);

    /**
     * 领取活动
     *
     * @param partake 参与活动请求
     * @param bill    活动账单
     * @return 领取结果
     */
    protected abstract Result grabActivity(PartakeReq partake, ActivityBilVO bill);
}
