package com.gaohuan.jms.chat01;

import javax.jms.*;
import javax.naming.InitialContext;

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

    }

    public void onMessage(Message message) {

    }
}
