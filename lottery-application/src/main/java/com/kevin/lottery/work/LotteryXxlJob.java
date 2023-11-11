package com.kevin.lottery.work;

import cn.bugstack.middleware.db.router.strategy.IDBRouterStrategy;
import com.alibaba.fastjson.JSON;
import com.kevin.common.Constance;
import com.kevin.common.Result;
import com.kevin.domain.activity.model.vo.AcitivityVo;
import com.kevin.domain.activity.model.vo.InvoiceVO;
import com.kevin.domain.activity.service.deploy.IActivityDeploy;
import com.kevin.domain.activity.service.partake.IActivityPartake;
import com.kevin.domain.activity.service.stateflow.IStateHandler;
import com.kevin.lottery.application.mq.producer.KafkaProducer;
import com.kevin.lottery.application.process.IActivityProcess;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author wang
 * @create 2023-11-10-16:53
 */
@Component
public class LotteryXxlJob {
    private final Logger logger = LoggerFactory.getLogger(LotteryXxlJob.class);

    @Resource
    private IActivityDeploy activityDeploy;

    @Resource
    private IStateHandler stateHandler;

    @Resource
    private IDBRouterStrategy dbRouterStrategy;

    @Resource
    private KafkaProducer kafkaProducer;

    @Resource
    private IActivityPartake activityPartake;

    @XxlJob("lotteryActivityStateJobHandler")
    public void lotteryActivityStateJobHandler() {
        List<AcitivityVo> acitivityVoList = activityDeploy.scanToDoActivityList(0L);
        if(acitivityVoList == null || acitivityVoList.isEmpty()){
            logger.info("没有需要从活动审核通过状态转换到进行中的状态，以及没有需要从进行中的状态转换到关闭的状态");
            return;
        }
        while(!acitivityVoList.isEmpty()) {
            for (AcitivityVo acitivityVo : acitivityVoList) {
                Integer state = acitivityVo.getState();
                switch (state) {
                    // 4表示已经通过了审核的状态，需要判断，然后将它变成进行中的状态
                    case 4:
                        Result passResult = stateHandler.doing(acitivityVo.getAcitivityId(), Constance.ActivityState.PASS);
                        logger.info("扫描活动状态为进行，结果:{}:activityId:{},activityName:{},creator:{}", passResult, acitivityVo.getAcitivityId(), acitivityVo.getActivityName(), acitivityVo.getCreator());
                        break;
                    // 表示正在运行中的活动，需要将其结尾时间同当前时间进行比较，如果结尾时间已过，表示活动过期，需要将活动关闭
                    case 5:
                        if (acitivityVo.getEndTime().before(new Date())) {
                            Result doResult = stateHandler.close(acitivityVo.getAcitivityId(), Constance.ActivityState.DOING);
                            logger.info("扫描活动状态为关闭，结果:{},activityId:{},activityName:{},creator:{}", doResult, acitivityVo.getAcitivityId(), acitivityVo.getActivityName(), acitivityVo.getCreator());
                        }
                        break;
                    default:
                        break;
                }
            }
            AcitivityVo acitivityVo = acitivityVoList.get(acitivityVoList.size() - 1);
            acitivityVoList = activityDeploy.scanToDoActivityList(acitivityVo.getAcitivityId());
        }
        logger.info("扫描结束，当前时间：{}", new Date());
    }

    @XxlJob("lotteryOrderMQStateJobHandler")
    public void lotteryActivityProcessJobHandler() {
        // 获取执行的参数
        String jobParam = XxlJobHelper.getJobParam();
        if(StringUtils.isBlank(jobParam)){
            logger.info("扫描用户抽奖奖品发放MQ状态[Table = 2*4] 错误 params is null");
            return;
        }
        String[] params = jobParam.split(",");
        logger.info("扫描用户抽奖奖品发放MQ状态[Table = 2*4] 开始 params：{}", JSON.toJSONString(params));

        if(params.length == 0){
            logger.info("扫描用户抽奖奖品发放MQ状态[Table = 2*4] 结束 params is null");
            return;
        }
        // 获取分表数
        int tbCount = dbRouterStrategy.tbCount();
        int dbCount = dbRouterStrategy.dbCount();
        for(String param:params){
            int dbIndex = Integer.parseInt(param);
            if(dbIndex >= dbCount){
                logger.error("参数错误，传输的数据库下标索引大于数据库总数");
                continue;
            }
            for(int i = 0;i<tbCount;i++){
                // 对对应的数据的mq状态进行扫描，提取出mq失败的
                List<InvoiceVO> invoiceVOList = activityDeploy.scanInvoiceMqState(dbIndex, i);
                if(invoiceVOList == null || invoiceVOList.isEmpty()){
                    continue;
                }
                for(InvoiceVO invoiceVO:invoiceVOList){
                    // 对失败的mq进行发送补偿，并进行更新状态
                    ListenableFuture<SendResult<String, Object>> future = kafkaProducer.send(invoiceVO);
                    future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
                        @Override
                        public void onFailure(Throwable ex) {
                            activityPartake.updateInvoiceMqState(invoiceVO.getuId(),invoiceVO.getOrderId(),Constance.MQState.FAIL.getCode());
                        }

                        @Override
                        public void onSuccess(SendResult<String, Object> result) {
                            activityPartake.updateInvoiceMqState(invoiceVO.getuId(),invoiceVO.getOrderId(),Constance.MQState.COMPLETE.getCode());
                        }
                    });
                }
            }
            logger.info("扫描用户抽奖奖品发放MQ状态[Table = 2*4] 完成 param：{}", JSON.toJSONString(params));
        }
    }
}
