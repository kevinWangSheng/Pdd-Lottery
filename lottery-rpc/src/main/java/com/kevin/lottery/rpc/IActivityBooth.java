package com.kevin.lottery.rpc;


import com.baomidou.mybatisplus.extension.service.IService;
import com.kevin.lottery.infrastructure.po.Activity;
import com.kevin.lottery.rpc.req.ActivityReq;
import com.kevin.lottery.rpc.resp.ActivityResp;
import org.springframework.stereotype.Service;

/**
* @author wang sheng hui
* @description 针对表【activity(活动配置)】的数据库操作Service
* @createDate 2023-11-03 18:20:41
*/

public interface IActivityBooth extends IService<Activity> {
    ActivityResp queryById(ActivityReq req);
}
