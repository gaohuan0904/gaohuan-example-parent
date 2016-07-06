package com.gaohuan.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.apache.commons.lang3.RandomUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by acer on 2016/6/29.
 */
public class Work {
    private final static String QUEUE_NAME = "workQueuePersistence";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        long hashCode = RandomUtils.nextLong(10000, 200000);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明队列
        boolean durable = true;
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
        System.out.println("waiting for message...");
        //设置最大服务转发消息数量
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);

        QueueingConsumer consumer = new QueueingConsumer(channel);
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(hashCode + "[x]received '" + message + "'");
            doWork(message);
            System.out.println(hashCode + "[x] done");
            //手动发送应答
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }

    private static void doWork(String task) throws InterruptedException {
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(1000);
            }
        }
    }
}
