package com.kevin.domain.activity.model.resp;

import com.kevin.common.Result;
import lombok.Data;

/**
 * @author wang
 * @create 2023-11-07-14:42
 */
@Data
public class PartakeResult extends Result {
    /** 策略ID */
    private Long strategyId;
    /** 活动领取ID */
    private Long takeId;
    /** 库存 */
    private Integer stockCount;
    /** activity 库存剩余 */
    private Integer stockSurplusCount;


    public PartakeResult(Integer code, String info) {
        super(code, info);
    }

    public PartakeResult(Integer code, String info, Long strategyId, Long takeId) {
        super(code, info);
        this.strategyId = strategyId;
        this.takeId = takeId;
    }

    public PartakeResult(Long strategyId, Long takeId) {
        this.strategyId = strategyId;
        this.takeId = takeId;
    }
}
