package com.kevin.domain.strategy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author wang
 * @create 2023-2023-05-9:12
 */
@Data
@NoArgsConstructor
public class DrawAwardInfo {
    private String awardId;
    private String awardName;

    private String awardContent;

    private Integer awardType;

    private Integer strategyMode;

    /**
     * 发放奖品方式（1:即时、2:定时[含活动结束]、3:人工）
     */
    private Integer grantType;
    /**
     * 发奖时间
     */
    private Date grantDate;



    public DrawAwardInfo(String awardId, String awardName, String awardContent, Integer awardType) {
        this.awardId = awardId;
        this.awardName = awardName;
        this.awardContent = awardContent;
        this.awardType = awardType;
    }

    public DrawAwardInfo(String awardId, String awardName, String awardContent, Integer awardType, Integer strategyMode, Integer grantType, Date grantDate) {
        this.awardId = awardId;
        this.awardName = awardName;
        this.awardContent = awardContent;
        this.awardType = awardType;
        this.strategyMode = strategyMode;
        this.grantType = grantType;
        this.grantDate = grantDate;
    }
}
