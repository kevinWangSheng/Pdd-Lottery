package com.kevin.domain.award.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author wang
 * @create 2023-2023-05-15:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ShippingAddress {
    // 收获人
    private String name;
    // 一级地址id
    private String provinceId;
    // 一级地址名称
    private String provinceName;
    // 二级地址id
    private String cityId;
    // 二级地址名称
    private String cityName;
    // 三级地址id
    private String countyId;
    // 三级地址名称
    private String countyName;
    // 四级地址id
    private String townId;
    // 四级地址名称
    private String townName;
    // 详细地址
    private String address;
    // 手机号
    private String phone;
    // 邮箱
    private String email;
    // 备注
    private String remark;
}
