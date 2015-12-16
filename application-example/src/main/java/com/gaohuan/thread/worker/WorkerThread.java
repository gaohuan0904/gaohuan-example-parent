package com.gaohuan.thread.worker;

/**
 * Created by gh on 2015/12/16.
 */
public class WorkerThread extends Thread {
    private Channel channel;


    public WorkerThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true) {
            channel.takeRequest().execute();
        }

    }
}
