package com.gaohuan.spring;

import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * Created by gh on 2016/2/18.
 */
public class AsyncTaskExecutorTest {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        AsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
        System.out.println("start");
        taskExecutor.execute(new AsyncTask());
        System.out.println("end");

    }

    static class AsyncTask implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                System.out.print(i + "\t");
            }

        }
    }
}
