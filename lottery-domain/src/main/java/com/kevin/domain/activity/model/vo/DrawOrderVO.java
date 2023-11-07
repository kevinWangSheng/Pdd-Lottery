package com.kevin.domain.activity.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author wang
 * @create 2023-11-07-19:10
 */
@Data
public class DrawOrderVO {

    private String uId;


    private Long activityId;

    private Long orderId;

    private Long strategyId;

    private Integer strategyMode;
    /**
     * 发放奖品方式（1:即时、2:定时[含活动结束]、3:人工）
     */
    private Integer grantType;
    /**
     * 发奖时间
     */
    private Date grantDate;
    /**
     * 发奖状态
     */
    private Integer grantState;
    /**
     * 发奖ID
     */
    private String awardId;
    /**
     * 奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）
     */
    private Integer awardType;
    /**
     * 奖品名称
     */
    private String awardName;
    /**
     * 奖品内容「文字描述、Key、码」
     */
    private String awardContent;

    private Long takeId;
}
