package com.kevin.lottery.infrastructure.util;

import cn.hutool.core.collection.CollectionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * @author wang
 * @create 2023-11-11-21:21
 */
@Component
public class RedisUtils {
    @Resource
    RedisTemplate<String,Object> redisTemplate;

    public RedisUtils(RedisTemplate<String,Object>redisTemplate){
        this.redisTemplate=redisTemplate;
    }


    public boolean expire(String key, long time){
        try {
            if(time>0){
                redisTemplate.expire(key,time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     * @param key 对应的key
     * @return 返回key是否存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void del(String... keys){
        if(keys!= null && keys.length>0){
            if(keys.length == 1){
                redisTemplate.delete(keys[0]);
            }else{
                redisTemplate.delete((Collection<String>)CollectionUtils.arrayToList(keys));
            }
        }
    }

    public Object get(String key){
        return StringUtils.isBlank(key)? key: redisTemplate.opsForValue().get(key);
    }

    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean set(String key,Object value,long time){
        try {
            if (time > 0 && StringUtils.isNotBlank(key) && value != null) {
                redisTemplate.opsForValue().set(key,value,time,TimeUnit.SECONDS);
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setNx(String key,long time){
        return (boolean)redisTemplate.execute((RedisCallback) connection->{
            return connection.setNX(key.getBytes(),String.valueOf(System.currentTimeMillis() + time + 1).getBytes());
        });
    }

    public long incr(String key){
        return redisTemplate.opsForValue().increment(key);
    }

    public long incr(String key,long delta){
        if(delta<0){
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key,delta);
    }

    public long decr(String key){
        return redisTemplate.opsForValue().increment(key,-1);
    }

    public long decr(String key,long delta){
        if(delta<0){
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key,-delta);
    }
}
