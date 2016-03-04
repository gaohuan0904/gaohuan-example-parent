package com.gaohuan.biz.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * Created by gh on 2016/3/4 0004.
 */
@Component("redisExample")
public class RedisUtils {


    // inject the actual template
    @Resource(name = "stringRedisTemplate")
    private RedisTemplate<String, String> stringRedisTemplate;


    public void test() {


    }
}