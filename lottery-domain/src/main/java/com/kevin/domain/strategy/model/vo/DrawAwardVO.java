package com.kevin.domain.strategy.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author wang
 * @create 2023-2023-05-9:12
 */
@Data
@NoArgsConstructor
public class DrawAwardVO {
    private String uId;

    private String awardId;

    private String awardName;

    private String awardContent;

    private Integer awardType;

    private Integer strategyMode;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getAwardId() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getAwardContent() {
        return awardContent;
    }

    public void setAwardContent(String awardContent) {
        this.awardContent = awardContent;
    }

    public Integer getAwardType() {
        return awardType;
    }

    public void setAwardType(Integer awardType) {
        this.awardType = awardType;
    }

    public Integer getStrategyMode() {
        return strategyMode;
    }

    public void setStrategyMode(Integer strategyMode) {
        this.strategyMode = strategyMode;
    }

    public Integer getGrantType() {
        return grantType;
    }

    public void setGrantType(Integer grantType) {
        this.grantType = grantType;
    }

    public Date getGrantDate() {
        return grantDate;
    }

    public void setGrantDate(Date grantDate) {
        this.grantDate = grantDate;
    }

    /**
     * 发放奖品方式（1:即时、2:定时[含活动结束]、3:人工）
     */
    private Integer grantType;
    /**
     * 发奖时间
     */
    private Date grantDate;



    public DrawAwardVO(String awardId, String awardName, String awardContent, Integer awardType) {
        this.awardId = awardId;
        this.awardName = awardName;
        this.awardContent = awardContent;
        this.awardType = awardType;
    }

    public DrawAwardVO(String awardId, String awardName, String awardContent, Integer awardType, Integer strategyMode, Integer grantType, Date grantDate) {
        this.awardId = awardId;
        this.awardName = awardName;
        this.awardContent = awardContent;
        this.awardType = awardType;
        this.strategyMode = strategyMode;
        this.grantType = grantType;
        this.grantDate = grantDate;
    }
}
