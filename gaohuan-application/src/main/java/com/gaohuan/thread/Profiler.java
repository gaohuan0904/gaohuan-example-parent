package com.gaohuan.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by huan on 2016/2/9.
 */
public class Profiler {

    private static final ThreadLocal<Long> TIME_THREAD_LOCAL = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    public static final void begin() {
        TIME_THREAD_LOCAL.set(System.currentTimeMillis());
    }

    public static final long end() {
        return System.currentTimeMillis() - TIME_THREAD_LOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Profiler.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("cost:" + Profiler.end() + " mills");
    }
}
