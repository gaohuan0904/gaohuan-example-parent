package com.gaohuan.java8.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>User: GaoHuan
 * <p>Date: 2016/8/23
 */
public class LockDemo {

    static class Shared {
        static int count = 0;
    }

    static class LockTask implements Runnable {
        String name;
        ReentrantLock lock;

        public LockTask(String name, ReentrantLock lock) {
            this.name = name;
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                System.out.println(name + " is waiting to lock count.");
                lock.lock();
                System.out.println(name + " is locking count.");
                Shared.count++;
                System.out.println(name + ":" + Shared.count);
                System.out.println(name + " is sleeping.");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(name + " is unlocking count.");
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        new Thread(new LockTask("A", lock)).start();
        new Thread(new LockTask("B", lock)).start();

    }

}
