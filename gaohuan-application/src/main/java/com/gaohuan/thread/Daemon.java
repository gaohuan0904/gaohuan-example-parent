package com.gaohuan.thread;

/**
 * Created by huan on 2016/1/23.
 */
public class Daemon {


    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner(), "daemonRunner");
        thread.setDaemon(true);
        thread.start();
    }

    static class DaemonRunner implements Runnable {

        @Override
        public void run() {
            try {
                ThreadState.SleepUitls.second(10);
            } finally {
                System.out.println("finally run");
            }
        }
    }
}
