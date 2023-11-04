package com.kevin.domain.strategy.service.draw;

import com.kevin.domain.strategy.model.req.DrawReq;
import com.kevin.domain.strategy.model.resp.DrawResp;

/**
 * @author wang
 * @create 2023-2023-04-18:32
 */
public interface IDrawExec {

    DrawResp doDrawExec(DrawReq drawreq);
}
