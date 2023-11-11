package com.kevin.lottery.application.controller;

import com.kevin.common.Result;
import com.kevin.lottery.application.process.IActivityProcess;
import com.kevin.lottery.application.process.req.DrawProcessReq;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wang
 * @create 2023-11-11-22:16
 */
@RestController
@RequestMapping("/process")
public class ProcessController {
    @Resource
    private IActivityProcess activityProcess;
    @GetMapping("/seckill")
    public Result seckill(DrawProcessReq req){
        return activityProcess.doDrawProcess(req);
    }
}
