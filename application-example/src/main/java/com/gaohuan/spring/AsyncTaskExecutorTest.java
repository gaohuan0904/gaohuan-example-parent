package com.gaohuan.spring;

import org.junit.Test;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * Created by gh on 2016/2/18.
 */
public class AsyncTaskExecutorTest {

    @Test
    public void test() {
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
