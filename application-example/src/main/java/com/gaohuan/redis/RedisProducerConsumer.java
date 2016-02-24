package com.gaohuan.redis;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * 使用redis实现生产者消费者模式
 */
public class RedisProducerConsumer {

    public static void main(String[] args) {
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
    }


    static class Consumer implements Runnable {
        Jedis jedis = JedisUtils.createJedis();

        @Override
        public void run() {
            String key = "demo:mq";
            while (true) {
                // block invoke
                List<String> msgs = jedis.brpop(30, key);
                if (msgs == null) continue;
                String jobMsg = msgs.get(1);
                System.out.println("consumer: " + jobMsg);

            }
        }
    }

    static class Producer implements Runnable {
        Jedis jedis = JedisUtils.createJedis();

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                String key = "demo:mq";
                String msg = "hello mq";
                jedis.lpush(key, msg + "-" + i);
                System.out.println("producer:" + msg + "-" + i);
            }

        }
    }
}
