package com.gaohuan.thread.connection.pool;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by huan on 2016/2/9.
 */
public class ConnectionRunner implements Runnable {

    private CountDownLatch start;

    private CountDownLatch end;

    private int count;

    private AtomicInteger got;

    private AtomicInteger notGot;

    private ConnectionPool pool;

    public ConnectionRunner(CountDownLatch start, CountDownLatch end, ConnectionPool pool, int count, AtomicInteger got, AtomicInteger notGot) {
        this.start = start;
        this.end = end;
        this.pool = pool;
        this.count = count;
        this.got = got;
        this.notGot = notGot;
    }

    @Override
    public void run() {

        if (start != null) {
            try {
                start.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (count > 0) {
            try {
                Connection connection = pool.fetchConnection(1000);
                if (connection != null) {
                    try {
                        connection.createStatement();
                        connection.commit();
                    } finally {
                        pool.releaseConnection(connection);
                        got.incrementAndGet();
                    }
                } else {
                    notGot.incrementAndGet();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                count--;
            }
        }
        end.countDown();
    }
}
