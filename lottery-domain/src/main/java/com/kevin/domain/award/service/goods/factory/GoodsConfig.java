package com.kevin.domain.award.service.goods.factory;

import com.kevin.common.Constance;
import com.kevin.domain.award.service.goods.DistributionGoods;
import com.kevin.domain.award.service.goods.impl.CouponGoodsCouponGoods;
import com.kevin.domain.award.service.goods.impl.DescGoods;
import com.kevin.domain.award.service.goods.impl.PhysicalGoods;
import com.kevin.domain.award.service.goods.impl.RedeemCodeGoods;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Console;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wang
 * @create 2023-2023-05-16:27
 */
public class GoodsConfig {
    protected static Map<Integer, DistributionGoods> goodMap = new ConcurrentHashMap<>();

    @Resource
    private DescGoods descGoods;

    @Resource
    private CouponGoodsCouponGoods couponGoodsCouponGoods;

    @Resource
    private PhysicalGoods physicalGoods;

    @Resource
    private RedeemCodeGoods redeemCodeGoods;

    @PostConstruct
    public void init(){
        goodMap.put(Constance.AwardType.Coupon.getCode(),couponGoodsCouponGoods);
        goodMap.put(Constance.AwardType.Desc.getCode(),descGoods);
        goodMap.put(Constance.AwardType.Physical.getCode(),physicalGoods);
        goodMap.put(Constance.AwardType.RedeemCode.getCode(),redeemCodeGoods);
    }
}
