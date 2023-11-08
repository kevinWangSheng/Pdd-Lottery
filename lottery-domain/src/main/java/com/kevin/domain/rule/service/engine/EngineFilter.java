package com.kevin.domain.rule.service.engine;

import com.kevin.domain.rule.model.req.DecisionMatterReq;
import com.kevin.domain.rule.model.rsep.EngineResult;

/**
 * @author wang
 * @create 2023-11-08-11:38
 */
public interface EngineFilter {
    EngineResult process(DecisionMatterReq req);
}
