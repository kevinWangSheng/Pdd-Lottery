package com.kevin.lottery.rpc.resp;

import com.kevin.common.Result;
import com.kevin.lottery.infrastructure.po.Activity;
import com.kevin.lottery.rpc.dto.ActivityDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wang
 * @create 2023-2023-03-18:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityResp {
    private Result result;

    private ActivityDto activity;
}
