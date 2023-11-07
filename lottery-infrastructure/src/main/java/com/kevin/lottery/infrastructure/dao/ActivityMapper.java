package com.kevin.lottery.infrastructure.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kevin.domain.activity.model.vo.AlterStateVo;
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


    int alterStatus(AlterStateVo alterStateVo);

    Activity queryById(Long activityId);

    int subtractionActivityStock(Long activityId);
}




