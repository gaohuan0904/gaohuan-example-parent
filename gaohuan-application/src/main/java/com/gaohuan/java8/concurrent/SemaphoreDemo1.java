package com.gaohuan.java8.concurrent;

import java.util.concurrent.Semaphore;

/**
 * 控制一个资源被同时访问的数量
 * 通过acquire()获取一个许可，如果没有就等待
 * 通过release()释放一个许可
 * 单个Semaphore可以实现互斥锁的功能，并且可以由一个线程获得锁，
 * 另一个线程释放锁，这可以应用到死锁恢复的一些场合
 */
public class SemaphoreDemo1 {
    private static int count = 0;


    static class IncThread implements Runnable {
        private Semaphore semaphore;
        String name;

        public IncThread(Semaphore semaphore, String name) {
            this.semaphore = semaphore;
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("starting " + name);
            System.out.println(name + " is waiting for a permit.");
            try {
//                semaphore.acquire();
                System.out.println(name + " get a permit");
                for (int i = 0; i < 5; i++) {
                    count++;
                    System.out.println(name + " : " + count);
                    //休眠，测试另外一个线程是否在休眠期间获得cpu时间片
                    Thread.sleep(10);

                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " release the permit");
//            semaphore.release();
        }
    }

    static class DecThread implements Runnable {
        private Semaphore semaphore;
        String name;

        public DecThread(Semaphore semaphore, String name) {
            this.semaphore = semaphore;
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("starting " + name);
            System.out.println(name + " is waiting for a permit.");
            try {
//                semaphore.acquire();
                System.out.println(name + " get a permit");
                for (int i = 0; i < 5; i++) {
                    count--;
                    System.out.println(name + " : " + count);
                    //休眠，测试另外一个线程是否在休眠期间获得cpu时间片
                    Thread.sleep(10);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " release the permit");
//            semaphore.release();
        }
    }

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        new Thread(new IncThread(semaphore, "A")).start();
        new Thread(new DecThread(semaphore, "B")).start();

    }

}
