package com.gaohuan.thread.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gh on 2015/12/16.
 */
public class Main {
    public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Channel channel = new Channel(10);
        ClientThread clientThread = new ClientThread("ClientThread", channel);
        channel.startWorker();
        clientThread.start();

    }
}
