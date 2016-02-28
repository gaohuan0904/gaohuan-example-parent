package com.gaohuan.thread.pool;

import java.util.LinkedList;

/**
 * Created by huan on 2016/2/24.
 */
public class Worker implements Runnable {

    private volatile boolean running = true;

    //工作列表
    private final LinkedList<Job> jobs;

    public Worker(LinkedList<Job> jobs) {
        this.jobs = jobs;
    }

    @Override
    public void run() {
        while (running) {
            Job job = null;
            synchronized (jobs) {
                while (jobs.isEmpty()) {
                    try {
                        jobs.wait();
                    } catch (InterruptedException e) {
                        //设置中断标记 wait、sleep阻塞下，调用interrupt,中断标记会被清除
                        //重新调用一次中断，设置中断状态为 true
//                        Thread.currentThread().interrupt();
                        System.out.println("------- Thread.currentThread().interrupt() ------->>> return");
                        return;
                    }
                }
                job = jobs.removeFirst();
            }

            if (job != null) {
                job.doJob();
            }
            System.out.println("<<<<<<<< " + Thread.currentThread().getName() + " >>>>>>>");

        }

    }

    public void shutdown() {
        running = false;
    }

    public boolean isShutdown() {
        return true;
    }
}
