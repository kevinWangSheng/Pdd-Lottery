package com.kevin.lottery.controller;

import com.kevin.lottery.rpc.IActivityBooth;
import com.kevin.lottery.rpc.req.ActivityReq;
import com.kevin.lottery.rpc.resp.ActivityResp;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wang
 * @create 2023-2023-03-22:36
 */
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @DubboReference
    private IActivityBooth activityBooth;

    @GetMapping("/getById")
    public ActivityResp getById(ActivityReq activityReq){
        return activityBooth.queryById(activityReq);
    }
}
