package com.gaohuan.thread.worker;

/**
 * Created by gh on 2015/12/16.
 */
public class Channel {

    public static final int MAX_REQUEST = 100;

    public static final int MAX_THREADS = 20;

    private Request[] requestQueue;

    private int head;

    private int tail;

    private int count;

    private WorkerThread[] workerThreadPool;


    public Channel(int threads) {

        this.head = 0;
        this.tail = 0;
        this.count = 0;

        if (threads > MAX_THREADS) {
            threads = MAX_THREADS;
        }
        this.workerThreadPool = new WorkerThread[threads];
        for (int i = 0; i < threads; i++) {
            workerThreadPool[i] = new WorkerThread("worker" + i, this);
        }
        this.requestQueue = new Request[MAX_REQUEST];

    }

    public void startWorker() {
        for (int i = 0; i < workerThreadPool.length; i++) {
            workerThreadPool[i].start();
        }
    }

    public synchronized void putRequest(Request request) {
        while (count >= MAX_REQUEST) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        requestQueue[tail] = request;
        tail = (tail + 1) % requestQueue.length;
        count++;
        notifyAll();

    }

    public synchronized Request takeRequest() {
        while (count <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        head = (head + 1) % requestQueue.length;
        notifyAll();
        return requestQueue[head];

    }
}
