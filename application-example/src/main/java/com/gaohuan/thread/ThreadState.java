package com.gaohuan.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by huan on 2016/1/23.
 */
public class ThreadState {

    public static void main(String[] args) {
        new Thread(new TimeWating(), "timeWaitingThread").start();
        new Thread(new Waiting(), "waitingThread").start();
        new Thread(new Blocked(), "blockedThread-1").start();
        new Thread(new Blocked(), "blockedThread-2").start();
    }

    static class TimeWating implements Runnable {


        @Override
        public void run() {
            while (true) {
                SleepUitls.second(100);
            }
        }
    }

    static class Waiting implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Blocked implements Runnable {

        @Override
        public void run() {
            synchronized (Blocked.class) {
                while (true) {
                    SleepUitls.second(100);
                }
            }
        }
    }


    public static class SleepUitls {
        public static final void second(long seconds) {
            try {
                TimeUnit.SECONDS.sleep(seconds);
            } catch (InterruptedException e) {
            }
        }
    }
}


