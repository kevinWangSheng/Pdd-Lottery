package com.kevin.lottery.infrastructure.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kevin.lottery.infrastructure.po.Award;
import org.apache.ibatis.annotations.Mapper;

/**
* @author wang sheng hui
* @description 针对表【award(奖品配置)】的数据库操作Mapper
* @createDate 2023-11-04 16:39:22
* @Entity generator.domain.Award
*/
@Mapper
public interface AwardMapper extends BaseMapper<Award> {

}




