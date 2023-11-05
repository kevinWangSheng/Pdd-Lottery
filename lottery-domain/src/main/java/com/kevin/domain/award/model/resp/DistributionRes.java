package com.kevin.domain.award.model.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author wang
 * @create 2023-2023-05-15:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DistributionRes {
    // 用户id
    private String uId; // 用户ID
    //编码
    private Integer code;
    // 描述
    private String info;
    // 结算id,如：发券后有券码、发货后有单号等，用于存根查询
    private String statementId;

    public DistributionRes(String uId, Integer code, String info) {
        this.uId = uId;
        this.code = code;
        this.info = info;
    }
}
