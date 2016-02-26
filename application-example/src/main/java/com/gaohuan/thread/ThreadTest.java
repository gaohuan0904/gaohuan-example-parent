package com.gaohuan.thread;

/**
 * Created by huan on 2016/2/24.
 */
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread() {
            @Override
            public void run() {
                // 每隔一秒检测是否设置了中断标示
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("Thread is running...");
                    long time = System.currentTimeMillis();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Thread interrupted...");
                        System.out.println("isInterrupted:" + this.isInterrupted());
                        Thread.currentThread().interrupt();

                    }
                }
                System.out.println("Thread exiting under request...");
            }
        };


        System.out.println("Starting thread...");
        thread.start();
        Thread.sleep(3000);
        System.out.println("Asking thread to stop...");
        // 发出中断请求
        thread.interrupt();
        Thread.sleep(3000);
        System.out.println("Stopping application...");

        Thread thread1 = new Thread();
        System.out.println("thread1");
        thread1.start();
        thread1.interrupt();
        System.out.println("thread1.isInterrupted():" + thread1.isInterrupted());
    }
}
