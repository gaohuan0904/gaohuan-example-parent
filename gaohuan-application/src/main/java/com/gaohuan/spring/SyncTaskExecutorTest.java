package com.gaohuan.spring;

import org.springframework.core.task.SyncTaskExecutor;

/**
 * Created by gh on 2016/2/18.
 */
public class SyncTaskExecutorTest {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        SyncTaskExecutor taskExecutor = new SyncTaskExecutor();
        System.out.println("syncTask start");
        taskExecutor.execute(new SyncTask());
        System.out.println("syncTask end");
    }

    static class SyncTask implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                System.out.print(i + "\t");
            }
            try {
                Thread.sleep(10000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("");

        }
    }
}
