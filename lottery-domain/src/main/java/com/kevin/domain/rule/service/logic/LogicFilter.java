package com.kevin.domain.rule.service.logic;

import com.kevin.domain.rule.model.req.DecisionMatterReq;
import com.kevin.domain.rule.model.vo.TreeNodeLineVo;

import java.util.List;

/**
 * @author wang
 * @create 2023-11-08-11:41
 */
public interface LogicFilter {
    Long filter(String matterValue, List<TreeNodeLineVo> treeNodeLineVoList);

    String matterValue(DecisionMatterReq req);
}
