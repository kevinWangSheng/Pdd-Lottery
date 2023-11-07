package com.kevin.domain.activity.model.resp;

import com.kevin.common.Result;
import lombok.Data;

/**
 * @author wang
 * @create 2023-11-07-14:42
 */
@Data
public class PartakeResult extends Result {

    private Long strategyId;


    public PartakeResult(Integer code, String info) {
        super(code, info);
    }
}
