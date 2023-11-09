package com.kevin.domain.award.repository;




/**
* @author wang sheng hui
* @description 针对表【award(奖品配置)】的数据库操作Service
* @createDate 2023-11-04 16:39:22
*/
public interface AwardRepository  {


    void updateUserAwardState(String uId, Long orderId, String awardId, Integer grantState);

}
