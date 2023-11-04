package com.kevin.domain.strategy.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kevin.domain.strategy.repository.AwardRepository;
import com.kevin.lottery.infrastructure.dao.AwardMapper;
import com.kevin.lottery.infrastructure.po.Award;
import org.springframework.stereotype.Service;

/**
* @author wang sheng hui
* @description 针对表【award(奖品配置)】的数据库操作Service实现
* @createDate 2023-11-04 16:39:22
*/
@Service
public class AwardRepositoryImpl extends ServiceImpl<AwardMapper, Award>
    implements AwardRepository {

    @Override
    public Award getByAwardId(String awardId) {
        if(awardId == null){
            return null;
        }
        QueryWrapper<Award> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Award::getId, awardId);
        return getOne(wrapper);
    }
}




