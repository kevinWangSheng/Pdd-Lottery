package com.kevin.lottery.infrastructure.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kevin.lottery.infrastructure.po.RuleTreeNodeLine;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author wang sheng hui
* @description 针对表【rule_tree_node_line】的数据库操作Mapper
* @createDate 2023-11-08 10:43:37
* @Entity generator.domain.RuleTreeNodeLine
*/
@Mapper
public interface RuleTreeNodeLineMapper extends BaseMapper<RuleTreeNodeLine> {

    List<RuleTreeNodeLine> queryRuleTreeNodeLineList(RuleTreeNodeLine req);

    /**
     * 查询规则树连线数量
     *
     * @param treeId    规则树ID
     * @return          规则树连线数量
     */
    int queryTreeNodeLineCount(Long treeId);

}




