package com.gaohuan.thread.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by gh on 2015/12/16.
 */
public class Channel {
    public static final Logger LOGGER = LoggerFactory.getLogger(Channel.class);


    public static final int MAX_REQUEST = 100;

    public static final int MAX_THREADS = 20;

    //方式1
    private Request[] requestQueue;
    //方式2
    private BlockingQueue<Request> requestBlockingQueue = new ArrayBlockingQueue<Request>(MAX_REQUEST);
    ;


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
//        LOGGER.debug("queue array ---------- " + queueToString(requestQueue));
        LOGGER.debug("putRequest begin :" + " tail=" + tail + " head=" + head + " count=" + count + " ==== " + request);
        while (count >= MAX_REQUEST) {
            LOGGER.debug("putRequest wait()");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        requestQueue[tail] = request;
        tail = (tail + 1) % requestQueue.length;
        count++;
        LOGGER.debug("putRequest end :" + " tail=" + tail + " head=" + head + " count=" + count);

        notifyAll();

    }

    public void putRequestInQueue(Request request) {
        try {
            requestBlockingQueue.put(request);
        } catch (InterruptedException e) {
        }

    }

    public synchronized Request takeRequest() {
//        LOGGER.debug("queue array ----------\n " + queueToString(requestQueue));
        LOGGER.debug("takeRequest begin :" + " tail=" + tail + " head=" + head + " count=" + count);

        while (count <= 0) {
            LOGGER.debug("takeRequest wait()");
            try {

                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Request request = requestQueue[head];
        head = (head + 1) % requestQueue.length;
        count--;
        notifyAll();
        LOGGER.debug("takeRequest end :" + " tail=" + tail + " head=" + head + " count=" + count);
        return request;

    }

    public Request takeRequestOutQueue() {
        Request request = null;
        try {
            request = requestBlockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return request;
    }

    public String queueToString(Request[] requestQueue) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < requestQueue.length; i++) {
            Request request = requestQueue[i];
            if (request != null) {
                stringBuilder.append("i=" + i + " request=" + request + "\n");
            }
        }
        return stringBuilder.toString();
    }
}
