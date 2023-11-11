package com.kevin.domain.activity.service.partake;

import com.kevin.common.Constance;
import com.kevin.common.Result;
import com.kevin.domain.activity.model.req.PartakeReq;
import com.kevin.domain.activity.model.resp.PartakeResult;
import com.kevin.domain.activity.model.resp.StockResult;
import com.kevin.domain.activity.model.vo.ActivityBilVO;
import com.kevin.domain.activity.model.vo.UserTakeActivityVO;
import com.kevin.domain.support.ids.IIDGenerate;

import javax.annotation.Resource;
import java.util.Map;


public abstract class BaseActivityPartake extends ActivityPartakeSupport implements  IActivityPartake{

    @Resource
    private Map<Constance.Ids, IIDGenerate> idGeneratorMap;
    @Override
    public PartakeResult doPartake(PartakeReq req) {
        // 1.获取之前抽奖失败的，如果有的话，在此执行，之前失败是因为抽奖没有抽到
        UserTakeActivityVO userTakeActivityVO = this.queryNoConsumedTakeActivityOrder(req.getActivityId(), req.getUid());
        if(null != userTakeActivityVO){
            return buildPartakeResult(userTakeActivityVO.getStrategyId() ,userTakeActivityVO.getTakeId(),Constance.ResponseCode.NOT_CONSUMED_TAKE);
        }
        // 2.查看活动账单
        ActivityBilVO activityBilVO = super.queryActivityBill(req);

        // 3.活动信息校验处理【活动库存、状态、日期、个人参与次数】
        Result result = this.checkActivityBill(req, activityBilVO);
        if(Constance.ResponseCode.SUCCESSFUL.getCode() != result.getCode()){

            return new PartakeResult(result.getCode(),result.getInfo());
        }

        // 4. 扣减活动库存，通过Redis【活动库存扣减编号，作为锁的Key，缩小颗粒度】 Begin
        StockResult stockResult = this.subtractionActivityStockByRedis(req.getUid(), req.getActivityId(), activityBilVO.getStockCount());
        if(Constance.ResponseCode.SUCCESSFUL.getCode() != result.getCode()){
            // 如果获取锁失败，可能是库存不够，或者加锁失败等，或者其他意外，都需要释放刚才加的锁
            this.recoverActivityCacheStockByRedis(req.getActivityId(),stockResult.getStockKey(),stockResult.getCode());
            return new PartakeResult(stockResult.getCode(),stockResult.getInfo());
        }
        // 生成对应的takeId
        Long takeId = idGeneratorMap.get(Constance.Ids.SnowFlake).nextId();
        // 5.插入领取活动信息【个人用户把活动信息写入到用户表】
        Result grabResult = grabActivity(req, activityBilVO, takeId);
        if(Constance.ResponseCode.SUCCESSFUL.getCode() != result.getCode()){
            // 如果插入失败，需要释放锁信息
            this.recoverActivityCacheStockByRedisAndDecrCount(req.getActivityId(),stockResult.getStockKey(),grabResult.getCode());
            return new PartakeResult(result.getCode(),result.getInfo());
        }

        // 6.最后一步需要将锁释放
        this.recoverActivityCacheStockByRedis(req.getActivityId(), stockResult.getStockKey(),Constance.ResponseCode.SUCCESSFUL.getCode());
        // 封装结果【返回的策略ID，用于继续完成抽奖步骤】
        return buildPartakeResult(activityBilVO.getActivityId(),takeId,activityBilVO.getStockCount(),stockResult.getStockSurplusCount(),Constance.ResponseCode.SUCCESSFUL);
    }



    private PartakeResult buildPartakeResult(Long strategyId, Long takeId,Integer stockCount,Integer stockSurplusCount, Constance.ResponseCode responseCode) {
        PartakeResult partakeResult = new PartakeResult(responseCode.getCode(), responseCode.getDesc());
        partakeResult.setStrategyId(strategyId);
        partakeResult.setTakeId(takeId);
        partakeResult.setStockCount(stockCount);
        partakeResult.setStockSurplusCount(stockSurplusCount);
        return partakeResult;
    }

    private PartakeResult buildPartakeResult(Long strategyId, Long takeId,Constance.ResponseCode responseCode) {
        PartakeResult partakeResult = new PartakeResult(responseCode.getCode(), responseCode.getDesc());
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

    protected abstract StockResult subtractionActivityStockByRedis(String uId, Long activityId, Integer stockCount);

    /**
     * 领取活动
     *
     * @param partake 参与活动请求
     * @param bill    活动账单
     * @return 领取结果
     */
    protected abstract Result grabActivity(PartakeReq partake, ActivityBilVO bill,Long takeId);
}
