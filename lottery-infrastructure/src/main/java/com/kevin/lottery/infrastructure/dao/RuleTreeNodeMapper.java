package com.kevin.lottery.infrastructure.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kevin.lottery.infrastructure.po.RuleTreeNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author wang sheng hui
* @description 针对表【rule_tree_node】的数据库操作Mapper
* @createDate 2023-11-08 10:43:37
* @Entity generator.domain.RuleTreeNode
*/
@Mapper
public interface RuleTreeNodeMapper extends BaseMapper<RuleTreeNode> {

    List<RuleTreeNode> queryRuleTreeNodeList(Long treeId);

    /**
     * 查询规则树节点数量
     * @param treeId    规则树ID
     * @return          节点数量
     */
    int queryTreeNodeCount(Long treeId);

    /**
     * 查询规则树节点
     *
     * @param treeId    规则树ID
     * @return          节点集合
     */
    List<RuleTreeNode> queryTreeRulePoint(Long treeId);

}




