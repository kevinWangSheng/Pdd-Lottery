package com.kevin.domain.award.service.goods.impl;

import com.kevin.common.Constance;
import com.kevin.domain.award.model.req.GoodReq;
import com.kevin.domain.award.model.resp.DistributionRes;
import com.kevin.domain.award.model.resp.MqStateResp;
import com.kevin.domain.award.service.goods.DistributionBase;
import com.kevin.domain.award.service.goods.DistributionGoods;
import org.springframework.stereotype.Service;

/**优惠券商品
 * @author wang
 * @create 2023-2023-05-16:08
 */
@Service
public class CouponGoodsCouponGoods extends DistributionBase implements DistributionGoods {
    @Override
    public DistributionRes doDistribution(GoodReq goodReq) {
        logger.info("模拟调用优惠券发放接口 uId：{} awardContent：{}", goodReq.getUserId(), goodReq.getAwardContent());
        MqStateResp mqStateResp = super.updateUserAwardState(goodReq.getUserId(), goodReq.getAwardId(), goodReq.getOrderId(), Constance.GrantState.COMPLETE.getCode());
        if(Constance.MQState.ALREADY_COMPLETE.getCode().equals(mqStateResp.getMqState())){
            return new DistributionRes(goodReq.getUserId(),Constance.AwardState.DUPLICATE.getCode(),Constance.AwardState.DUPLICATE.getInfo());
        }
        return new DistributionRes(goodReq.getUserId(),Constance.AwardState.SUCCESS.getCode(),Constance.AwardState.SUCCESS.getInfo());
    }

    @Override
    public Integer getDistributionGoodName() {
        return Constance.AwardType.Coupon.getCode();
    }
}
