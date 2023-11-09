package com.kevin.lottery.infrastructure.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
import cn.bugstack.middleware.db.router.annotation.DBRouterStrategy;
import com.kevin.lottery.infrastructure.po.UserStrategyExport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author wang sheng hui
* @description 针对表【user_strategy_export_001(用户策略计算结果表)】的数据库操作Mapper
* @createDate 2023-11-06 21:14:11
* @Entity generator.domain.UserStrategyExport001
*/
@Mapper
@DBRouterStrategy(splitTable = true)
public interface UserStrategyExportMapper extends BaseMapper<UserStrategyExport> {

    @DBRouter(key = "uid")
    UserStrategyExport selectByUserId(String userId);

    @DBRouter(key = "uid")
    void insertSelective (UserStrategyExport userStrategyExport);

    @DBRouter(key = "uid")
    void updateAwardState (UserStrategyExport userStrategyExport);

    @DBRouter(key = "uid")
    void updateInvoiceMqState(UserStrategyExport userStrategyExport);
}




