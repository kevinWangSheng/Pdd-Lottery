package com.kevin.domain.strategy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wang
 * @create 2023-2023-05-9:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrawAwardInfo {
    private String awardId;
    private String awardName;

    private String awardContent;

    private Integer awardType;
}
