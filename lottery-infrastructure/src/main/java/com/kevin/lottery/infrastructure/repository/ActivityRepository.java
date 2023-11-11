package com.kevin.lottery.infrastructure.repository;

import com.kevin.common.Constance;
import com.kevin.domain.activity.model.req.PartakeReq;
import com.kevin.domain.activity.model.resp.StockResult;
import com.kevin.domain.activity.model.vo.*;
import com.kevin.domain.activity.reporisitory.IActivityRepository;
import com.kevin.lottery.infrastructure.dao.*;
import com.kevin.lottery.infrastructure.po.*;
import com.kevin.lottery.infrastructure.util.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wang
 * @create 2023-2023-05-21:00
 */
@Component
public class ActivityRepository implements IActivityRepository{

    private final Logger logger = LoggerFactory.getLogger(ActivityRepository.class);

    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private AwardMapper awardMapper;

    @Resource
    private StrategyMapper strategyMapper;

    @Resource
    private StrategyDetailMapper strategyDetailMapper;

    @Resource
    private UserTakeActivityCountMapper userTakeActivityCountMapper;

    @Resource
    private UserStrategyExportMapper userStrategyExportMapper;
    @Resource
    private RedisUtils redisUtils;
    @Override
    public void addAcitivity(AcitivityVo acitivityVo) {
        if(acitivityVo == null){
            return;
        }
        Activity activity = new Activity(acitivityVo.getAcitivityId(),acitivityVo.getActivityName(),acitivityVo.getActivityDesc(),
                acitivityVo.getBeginTime(),acitivityVo.getEndTime(),acitivityVo.getStockCount(), acitivityVo.getTakeCount(),
                acitivityVo.getState(),acitivityVo.getCreator());
        activityMapper.insert(activity);
    }

    @Override
    public void addAward(List<AwardVo> awardVos) {
        if(awardVos == null || awardVos.isEmpty()){
            return;
        }
        List<Award> awards = awardVos.stream().map(awardVo -> new Award(awardVo.getAwardId(),awardVo.getAwardType(),awardVo.getAwardName(),awardVo.getAwardContent())).collect(Collectors.toList());
        awardMapper.insertBatch(awards);
    }

    @Override
    public void addStrategy(StrategyVo strategyVo) {
        if(strategyVo == null){
            return;
        }
        Strategy strategy = new Strategy(strategyVo.getStrategyId(),strategyVo.getStrategyDesc(),
                strategyVo.getStrategyMode(),strategyVo.getGrantType(),strategyVo.getGrantDate(),strategyVo.getExtendInfo());
        strategyMapper.insert(strategy);
    }

    @Override
    public void addStrategyDetailList(List<StrategyDetailVo> strategyDetailVos) {
        if (strategyDetailVos == null || strategyDetailVos.isEmpty()) {
            return;
        }
        List<StrategyDetail> strategyDetails = strategyDetailVos.stream().map(strategyDetailVo -> new StrategyDetail(strategyDetailVo.getStrategyId(),strategyDetailVo.getAwardId(),strategyDetailVo.getAwardCount(),strategyDetailVo.getAwardRate(),strategyDetailVo.getAwardSurplusCount(),strategyDetailVo.getAwardName())).collect(Collectors.toList());
        strategyDetailMapper.insertBatch(strategyDetails);
    }

    @Override
    public boolean alterStatus(Long acitivityId, Enum<Constance.ActivityState> beforeState, Enum<Constance.ActivityState> afterState) {
        AlterStateVo alterStateVo = new AlterStateVo(acitivityId, ((Constance.ActivityState)beforeState).getCode(),((Constance.ActivityState)afterState).getCode());

        return activityMapper.alterStatus(alterStateVo) == 1;
    }

    @Override
    public ActivityBilVO queryActivityBill(PartakeReq req) {
        if(req == null){
            return null;
        }
        //获取活动的参与次数
        UserTakeActivityCount userTakeActivityCountReq = new UserTakeActivityCount();
        userTakeActivityCountReq.setActivityid(req.getActivityId());
        userTakeActivityCountReq.setUid(req.getUid());
        UserTakeActivityCount userTakeActivityCount = userTakeActivityCountMapper.queryUserTakeActivityCount(userTakeActivityCountReq);

        // 从缓存中获取库存,主要是因为处于秒杀状态，所以需要从redis中直接获取，而不是数据库，因为数据库还没有更新，但是第一次需要从数据库中获取对应
        Object usedStockCountObj =  redisUtils.get(Constance.RedisKey.KEY_LOTTERY_ACTIVITY_STOCK_COUNT(req.getActivityId()));
        // 获取活动的参与信息
        Activity activity = activityMapper.queryById(req.getActivityId());
        ActivityBilVO activityBilVO = new ActivityBilVO();
        activityBilVO.setActivityId(activity.getActivityId())
                .setActivityName(activity.getActivityName())
                .setState(activity.getState())
                .setTakeCount(activity.getTakeCount()).setUid(activityBilVO.getUid())
                .setStrategyId(activity.getStrategyId())
                .setStockCount(activity.getStockCount())
                // 对其进行已使用次数的设置
                .setStockSurplusCount(null == usedStockCountObj ? activity.getStocksurpluscount() : activity.getStockCount() - Integer.parseInt(String.valueOf(usedStockCountObj)) )
                .setBeginDateTime(activity.getBeginDateTime())
                .setEndDateTime(activity.getEndDateTime())
                .setUid(req.getUid())
                .setUserTakeLeftCount(null == userTakeActivityCount? 0 : userTakeActivityCount.getLeftcount());
        if(usedStockCountObj == null){
            // 如果对应的分布式没有设置过需要设置一下，并且在获得锁之后还需要在判断一下有没有设置，如果设置了就不需要在设置了
            redisUtils.setNx(Constance.RedisKey.SET_LOCK+activityBilVO.getActivityId(),1000l);
            if(!redisUtils.hasKey(Constance.RedisKey.KEY_LOTTERY_ACTIVITY_STOCK_COUNT(activityBilVO.getActivityId()))) {
                redisUtils.set(Constance.RedisKey.KEY_LOTTERY_ACTIVITY_STOCK_COUNT(req.getActivityId()), activityBilVO.getStockCount() - activityBilVO.getStockSurplusCount());
            }
            redisUtils.del(Constance.RedisKey.SET_LOCK);
        }
        return activityBilVO;
    }

    @Override
    public int subtractionActivityStock(Long activityId) {
        return activityMapper.subtractionActivityStock(activityId);
    }

    @Override
    public List<AcitivityVo> scanToDoActivityList(Long id) {

        List<Activity> activities = activityMapper.scanToDoActivityList(id);
        if(activities == null || activities.isEmpty()){
            return new ArrayList<>();
        }
        List<AcitivityVo> acitivityVoList = new ArrayList<>();
        for(Activity activity :activities) {
            AcitivityVo acitivityVo = new AcitivityVo();
            acitivityVo.setAcitivityId(activity.getActivityId());
            acitivityVo.setActivityName(activity.getActivityName());
            acitivityVo.setBeginTime(activity.getBeginDateTime());
            acitivityVo.setEndTime(activity.getEndDateTime());
            acitivityVo.setState(activity.getState());
            acitivityVo.setCreator(activity.getCreator());

            acitivityVoList.add(acitivityVo);
        }
        return acitivityVoList;
    }

    @Override
    public List<InvoiceVO> scanInvoiceMqState() {
        List<UserStrategyExport> userStrategyExports = userStrategyExportMapper.scanInvoiceMqState(new Date(System.currentTimeMillis() - 30 * 60 * 1000));

        List<InvoiceVO> invoiceVOList = new ArrayList<>();

        for(UserStrategyExport userStrategyExport : userStrategyExports){
            InvoiceVO invoiceVO = new InvoiceVO();

            invoiceVO.setuId(userStrategyExport.getUid());
            invoiceVO.setOrderId(userStrategyExport.getOrderid());
            invoiceVO.setAwardName(userStrategyExport.getAwardname());
            invoiceVO.setAwardId(userStrategyExport.getAwardid());
            invoiceVO.setAwardType(userStrategyExport.getAwardtype());
            invoiceVO.setAwardContent(userStrategyExport.getAwardcontent());

            invoiceVOList.add(invoiceVO);
        }
        return invoiceVOList;
    }

    /**
     * 对需要秒杀的库存进行判断是否可以秒杀，并进行加锁操作
     * @param uId 用户id
     * @param activityId 活动id
     * @param stockCount 秒杀总库存，用于判断当前使用的数量是否超过总库存的数量
     * @return
     */
    @Override
    public StockResult subtractionActivityStockByRedis(String uId, Long activityId, Integer stockCount) {
        // 先获取对应库存的key
        String activityStockCountKey = Constance.RedisKey.KEY_LOTTERY_ACTIVITY_STOCK_COUNT(activityId);

        // 超出秒杀库存范围，直接返回
        Integer stockUserCount = (int)redisUtils.incr(activityStockCountKey,1);
        if(stockUserCount>stockCount){
            redisUtils.decr(activityStockCountKey,1);
            return new StockResult(Constance.ResponseCode.OUT_OF_STOCK.getCode(), Constance.ResponseCode.OUT_OF_STOCK.getDesc());
        }

        String redisLockKey = Constance.RedisKey.KEY_LOTTERY_ACTIVITY_STOCK_COUNT_TOKEN(activityId, stockUserCount);
        boolean lockSuccess = redisUtils.setNx(redisLockKey,350L);
        if(!lockSuccess){
            logger.info("抽奖活动{}用户秒杀{}扣减库存，分布式锁失败：{}", activityId, uId, redisLockKey);
            return new StockResult(Constance.ResponseCode.ERR_TOKEN.getCode(),Constance.ResponseCode.ERR_TOKEN.getDesc());
        }
        // 返回加锁成功
        return new StockResult(Constance.ResponseCode.SUCCESSFUL.getCode(), Constance.ResponseCode.SUCCESSFUL.getDesc(),redisLockKey, stockCount - stockUserCount);
    }

    @Override
    public void recoverActivityCacheStockByRedis(Long activityId, String stockKey, Integer code) {
        if(StringUtils.isBlank(stockKey)){
            return;
        }
        redisUtils.del(stockKey);
    }

    @Override
    public void recoverActivityCacheStockByRedisAndDecrCount(Long activityId, String stockKey, Integer code) {
        if(StringUtils.isBlank(stockKey)){
            return;
        }
        redisUtils.del(stockKey);
        String activityStockCountKey = Constance.RedisKey.KEY_LOTTERY_ACTIVITY_STOCK_COUNT(activityId);
        // 这里主要是处理数据库发生异常的情况下的释放锁，因为是数据库层面的，所以他这个加锁已经成功，库存已经扣减，但是数据库还没有对应的操作成功，所以他这个redis的库存也应该进行恢复.
        redisUtils.decr(activityStockCountKey,1);

    }
}
