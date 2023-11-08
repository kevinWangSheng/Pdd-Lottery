package com.kevin.lottery.infrastructure.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kevin.lottery.infrastructure.po.RuleTree;
import org.apache.ibatis.annotations.Mapper;

/**
* @author wang sheng hui
* @description 针对表【rule_tree】的数据库操作Mapper
* @createDate 2023-11-08 10:43:37
* @Entity generator.domain.RuleTree
*/
@Mapper
public interface RuleTreeMapper extends BaseMapper<RuleTree> {
    RuleTree queryRuleTreeByTreeId(Long id);

    /**
     * 规则树简要信息查询
     * @param treeId 规则树ID
     * @return       规则树
     */
    RuleTree queryTreeSummaryInfo(Long treeId);

}




