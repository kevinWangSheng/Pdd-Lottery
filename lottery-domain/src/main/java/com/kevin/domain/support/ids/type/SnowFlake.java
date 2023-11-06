package com.kevin.domain.support.ids.type;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import com.kevin.domain.support.ids.IIDGenerate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author wang
 * @create 2023-06-14:13
 */
@Component
public class SnowFlake implements IIDGenerate {
    private Snowflake snowFlake;

    @PostConstruct
    public void init(){
        long workId;

        try {
            workId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        } catch (Exception e) {
            workId = NetUtil.getLocalhost().hashCode();
        }
        workId = workId >>16 &31;
        long dateCenterId = 1l;
        snowFlake = IdUtil.createSnowflake(workId,dateCenterId);
    }
    @Override
    public Long nextId(){
        return snowFlake.nextId();
    }
}
