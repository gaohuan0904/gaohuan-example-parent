package com.gaohuan.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.apache.commons.lang3.RandomUtils;

import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * Created by acer on 2016/6/29.
 */
public class EmitLog {
    private final static String EXCHANGE_NAME = "exchange-direct";
    private static final String[] SEVERITIES = {"info", "warning", "error"};

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明转发器和类型
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        for (int i = 0; i < 6; i++) {
            String severity = randomSeverity();
            String message = "[" + severity + "]:" + UUID.randomUUID().toString();

            channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
            System.out.println("sent '" + message + "'");
        }
        channel.close();
        connection.close();
    }

    private static String randomSeverity() {
        return SEVERITIES[RandomUtils.nextInt(0, 3)];
    }
}
