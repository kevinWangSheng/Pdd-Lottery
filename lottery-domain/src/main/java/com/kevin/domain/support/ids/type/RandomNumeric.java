package com.kevin.domain.support.ids.type;

import com.kevin.domain.support.ids.IIDGenerate;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

/**
 * @author wang
 * @create 2023-2023-06-14:27
 */
@Component
public class RandomNumeric implements IIDGenerate {

    @Override
    public Long nextId() {
        return Long.parseLong(RandomStringUtils.randomNumeric(11));
    }
}
