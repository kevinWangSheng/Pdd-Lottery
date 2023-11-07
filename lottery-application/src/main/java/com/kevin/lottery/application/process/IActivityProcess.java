package com.kevin.lottery.application.process;

import com.kevin.lottery.application.process.req.DrawProcessReq;
import com.kevin.lottery.application.process.resp.DrawProcessResp;

/**
 * @author wang
 * @create 2023-11-07-18:47
 */
public interface IActivityProcess {
    DrawProcessResp doDrawProcess(DrawProcessReq req);
}
