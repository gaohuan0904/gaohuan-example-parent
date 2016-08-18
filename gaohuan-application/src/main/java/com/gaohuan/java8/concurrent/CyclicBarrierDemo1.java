package com.gaohuan.java8.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 一个或多个线程组必须在执行点等待，直到所有线程到达执行点为止
 * 同步对象对象会被挂起，直到指定数量的线程到达执行点为止
 * 所有线程到达后，执行指定的后续线程
 */
public class CyclicBarrierDemo1 {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3, () -> System.out.println("barrier reached"));
        System.out.println("Starting...");
        Runnable runnable = () -> {
            try {
                System.out.println(Thread.currentThread().getName());
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        };
        new Thread(runnable, "A").start();
        new Thread(runnable, "B").start();
        new Thread(runnable, "C").start();

    }
}
