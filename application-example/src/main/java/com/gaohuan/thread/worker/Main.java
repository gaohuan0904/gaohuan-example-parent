package com.gaohuan.thread.worker;

/**
 * Created by gh on 2015/12/16.
 */
public class Main {

    public static void main(String[] args) {
        Channel channel = new Channel(5);
        ClientThread clientThread = new ClientThread("ClientThread", channel);
        channel.startWorker();
        clientThread.start();

    }
}
