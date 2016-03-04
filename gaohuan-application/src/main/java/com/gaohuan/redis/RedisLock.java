package com.gaohuan.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;

import java.util.Random;

/**
 * 跨jvm的锁实现
 */
public class RedisLock {
    /**
     * 锁标识
     */
    public static final String LOCKED = "true";
    /**
     * 纳秒到毫秒单位
     */
    public static final long ONE_MILLI_NANOS = 1000000l;
    /**
     * 默认超时时间
     */
    public static final long DEFAULT_TIME_OUT = 3000;

    public static JedisPool pool;

    public static final Random r = new Random();
    /**
     * 过期时间
     */
    public static final int EXPIRE = 5 * 60;

    static {
        pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1", 6379);
    }

    private Jedis jedis;

    private String key;

    //锁状态标识

    private boolean locked = false;

    public RedisLock(String key) {
        this.key = key;
        this.jedis = pool.getResource();
    }

    public boolean lock(long timeout) {
        long nano = System.nanoTime();
        timeout = timeout * ONE_MILLI_NANOS;
        try {
            while (System.nanoTime() - nano < timeout) {//未超时
                if (jedis.setnx(key, LOCKED) == 1) {
                    jedis.expire(key, EXPIRE);
                    locked = true;
                    return locked;
                }
                //避免活锁
                Thread.sleep(3, r.nextInt(500));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean lock() {
        return lock(DEFAULT_TIME_OUT);
    }

    public void unlock() {
        try {
            if (locked) {
                jedis.del(key);
            }
        } finally {
            pool.returnResource(jedis);
        }
    }

    public boolean lock2(long timeout) {
        long nano = System.nanoTime();
        timeout = timeout * ONE_MILLI_NANOS;
        try {
            while ((System.nanoTime() - nano) < timeout) {
                Transaction t = jedis.multi();
                t.getSet(key, LOCKED);
                t.expire(key, EXPIRE);
                String ret = (String) t.exec().get(0);
                if (ret == null || ret.equals("UNLOCK")) {
                    return true;
                }
                Thread.sleep(3, r.nextInt(500));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean lock3(long timeout) {
        long nano = System.nanoTime();
        timeout = timeout * ONE_MILLI_NANOS;

        try {
            while (System.nanoTime() - nano < timeout) {
                jedis.watch(key);
                String value = jedis.get(key);
                if (value == null || value.equals("UNLOCK")) {
                    Transaction t = jedis.multi();
                    t.setex(key, EXPIRE, LOCKED);
                    if (t.exec() != null) {
                        return true;
                    }
                }
                jedis.unwatch();
                Thread.sleep(3, r.nextInt(500));

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
