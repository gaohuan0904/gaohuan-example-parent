package com.gaohuan.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by huan on 2016/2/18.
 */
public class JedisUtils {
    private static JedisPool pool;

    public static void main(String[] args) {

    }

    public static synchronized Jedis createJedis(){

        if(pool==null){
            // 建立连接池配置参数
            JedisPoolConfig config = new JedisPoolConfig();
            // 设置最大连接数
            config.setMaxTotal(100);
            // 设置最大阻塞时间，记住是毫秒数milliseconds
            config.setMaxWaitMillis(1000);
            // 设置空间连接
            config.setMaxIdle(10);
            // 创建连接池
            pool = new JedisPool(config, "127.0.0.1", 6379);
        }

        return pool.getResource();
    }

    public static void returnResource(Jedis jedis){
        pool.returnResource(jedis);
    }

}
