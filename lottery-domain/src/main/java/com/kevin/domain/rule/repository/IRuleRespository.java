package com.kevin.domain.rule.repository;

import com.kevin.domain.rule.model.aggregates.TreeRuleRich;

/**
 * @author wang
 * @create 2023-11-08-11:37
 */
public interface IRuleRespository {
    TreeRuleRich queryTreeRuleRich(Long treeId);
}
