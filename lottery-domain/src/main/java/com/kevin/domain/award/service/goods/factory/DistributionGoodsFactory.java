package com.kevin.domain.award.service.goods.factory;

import com.kevin.domain.award.service.goods.DistributionGoods;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wang
 * @create 2023-2023-05-16:31
 */
@Service
public class DistributionGoodsFactory extends GoodsConfig {

    public DistributionGoods  getDistributionGoods(Integer goodsType) {
        return goodMap.get(goodsType);
    }
}
