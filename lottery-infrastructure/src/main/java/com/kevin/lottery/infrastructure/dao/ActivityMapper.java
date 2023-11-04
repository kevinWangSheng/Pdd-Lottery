package com.kevin.lottery.infrastructure.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kevin.lottery.infrastructure.po.Activity;
import org.apache.ibatis.annotations.Mapper;

/**
* @author wang sheng hui
* @description 针对表【activity(活动配置)】的数据库操作Mapper
* @createDate 2023-11-03 18:20:41
* @Entity generator.domain.Activity
*/
@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {


}




