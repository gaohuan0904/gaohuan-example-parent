package com.gaohuan.thread.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gh on 2015/12/16.
 */
public class WorkerThread extends Thread {
    public static final Logger LOGGER = LoggerFactory.getLogger(WorkerThread.class);

    private Channel channel;


    public WorkerThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true) {
<<<<<<< HEAD
            Request request = channel.takeRequest();
            request.execute();
=======
            LOGGER.debug("WorkerThread ready takeRequest..................");
//            Request request = channel.takeRequest();
            Request request = channel.takeRequestOutQueue();
            request.execute();
            LOGGER.debug("WorkerThread excute takeRequest!");

>>>>>>> ae399a8f4c2fadcc130ad4011368927b520d639c
        }

    }
}
