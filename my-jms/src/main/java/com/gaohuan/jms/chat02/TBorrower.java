package com.gaohuan.jms.chat02;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by gh on 2016/1/26.
 */
public class TBorrower implements MessageListener {

    private TopicConnection topicConnection;

    private TopicSession topicSession;

    private Topic topic;

    private double currentRate;

    public TBorrower(String topicCF, String topicName, String rate) throws NamingException, JMSException {
        currentRate = Double.valueOf(rate);

        Context ctx = new InitialContext();

        TopicConnectionFactory factory = (TopicConnectionFactory) ctx.lookup(topicCF);

        topicConnection = factory.createTopicConnection();

        topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        topic = (Topic) ctx.lookup(topicName);

        //创建消息侦听器
        TopicSubscriber subscriber = topicSession.createSubscriber(topic);
        subscriber.setMessageListener(this);

        topicConnection.start();


    }

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof BytesMessage) {
                BytesMessage msg = (BytesMessage) message;
                double newRate = msg.readDouble();
                if (currentRate - newRate >= 1.0) {
                    System.out.println("new Rate=" + newRate + " consider refinancing");
                } else {
                    System.out.println("new rate=" + newRate + " - keep existing");
                }
            } else {
                throw new UnsupportedOperationException("不支持的消息");
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    public void exit() throws JMSException {
        topicConnection.close();
        System.exit(0);
    }

    public static void main(String[] args) {
        String topicCF = "topicCF";
        String topicName = "topic1";
        String rate = "1.2";

        try {
            TBorrower borrower = new TBorrower(topicCF, topicName, rate);
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("press enter to quit application>");
            stdin.readLine();
            borrower.exit();

        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
