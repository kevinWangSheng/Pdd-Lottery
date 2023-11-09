package com.kevin.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wang
 * @create 2023-2023-03-17:35
 */
public class Constance {

    public enum ResponseCode{
        SUCCESSFUL(0,"成功"),
        UN_ERROR(500,"未知失败"),
        ILEEGLE_ERROR(501,"非法参数错误"),
        INDEX_DUP(100,"主键冲突"),
        LOSING_AWARD(505,"抽奖失败"),

        PARAMERROR(506,"参数错误"),
        UNLL_ERROR(507,"空指针错误"),
        RULE_ERR(508, "量化人群规则执行失败"),
        NO_UPDATE(502,"SQL操作无更新");
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
    @Getter
    @AllArgsConstructor
    public enum ActivityState {
        EDIT(1, "编辑"),
        /** 2：提审 */
        ARRAIGNMENT(2, "提审"),
        /** 3：撤审 */
        REVOKE(3, "撤审"),
        /** 4：通过 */
        PASS(4, "通过"),
        /** 5：运行(活动中) */
        DOING(5, "运行(活动中)"),
        /** 6：拒绝 */
        REFUSE(6, "拒绝"),
        /** 7：关闭 */
        CLOSE(7, "关闭"),
        /** 8：开启 */
        OPEN(8, "开启");

        private Integer code;
        private String info;
    }

    /**
     * 算法生成策略枚举
     */
    public enum Ids {
        /** 雪花算法 */
        SnowFlake,
        /** 日期算法 */
        ShortCode,
        /** 随机算法 */
        RandomNumeric;
    }

    public enum GrantState{

        INIT(0, "初始"),
        COMPLETE(1, "完成"),
        FAIL(2, "失败");

        private Integer code;
        private String info;

        GrantState(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }


    public static final class Global {
        /** 空节点值 */
        public static final Long TREE_NULL_NODE = 0L;
    }

    public static final class RuleLimitType {
        /** 等于 */
        public static final int EQUAL = 1;
        /** 大于 */
        public static final int GT = 2;
        /** 小于 */
        public static final int LT = 3;
        /** 大于&等于 */
        public static final int GE = 4;
        /** 小于&等于 */
        public static final int LE = 5;
        /** 枚举 */
        public static final int ENUM = 6;
    }

    /**
     * 决策树节点
     */
    public static final class NodeType{
        /** 树茎 */
        public static final Integer STEM = 1;
        /** 果实 */
        public static final Integer FRUIT = 2;
    }

    public static class Matter{
        public static String GENDER = "gender";

        public static String AGE = "age";

        public static String USER_AGE = "userAge";

        public static String USER_GENDER = "userGender";
    }


}
