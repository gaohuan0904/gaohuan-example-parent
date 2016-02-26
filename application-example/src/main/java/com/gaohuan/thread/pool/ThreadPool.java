package com.gaohuan.thread.pool;

/**
 * 线程池接口.
 */
public interface ThreadPool {
    /**
     * 执行一个job
     *
     * @param job
     */
    void execute(Job job);

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     * 增加工作线程数
     *
     * @param num
     */
    void addWorkers(int num);

    /**
     * 减少工作线程数
     *
     * @param num
     */
    void removeWorkers(int num);

    /**
     * 获取任务数
     *
     * @return
     */
    int getJobSize();

    /**
     * 获取当前线程数
     * @return
     */
    int getThreadNum();


}
