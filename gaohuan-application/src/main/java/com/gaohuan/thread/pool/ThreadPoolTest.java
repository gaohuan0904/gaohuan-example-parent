package com.gaohuan.thread.pool;


import java.util.Random;

/**
 * 线程池测试
 */
public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        ThreadPool threadPool = new DefaultThreadPool();
        //增加减少线程测试运行时间
//        threadPool.addWorkers(10);
        for (int i = 0; i < 1000; i++) {
            threadPool.execute(new Job());
        }

        Random r = new Random();
        for (; threadPool.getJobSize() > 0; ) {
            //空循环 ，直到job执行完
            Thread.sleep(1000, r.nextInt(500));
        }
        threadPool.shutdown();

        System.out.println("job 执行结束！");
        System.out.println((System.currentTimeMillis() - start) / 1000 + " 秒");

    }

}
