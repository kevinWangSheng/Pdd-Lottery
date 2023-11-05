package com.kevin.domain.award.model.req;

import com.kevin.domain.award.model.vo.ShippingAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**奖品发货信息
 * @author wang
 * @create 2023-2023-05-15:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class GoodReq{
    // 用户id
    private String userId;
    // 订单id
    private String orderId;
    // 奖品id
    private String awardId;
    // 奖品名称
    private String awardName;
    // 奖品内容
    private String awardContent;
    // 订单状态
    private String orderStatus;
    // 发货地址信息
    private ShippingAddress shippingAddress;
    // 其他信息
    private String extendInfo;

    public GoodReq(String userId, String orderId, String awardId, String awardName, String awardContent, String orderStatus) {
        this.userId = userId;
        this.orderId = orderId;
        this.awardId = awardId;
        this.awardName = awardName;
        this.awardContent = awardContent;
        this.orderStatus = orderStatus;
    }

    public GoodReq(String userId, String orderId, String awardId, String awardName, String awardContent, String orderStatus, String extendInfo) {
        this.userId = userId;
        this.orderId = orderId;
        this.awardId = awardId;
        this.awardName = awardName;
        this.awardContent = awardContent;
        this.orderStatus = orderStatus;
        this.extendInfo = extendInfo;
    }

    public GoodReq(String userId, String orderId, String awardId, String awardName, String awardContent) {
        this.userId = userId;
        this.orderId = orderId;
        this.awardId = awardId;
        this.awardName = awardName;
        this.awardContent = awardContent;
    }
}
