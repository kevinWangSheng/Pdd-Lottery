package com.kevin.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wang
 * @create 2023-2023-03-17:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {
    private Integer code;

    private String info;



    public static Result buildResult(Integer code, String info) {
        return  new Result(code,info);
    }

    public static Result buildSuccessResult() {
        return new Result(Constance.ResponseCode.SUCCESSFUL.getCode(), Constance.ResponseCode.SUCCESSFUL.getDesc());
    }

    public static Result buildFailResult() {
        return new Result(Constance.ResponseCode.UN_ERROR.getCode(), Constance.ResponseCode.UN_ERROR.getDesc());
    }
}
