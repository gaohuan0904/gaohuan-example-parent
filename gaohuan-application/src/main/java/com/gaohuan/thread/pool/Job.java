package com.gaohuan.thread.pool;

/**
 * Created by huan on 2016/2/25.
 */
public class Job {

    public void doJob() {
        long timeEnd = System.currentTimeMillis() + 100;
        while (System.currentTimeMillis() < timeEnd) {
            //模拟耗时操作
        }
        System.out.println("  ~ run job ~");
    }
}
