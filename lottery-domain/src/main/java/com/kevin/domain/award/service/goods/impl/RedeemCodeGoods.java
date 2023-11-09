package com.kevin.domain.award.service.goods.impl;

import com.kevin.common.Constance;
import com.kevin.domain.award.model.req.GoodReq;
import com.kevin.domain.award.model.resp.DistributionRes;
import com.kevin.domain.award.service.goods.DistributionBase;
import com.kevin.domain.award.service.goods.DistributionGoods;
import org.springframework.stereotype.Service;

/**兑换码类商品
 * @author wang
 * @create 2023-2023-05-16:18
 */
@Service
public class RedeemCodeGoods extends DistributionBase implements DistributionGoods {
    @Override
    public DistributionRes doDistribution(GoodReq goodReq) {
        logger.info("模拟调用兑换码发奖 uId：{} awardContent：{}", goodReq.getUserId(), goodReq.getAwardContent());
        super.updateUserAwardState(goodReq.getUserId(), goodReq.getAwardId(), goodReq.getOrderId(),Constance.GrantState.COMPLETE.getCode());
        return new DistributionRes(goodReq.getUserId(),Constance.AwardState.SUCCESS.getCode(),Constance.AwardState.SUCCESS.getInfo());
    }

    @Override
    public Integer getDistributionGoodName() {
        return Constance.AwardType.RedeemCode.getCode();
    }
}
