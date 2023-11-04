package com.kevin.domain.strategy.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author wang
 * @create 2023-2023-04-18:18
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum DrawRateRandomDrawAlgorithmEnum {
    DefaultRateRandomDrawAlgorithm(1,"默认的随机概率生成，计算剩余概率"),
    SingleRateRandomDrawAlgorithm(2,"单独的随机概率，生成随机概率以后会判断该概率区间是否已经去除，去除返回未中奖，否者返回中奖id");
    private int code;

    private String desc;


}
