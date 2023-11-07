package com.kevin.lottery.infrastructure.dao;


import cn.bugstack.middleware.db.router.annotation.DBRouter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kevin.lottery.infrastructure.po.UserTakeActivity;
import org.apache.ibatis.annotations.Mapper;

/**
* @author wang sheng hui
* @description 针对表【user_take_activity(用户参与活动记录表)】的数据库操作Mapper
* @createDate 2023-11-06 21:14:11
* @Entity generator.domain.UserTakeActivity
*/
@Mapper
public interface UserTakeActivityMapper extends BaseMapper<UserTakeActivity> {

    @DBRouter(key = "uid")
    void insertOne(UserTakeActivity userTakeActivity);

    @DBRouter(key = "uid")
    UserTakeActivity  selectOne(String uid);

    int lockTackActivity(UserTakeActivity userTakeActivity);
}




