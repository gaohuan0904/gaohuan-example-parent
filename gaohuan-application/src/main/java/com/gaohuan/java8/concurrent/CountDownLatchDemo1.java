package com.gaohuan.java8.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch:  线程等待，直到发生一个或多个事件为止.
 * 初始创建带有事件数量的计数器，在释放锁存器之前，必须发生指定
 * 数量的事件，每次发生一个事件，计数器递减，当计数器达到0时，
 * 打开锁存器。
 * <p>
 * 适用线程必须等待一个或多个时间发生时
 */
public class CountDownLatchDemo1 {

    public static void main(String[] args) {
        //新建一个递减锁存器，需要满足递减5次的事件。
        CountDownLatch countDownLatch = new CountDownLatch(5);
        System.out.println("starting...");
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
                countDownLatch.countDown();
                try {
                    //让出cpu时间片，观察主线程是否等待CountDownLatch计数为0
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            //等待CountDownLatch的计算器达到0或者被中断后继续
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Done");
    }
}
