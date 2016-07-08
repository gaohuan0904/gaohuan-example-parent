package com.gaohuan.jta;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * Created by acer on 2016/7/8.
 */
public class RabbitConsumer {
    public void onMessage(String message) {
        System.out.println("data:" + message);
    }
}
