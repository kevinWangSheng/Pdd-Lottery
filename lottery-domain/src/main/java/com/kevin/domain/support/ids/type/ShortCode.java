package com.kevin.domain.support.ids.type;

import com.kevin.domain.support.ids.IIDGenerate;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Random;

/**
 短码生成策略，仅支持很小的调用量，用于生成活动配置类编号，保证全局唯一
 * @author wang
 * @create 2023-06-14:27
 */
@Component
public class ShortCode implements IIDGenerate {

    @Override
    public Long nextId() {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        StringBuilder sb = new StringBuilder();

        sb.append(year-2020).append(hour).append(String.format("%02d",week)).append(day).append(String.format("%03d",new Random().nextInt(1000)));

        return Long.parseLong(sb.toString());
    }
}
