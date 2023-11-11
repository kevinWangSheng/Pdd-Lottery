package com.kevin.lottery.infrastructure.dao;


import cn.bugstack.middleware.db.router.annotation.DBRouter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kevin.lottery.infrastructure.po.UserTakeActivityCount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author wang sheng hui
* @description 针对表【user_take_activity_count(用户活动参与次数表)】的数据库操作Mapper
* @createDate 2023-11-07 12:57:46
* @Entity generator.domain.UserTakeActivityCount
*/
@Mapper
public interface UserTakeActivityCountMapper extends BaseMapper<UserTakeActivityCount> {

    @DBRouter(key = "uid")
    UserTakeActivityCount queryUserTakeActivityCount(UserTakeActivityCount userTakeActivityCountReq);

    void insertOne(UserTakeActivityCount userTakeActivityCount);

    int updateLeftCount(UserTakeActivityCount userTakeActivityCount);

    List<UserTakeActivityCount> scanInvoiceMqState();
}




