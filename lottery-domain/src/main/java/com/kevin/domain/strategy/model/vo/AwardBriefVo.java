package com.kevin.domain.strategy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author wang
 * @create 2023-2023-05-20:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AwardBriefVo {
    private String awardId;

    private String awardName;

    private String awardDesc;

    private Integer awardType;
}
