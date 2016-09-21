package com.gaohuan.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by acer on 2016/6/29.
 */
public class ReceiveLogToConsole {
    private final static String EXCHANGE_NAME = "exchange-direct";
    private static final String[] SEVERITIES = {"info", "warning", "error"};
    private static final String SEVERITY_INFO = SEVERITIES[0];

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明转发器和类型
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        //创建一个非持久的，唯一切自动删除的队列
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, SEVERITY_INFO);

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, consumer);
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("[x] Received '" + message + "'");
        }
    }

}
