package com.kevin.domain.activity.service.deploy;

import com.kevin.domain.activity.model.req.ActivityConfigReq;

/**
 * @author wang
 * @create 2023-2023-06-0:54
 */
public interface IActivityDeploy {

    void addActivity(ActivityConfigReq req);

    void updateActivity(ActivityConfigReq req);
}
