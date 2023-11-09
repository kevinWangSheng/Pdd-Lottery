package com.kevin.lottery.rpc;

import com.kevin.lottery.rpc.req.DrawReq;
import com.kevin.lottery.rpc.req.QuantificationDrawReq;
import com.kevin.lottery.rpc.resp.DrawResp;

/** 抽奖接口门面
 * @author wang
 * @create 2023-11-09-13:15
 */
public interface ILotteryActivityBooth {
    DrawResp doDraw(DrawReq req);

    DrawResp doQuantificationDraw(QuantificationDrawReq req);
}
