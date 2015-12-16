package com.gaohuan.thread.worker;

import java.util.Random;

/**
 * Created by gh on 2015/12/16.
 */
public class ClientThread extends Thread {

    private static final int REQUEST_NUM = 1000;

    private Channel channel;

    private Random random = new Random();


    public ClientThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < REQUEST_NUM; i++) {
                Request request = new Request("Request" + i, i);
                channel.putRequest(request);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
