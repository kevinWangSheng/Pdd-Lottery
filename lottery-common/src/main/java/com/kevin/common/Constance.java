package com.kevin.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wang
 * @create 2023-2023-03-17:35
 */
public class Constance {

    public enum ResponseCode{
        SUCCESSFUL(0000,"成功"),
        UN_ERROR(0001,"未知失败"),
        ILEEGLE_ERROR(0002,"非法参数错误"),
        INDEX_DUP(0003,"主键冲突");
        private int code;
        private String desc;



        ResponseCode() {
        }

        ResponseCode(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    @Getter
    @AllArgsConstructor
    public enum StrategyMode{
        SINGLE(1,"单次概率"),
        ENTIRETY(2,"总体概率");
        private Integer code;
        private String info;
    }

    @Getter
    @AllArgsConstructor
    public enum DrawState{
        FAIL(0,"未中奖"),
        SUCCESS(1,"已中奖"),
        COVER(2,"兜底奖");
        private Integer code;
        private String info;
    }

    @Getter
    @AllArgsConstructor
    public enum AwardState {
        WAIT(0, "等待发奖"),

        /**
         * 发奖成功
         */
        SUCCESS(1, "发奖成功"),

        /**
         * 发奖失败
         */
        FAILURE(2, "发奖失败");
        private Integer code;
        private String info;

    }

    @Getter
    @AllArgsConstructor
    public enum AwardType{
        Coupon(1,"优惠券"),
        Desc(2,"文字描述"),
        Physical(3,"实物奖品"),
        RedeemCode(4,"兑换码");
        private Integer code;
        private String info;
    }
}
