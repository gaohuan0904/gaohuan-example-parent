package com.gaohuan.thread.connection.pool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 简单连接池测试
 */
public class PoolTest {

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 30;
        int poolSize = 10;
        int count = 20;

        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();

        ConnectionPool pool = new ConnectionPool(poolSize);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new ConnectionRunner(start, end, pool, count, got, notGot), "thread-run-" + i);
            thread.start();
        }
        start.countDown();
        end.await();

        System.out.println("total invoke:" + (threadCount * count));
        System.out.println("got connection:" + got);
        System.out.println("notGot connection:" + notGot);

    }
}
