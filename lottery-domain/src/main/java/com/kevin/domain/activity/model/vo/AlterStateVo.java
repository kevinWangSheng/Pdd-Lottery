package com.kevin.domain.activity.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author wang
 * @create 2023-2023-05-19:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AlterStateVo {

    private Long acitivityId;

    private Integer beforeState;

    private Integer afterState;
}
