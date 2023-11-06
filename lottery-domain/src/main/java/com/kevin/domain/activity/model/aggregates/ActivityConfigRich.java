package com.kevin.domain.activity.model.aggregates;

import com.kevin.domain.activity.model.vo.AcitivityVo;
import com.kevin.domain.activity.model.vo.AwardVo;
import com.kevin.domain.activity.model.vo.StrategyVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**活动配置聚合信息
 * @author wang
 * @create 2023-2023-05-19:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ActivityConfigRich {

    //活动配置信息
    private AcitivityVo acitivityVo;

    //策略配置
    private StrategyVo strategyVo;

    //奖品配置
    private List<AwardVo> awardVoList;
}
