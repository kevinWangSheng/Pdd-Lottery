package com.kevin.domain.award.service.goods;

import com.kevin.domain.award.model.req.GoodReq;
import com.kevin.domain.award.model.resp.DistributionRes;

/**
 * @author wang
 * @create 2023-2023-05-15:43
 */
public interface DistributionGoods {
    // 奖品配送接口，奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）
    DistributionRes doDistribution(GoodReq goodReq);

    Integer getDistributionGoodName();
}
