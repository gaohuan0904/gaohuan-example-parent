package com.gaohuan.jta;

/**
 * Created by acer on 2016/7/8.
 */
public class RabbitConsumer {
    public void onMessage(String message) {
        System.out.println("data:" + message);
    }
}
