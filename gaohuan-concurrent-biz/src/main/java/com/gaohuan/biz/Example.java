package com.gaohuan.biz;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


/**
 * Created by gh on 2016/3/4 0004.
 */
@Component("example")
public class Example {

    // inject the actual template
//    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // inject the template as ListOperations
    // can also inject as Value, Set, ZSet, and HashOperations
//    @Resource(name="redisTemplate")
//    private ListOperations<String, String> listOps;

    public void addLink(String userId, String url) {
//        redisTemplate.leftPush(userId, url);
        // or use template directly
//        template.boundListOps(userId).leftPush(url);
//        ListOperations listOperations= (ListOperations) new StringRedisTemplate();
    }
}