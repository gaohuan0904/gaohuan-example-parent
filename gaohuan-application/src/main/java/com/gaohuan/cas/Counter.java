package com.gaohuan.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gh on 2015/12/11.
 */
public class Counter {
    private AtomicInteger atomici = new AtomicInteger(0);
    private int i = 0;

    public static void main(String[] args) {
        final Counter counter = new Counter();
        List<Thread> threadList = new ArrayList<Thread>(600);
        long start = System.currentTimeMillis();
        for (int j = 0; j < 100; j++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        counter.safeCount();
                        counter.unsafeCount();
                    }
                }
            });
            threadList.add(t);
        }
        //启动线程
        for (Thread t : threadList) {
            t.start();
        }
        //确认所有线程执行完毕
        for (Thread t : threadList) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("i:" + counter.i);
        System.out.println("atomici:" + counter.atomici.get());
        System.out.println("time:" + (System.currentTimeMillis() - start));


    }

    /**
     * 使用CAS实现线程安全计数器
     */
    private void safeCount() {
        for (; ; ) {
            int i = atomici.get();
            boolean suc = atomici.compareAndSet(i, ++i);
            if (suc) {
                break;
            }
        }

    }

    /**
     * 非线程安全
     */
    private void unsafeCount() {
        i++;
    }
}
