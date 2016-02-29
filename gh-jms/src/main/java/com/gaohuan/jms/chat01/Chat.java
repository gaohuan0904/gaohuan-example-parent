package com.gaohuan.jms.chat01;

import javax.jms.*;
import javax.naming.InitialContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by gh on 2016/1/22.
 */
public class Chat implements MessageListener {

    public TopicSession pubSession;
    public TopicPublisher publisher;
    public TopicConnection topicConnection;
    public String userName;

    public Chat(String topicFactory, String topicName, String userName) throws Exception {

        //使用jndi.properties文件获得一个jndi连接
        InitialContext ctx = new InitialContext();

        //查找一个jms连接工厂并建立连接
        TopicConnectionFactory connectionFactory = (TopicConnectionFactory) ctx.lookup(topicFactory);
        TopicConnection connection = connectionFactory.createTopicConnection();

        //创建2个jms会话对象
        TopicSession pubSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        TopicSession subSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        //查找一个jms主题
        Topic chatTopic = (Topic) ctx.lookup(topicName);

        //创建一个jms发布者和订阅者
        TopicPublisher publisher = pubSession.createPublisher(chatTopic);
        TopicSubscriber subscriber = subSession.createSubscriber(chatTopic);

        subscriber.setMessageListener(this);
        this.topicConnection = connection;
        this.pubSession = pubSession;
        this.publisher = publisher;
        this.userName = userName;

        topicConnection.start();

    }

    /**
     * 消息处理
     * @param message
     */
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            System.out.println(textMessage.getText());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发布消息
     * @param text
     */
    private void writeMessage(String text) throws JMSException {
        TextMessage message = pubSession.createTextMessage();
        message.setText(userName + ":" + text);
        publisher.publish(message);
    }

    /**
     * 关闭jms连接
     * @throws JMSException
     */
    public void close() throws JMSException {
        topicConnection.close();
    }


    public static void main(String[] args) throws Exception {
        String topicFactory = "TopicCF";
        String topicName = "topic1";
        String userName = "ss";
//        String userName="sm";
        Chat chat = new Chat(topicFactory, topicName, userName);

        BufferedReader commandLine = new BufferedReader(new InputStreamReader(System.in));
        /*循环直到键入：exit*/
        while (true) {
            String s = commandLine.readLine();
            if (s.equalsIgnoreCase("exit")) {
                chat.close();
                System.exit(0);
            } else {
                chat.writeMessage(s);
            }
        }
    }
}
