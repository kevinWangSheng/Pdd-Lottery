package com.kevin.lottery.application.process;

import com.kevin.domain.rule.model.req.DecisionMatterReq;
import com.kevin.lottery.application.process.req.DrawProcessReq;
import com.kevin.lottery.application.process.resp.DrawProcessResp;
import com.kevin.lottery.application.process.resp.RuleQuantificationCrowdResult;

/**
 * @author wang
 * @create 2023-11-07-18:47
 */
public interface IActivityProcess {
    DrawProcessResp doDrawProcess(DrawProcessReq req);

    RuleQuantificationCrowdResult doRuleQuantificationCrowd(DecisionMatterReq req);
}
