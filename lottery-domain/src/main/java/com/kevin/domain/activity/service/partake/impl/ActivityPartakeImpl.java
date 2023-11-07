package com.kevin.domain.activity.service.partake.impl;

import cn.bugstack.middleware.db.router.strategy.IDBRouterStrategy;
import com.kevin.common.Constance;
import com.kevin.common.Result;
import com.kevin.domain.activity.model.req.PartakeReq;
import com.kevin.domain.activity.model.vo.ActivityBilVO;
import com.kevin.domain.activity.model.vo.DrawOrderVO;
import com.kevin.domain.activity.reporisitory.UserTakeActivityRepository;
import com.kevin.domain.activity.service.partake.BaseActivityPartake;
import com.kevin.domain.support.ids.IIDGenerate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author wang
 * @create 2023-11-07-14:51
 */
@Service
public class ActivityPartakeImpl extends BaseActivityPartake {
    private final Logger logger = LoggerFactory.getLogger(ActivityPartakeImpl.class);

    @Resource
    private UserTakeActivityRepository userTakeActivityRepository;

    @Resource
    private Map<Constance.Ids, IIDGenerate> idGenerateMap;
    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private IDBRouterStrategy dbRouter;
    @Override
    protected Result checkActivityBill(PartakeReq partake, ActivityBilVO bill) {
        if(!Constance.ActivityState.DOING.getCode().equals(bill.getState())){
            logger.info("当前活动状态：{},不可用",bill.getState());
            return new Result(Constance.ResponseCode.UN_ERROR.getCode(),"当前状态不可用");
        }
        if(bill.getStockSurplusCount() <=0){
            logger.info("当前活动库存不足");
            return new Result(Constance.ResponseCode.UN_ERROR.getCode(), "库存不足");
        }
        if(bill.getBeginDateTime().after(partake.getPartakeDate()) || bill.getEndDateTime().before(partake.getPartakeDate())){
            logger.info("当前活动时间不可用");
            return new Result(Constance.ResponseCode.UN_ERROR.getCode(), "当前时间不可用");
        }

        if(bill.getUserTakeLeftCount()<=0){
            logger.info("个人领取次数非可用: userTakeLeftCount:{}",bill.getUserTakeLeftCount());
            return new Result(Constance.ResponseCode.UN_ERROR.getCode(), "个人领取次数不足");
        }
        return Result.buildSuccessResult();
    }

    @Override
    protected Result subtractionActivityStock(PartakeReq req) {
        int count = activityRepository.subtractionActivityStock(req.getActivityId());
        return count != 0 ? Result.buildSuccessResult() : Result.buildFailResult("扣减库存失败");
    }

    @Override
    protected Result grabActivity(PartakeReq partake, ActivityBilVO bill) {
        try {
            dbRouter.doRouter(partake.getUid());
            return transactionTemplate.execute(status->{
                try {
                    int updateCount = userTakeActivityRepository.subtractionLeftCount(partake.getActivityId(), bill.getActivityName(), bill.getTakeCount(),
                            bill.getUserTakeLeftCount(), partake.getUid(), partake.getPartakeDate());
                    if (updateCount == 0) {
                        status.setRollbackOnly();
                        logger.error("领取活动，扣减个人已参与次数失败 activityId：{} uId：{}", partake.getActivityId(), partake.getUid());
                        return Result.buildFailResult(Constance.ResponseCode.NO_UPDATE);
                    }
                    Long takeId = idGenerateMap.get(Constance.Ids.SnowFlake).nextId();
                    userTakeActivityRepository.takeActivity(bill.getActivityId(),bill.getActivityName(),bill.getTakeCount(),bill.getUserTakeLeftCount(),bill.getUid(),partake.getPartakeDate(),takeId);
                } catch (Exception e) {
                    status.setRollbackOnly();
                    logger.error("领取活动，唯一索引冲突 activityId：{} uId：{}", partake.getActivityId(), partake.getUid(), e);
                    return Result.buildFailResult(Constance.ResponseCode.INDEX_DUP);
                }
                return Result.buildSuccessResult();
            });
        } finally {
            dbRouter.clear();
        }
    }

    /**
     * 记录抽奖订单
     * @param drawOrderVO
     * @return
     */
    @Override
    public Result recordDrawOrder(DrawOrderVO drawOrderVO) {
        try {
            dbRouter.doRouter(drawOrderVO.getUId());
            return transactionTemplate.execute(state->{
                try {
                    int lockCount = userTakeActivityRepository.lockTracActivity(drawOrderVO.getUId(),drawOrderVO.getActivityId(),drawOrderVO.getTakeId());
                    if(0 == lockCount){
                        state.setRollbackOnly();
                        logger.error("记录中奖单，个人参与活动抽奖已消耗完 activityId：{} uId：{}", drawOrderVO.getActivityId(), drawOrderVO.getUId());
                        return Result.buildFailResult(Constance.ResponseCode.NO_UPDATE);
                    }
                    // 保存抽奖信息
                    userTakeActivityRepository.saveUserStrategyExport(drawOrderVO);
                } catch (Exception e) {
                    state.setRollbackOnly();
                    logger.error("记录中奖单，唯一索引冲突 activityId：{} uId：{}", drawOrderVO.getActivityId(), drawOrderVO.getUId(), e);
                    return Result.buildResult(Constance.ResponseCode.INDEX_DUP);
                }
                return Result.buildSuccessResult();
            });
        } finally {
            dbRouter.clear();
        }
    }
}
