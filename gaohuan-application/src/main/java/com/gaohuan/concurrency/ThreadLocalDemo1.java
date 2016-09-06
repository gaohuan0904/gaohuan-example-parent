package com.gaohuan.concurrency;

/**
 * ThreadLocal:为变量在每个线程中创建一个独立的副本，每个线程都可以访问自己内部的副本
 * 应用场景：常用来解决数据库连接 Session管理等
 *
 *
 *
 */
public class ThreadLocalDemo1 {
    ThreadLocal<Long> longThreadLocal = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            return Thread.currentThread().getId();
        }
    };
    ThreadLocal<String> stringThreadLocal = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return Thread.currentThread().getName();
        }
    };

    public void set() {
        longThreadLocal.set(Thread.currentThread().getId());
        stringThreadLocal.set(Thread.currentThread().getName());
    }

    public void get() {
        System.out.println(longThreadLocal.get());
        System.out.println(stringThreadLocal.get());
    }

    public static void main(String[] args) {
        ThreadLocalDemo1 localDemo = new ThreadLocalDemo1();
        localDemo.get();
        new Thread(() -> {
            localDemo.get();
        }).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        localDemo.get();

    }
}
