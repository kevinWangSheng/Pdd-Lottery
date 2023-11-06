package com.kevin.domain.award.service.goods.impl;

import com.kevin.common.Constance;
import com.kevin.domain.award.model.req.GoodReq;
import com.kevin.domain.award.model.resp.DistributionRes;
import com.kevin.domain.award.service.goods.DistributionBase;
import com.kevin.domain.award.service.goods.DistributionGoods;
import org.springframework.stereotype.Service;

/**实体类发货商品
 * @author wang
 * @create 2023-2023-05-16:18
 */
@Service
public class PhysicalGoods extends DistributionBase implements DistributionGoods {
    @Override
    public DistributionRes doDistribution(GoodReq goodReq) {
        logger.info("模拟调用实物发奖 uId：{} awardContent：{}", goodReq.getUserId(), goodReq.getAwardContent());
        super.updateUserAwardState(goodReq.getUserId(), goodReq.getAwardId(), Constance.AwardState.SUCCESS.getCode(),goodReq.getOrderId(),Constance.AwardState.SUCCESS.getInfo());
        return new DistributionRes(goodReq.getUserId(),Constance.AwardState.SUCCESS.getCode(),Constance.AwardState.SUCCESS.getInfo());
    }

    @Override
    public Integer getDistributionGoodName() {
        return Constance.AwardType.Physical.getCode();
    }
}