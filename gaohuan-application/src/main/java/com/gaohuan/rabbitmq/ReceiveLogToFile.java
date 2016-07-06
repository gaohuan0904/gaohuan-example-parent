package com.gaohuan.rabbitmq;

import com.alibaba.dubbo.remoting.exchange.Exchangers;
import com.rabbitmq.client.*;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.TimeoutException;

/**
 * Created by acer on 2016/6/29.
 */
public class ReceiveLogToFile {
    private final static String EXCHANGE_NAME = "exchange-direct";
    private static final String[] SEVERITIES = {"info", "warning", "error"};
    private static final String SEVERITY_ERROR = SEVERITIES[2];


    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明转发器和类型
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        //创建一个非持久的，唯一切自动删除的队列
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, SEVERITY_ERROR);

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, consumer);
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            print2File(message);
        }

    }

    private static void print2File(String msg) {
        String dir = ReceiveLogToFile.class.getClassLoader().getResource("").getPath();
        String logFileName = DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd");
        File file = new File(dir, logFileName + ".txt");
        try {
            FileOutputStream fos = new FileOutputStream(file, true);
            fos.write((msg + "\r\n").getBytes());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
