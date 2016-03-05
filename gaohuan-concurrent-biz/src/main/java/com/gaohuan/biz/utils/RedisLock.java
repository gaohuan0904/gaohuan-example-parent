package com.gaohuan.biz.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 实现Redis锁，redis分布式锁
 */
@Component
public class RedisLock {

    public static final Logger logger = LoggerFactory.getLogger(RedisLock.class);
    //加锁标志
    public static final String LOCKED = "TRUE";
    public static final long ONE_MILLI_NANOS = 1000000L;
    //默认超时时间（毫秒）
    public static final long DEFAULT_TIME_OUT = 3000;
    public static final Random r = new Random();
    //锁的超时时间（秒），过期删除
    public static final int EXPIRE = 10;

    //锁状态标志
//    private volatile boolean locked = false;

    @Autowired
    public RedisTemplate redisTemplate;


    public boolean lock(String key, long timeout) {
        long nano = System.nanoTime();
        timeout = timeout * ONE_MILLI_NANOS;
        try {
            while ((System.nanoTime() - nano) < timeout) {
                if (redisTemplate.opsForValue().setIfAbsent(key, LOCKED)) {
                    redisTemplate.expire(key, EXPIRE, TimeUnit.SECONDS);
                    return true;
                }
                // 短暂休眠，nano避免出现活锁
                Thread.sleep(3, r.nextInt(500));
            }
        } catch (Exception e) {
            logger.error("获取锁出现异常!", e);
        }
        return false;
    }

    public boolean lock(String key) {
        return lock(key, DEFAULT_TIME_OUT);
    }

    // 无论是否加锁成功，必须调用
    public void unlock(String key) {
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            logger.error("释放锁出现异常!", e);
        }
    }


}
