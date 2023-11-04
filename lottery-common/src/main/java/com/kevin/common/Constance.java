package com.kevin.common;

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
}
