package com.kevin.domain.activity.service.partake;

import com.kevin.common.Result;
import com.kevin.domain.activity.model.req.PartakeReq;
import com.kevin.domain.activity.model.resp.PartakeResult;
import com.kevin.domain.activity.model.vo.DrawOrderVO;

/**抽奖活动参与接口
 * @author wang
 * @create 2023-2023-06-0:55
 */
public interface IActivityPartake {
    PartakeResult doPartake(PartakeReq req);

    Result recordDrawOrder(DrawOrderVO drawOrderVO);

    /**
     * 更新发货单MQ状态
     *  @param uId      用户ID
     * @param orderId   订单ID
     * @param mqState   MQ 发送状态
     */
    void updateInvoiceMqState(String uId, Long orderId, Integer mqState);
}
