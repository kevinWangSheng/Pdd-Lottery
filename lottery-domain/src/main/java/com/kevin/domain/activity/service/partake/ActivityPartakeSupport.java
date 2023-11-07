package com.kevin.domain.activity.service.partake;

import com.kevin.domain.activity.model.req.PartakeReq;
import com.kevin.domain.activity.model.vo.ActivityBilVO;
import com.kevin.domain.activity.reporisitory.IActivityRepository;

import javax.annotation.Resource;

/**
 * @author wang
 * @create 2023-11-07-14:35
 */
public class ActivityPartakeSupport {
    @Resource
    protected IActivityRepository activityRepository;

    public ActivityBilVO queryActivityBill(PartakeReq req){ return activityRepository.queryActivityBill(req);}
}
