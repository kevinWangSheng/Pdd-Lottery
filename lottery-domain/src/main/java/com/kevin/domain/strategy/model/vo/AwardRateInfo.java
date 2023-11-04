package com.kevin.domain.strategy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**主要用于抽奖的概率信息，包括某一个产品中奖的概率
 * @author wang
 * @create 2023-2023-04-16:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AwardRateInfo {
    // 奖品id
    private String awardId;
    //中奖概率
    private BigDecimal awardRate;

}
