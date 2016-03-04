package com.gaohuan.thread.pool;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by huan on 2016/2/9.
 */
public class DefaultThreadPool implements ThreadPool {

    //线程池最大限制
    private static final int MAX_WORKER_NUMBERS = 50;
    //默认线程池数量
    private static final int DEFAULT_WORKER_NUMBERS = 20;
    //最小线程池数量
    private static final int MIN_WORKER_NUMBERS = 1;
    //工作列表
    private final LinkedList<Job> jobs = new LinkedList<Job>();
    //工作者列表
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());

    private final Map<Worker, Thread> workerToThreads = new ConcurrentHashMap<Worker, Thread>();
    //工作者线程数量
    private int workerNum = DEFAULT_WORKER_NUMBERS;
    //线程编号生成
    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool() {
        initializeWorkers(DEFAULT_WORKER_NUMBERS);
    }

    public DefaultThreadPool(int num) {
        workerNum = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : (num < MIN_WORKER_NUMBERS ? MIN_WORKER_NUMBERS : num);
        initializeWorkers(workerNum);
    }


    private void initializeWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker(jobs);
            workers.add(worker);
            Thread thread = new Thread(worker, "threadPool-worker-" + threadNum.incrementAndGet());
            workerToThreads.put(worker, thread);
            thread.start();

        }
    }


    @Override
    public void execute(Job job) {
        if (job != null) {
            synchronized (jobs) {
                jobs.addLast(job);
                jobs.notify();
            }
        }

    }

    @Override
    public void shutdown() {
        if (workers != null && !workers.isEmpty()) {
            for (Worker worker : workers) {
                worker.shutdown();
                Thread thread = workerToThreads.get(worker);
                if (thread != null && !thread.isInterrupted()) {
                    thread.interrupt();
                    workerToThreads.remove(worker);
                }
            }
        }

    }

    @Override
    public void addWorkers(int num) {
        synchronized (jobs) {//获取jobs锁，增加线程时需要初始化，不能添加job
            //不能大于最大线程数
            if (num + this.workerNum > MAX_WORKER_NUMBERS) {
                num = MAX_WORKER_NUMBERS - this.workerNum;
            }
            initializeWorkers(num);
            this.workerNum += num;
        }

    }

    @Override
    public void removeWorkers(int num) {

        synchronized (jobs) {
            if (num >= this.workerNum) {
                throw new IllegalArgumentException("数量超出当前线程数！");
            }
            int count = 0;
            while (count < num) {
                Worker worker = workers.get(count);
                if (workers.remove(worker)) {
                    worker.shutdown();
                    count++;
                }
            }

            this.workerNum = this.workerNum - count;
        }

    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }

    @Override
    public int getThreadNum() {
        return workerNum;
    }
}
