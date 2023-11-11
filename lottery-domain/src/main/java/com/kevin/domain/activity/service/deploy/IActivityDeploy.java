package com.kevin.domain.activity.service.deploy;

import com.kevin.domain.activity.model.req.ActivityConfigReq;
import com.kevin.domain.activity.model.vo.AcitivityVo;
import com.kevin.domain.activity.model.vo.InvoiceVO;

import java.util.List;

/**
 * @author wang
 * @create 2023-2023-06-0:54
 */
public interface IActivityDeploy {

    void addActivity(ActivityConfigReq req);

    void updateActivity(ActivityConfigReq req);

    List<AcitivityVo> scanToDoActivityList(Long l);

    List<InvoiceVO> scanInvoiceMqState(int dbIndex, int tbIndex);
}
