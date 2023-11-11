package com.kevin.lottery.infrastructure.repository;

import com.kevin.domain.award.repository.AwardRepository;
import com.kevin.lottery.infrastructure.dao.UserStrategyExportMapper;
import com.kevin.lottery.infrastructure.po.UserStrategyExport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author wang sheng hui
* @description 针对表【award(奖品配置)】的数据库操作Service实现
* @createDate 2023-11-04 16:39:22
*/
@Component
public class AwardRepositoryImpl implements AwardRepository {

    @Resource
    private UserStrategyExportMapper userStrategyExportMapper;
    @Override
    public void updateUserAwardState(String uId, Long orderId, String awardId, Integer grantState) {
        UserStrategyExport userStrategyExport = new UserStrategyExport();
        userStrategyExport.setUid(uId);
        userStrategyExport.setOrderid(orderId);
        userStrategyExport.setAwardid(awardId);
        userStrategyExport.setGrantstate(grantState);

        userStrategyExportMapper.updateAwardState(userStrategyExport);
    }

    @Override
    public Integer getMqState(String userId, String awardId, Long orderId) {
        UserStrategyExport userStrategyExport = new UserStrategyExport();
        userStrategyExport.setUid(userId);
        userStrategyExport.setAwardid(awardId);
        userStrategyExport.setOrderid(orderId);
        return userStrategyExportMapper.getMqState(userStrategyExport);
    }
}




