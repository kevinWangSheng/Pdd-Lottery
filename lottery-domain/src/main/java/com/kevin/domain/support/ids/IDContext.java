package com.kevin.domain.support.ids;

import com.kevin.common.Constance;
import com.kevin.domain.support.ids.type.RandomNumeric;
import com.kevin.domain.support.ids.type.ShortCode;
import com.kevin.domain.support.ids.type.SnowFlake;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 Id 策略模式上下文配置「在正式的完整的系统架构中，ID 的生成会有单独的服务来完成，其他服务来调用 ID 生成接口即可」
 * @author wang
 * @create 2023-06-14:12
 */
@Component
public class IDContext {

    /**
     * 创建 ID 生成策略对象，属于策略设计模式的使用方式
     * Params:
     * snowFlake – 雪花算法，长码，大量 shortCode – 日期算法，短码，少量，全局唯一需要自己保证 randomNumeric – 随机算法，短码，大量，全局唯一需要自己保证
     * Returns:
     * IIdGenerator 实现类
     * @param randomNumeric
     * @param shortCode
     * @param snowFlake
     * @return
     */
    @Bean
    public Map<Constance.Ids, IIDGenerate> idGenerateMap(RandomNumeric randomNumeric, ShortCode shortCode, SnowFlake snowFlake){
        Map<Constance.Ids,IIDGenerate> map = new HashMap<>(8);
        map.put(Constance.Ids.RandomNumeric,randomNumeric);
        map.put(Constance.Ids.ShortCode,shortCode);
        map.put(Constance.Ids.SnowFlake,snowFlake);
        return map;
    }
}
