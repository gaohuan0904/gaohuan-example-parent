package com.gaohuan.jms.chat02;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by gh on 2016/1/26.
 */
public class TLender {

    private TopicConnection topicConnection;
    private TopicSession topicSession;
    private Topic topic;

    public TLender(String topicCF, String topicName) throws NamingException, JMSException {
        Context ctx = new InitialContext();

        TopicConnectionFactory topicConnectionFactory = (TopicConnectionFactory) ctx.lookup(topicCF);

        topicConnection = topicConnectionFactory.createTopicConnection();

        //创建jms会话
        topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        topic = (Topic) ctx.lookup(topicName);

        topicConnection.start();


    }

    private void publishRate(double newRate) throws JMSException {
        BytesMessage bytesMessage = topicSession.createBytesMessage();
        bytesMessage.writeDouble(newRate);

        //创建发布者并发布消息
        TopicPublisher topicPublisher = topicSession.createPublisher(topic);
        topicPublisher.publish(bytesMessage);

    }

    private void exit() throws JMSException {
        topicConnection.close();
        System.exit(0);
    }

    public static void main(String argv[]) {
        String topicCF = "topicCF";
        String topicName = "topic1";

        try {
            TLender lender = new TLender(topicCF, topicName);
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.println(">");
                String rate = stdin.readLine();
                if (rate == null || rate.trim().length() <= 0) {
                    lender.exit();
                }
                double newRate = Double.valueOf(rate);
                lender.publishRate(newRate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
