package com.gaohuan.jms.chat01;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by gh on 2016/1/25.
 */
public class QLender implements MessageListener {
    private QueueConnection queueConnection;
    private QueueSession queueSession;
    private Queue requestQ;

    public QLender(String queuecf, String requestQueue) throws NamingException, JMSException {
        Context ctx = new InitialContext();
        QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) ctx.lookup(queuecf);
        queueConnection = queueConnectionFactory.createQueueConnection();
        //创建jms会话
        queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        //查找申请队列
        requestQ = (Queue) ctx.lookup(requestQueue);

        //启动连接
        queueConnection.start();

        //创建消息接收器
        QueueReceiver receiver = queueSession.createReceiver(requestQ);
        receiver.setMessageListener(this);
        System.out.println("waiting for loan request ...");
    }

    @Override
    public void onMessage(Message message) {
        try {
            boolean accepted = false;
            MapMessage msg = (MapMessage) message;
            double salary = msg.getDouble("salary");
            double loanAmt = msg.getDouble("loanAmount");
            if (loanAmt < 200000) {
                accepted = (salary / loanAmt) > 0.25;
            } else {
                accepted = (salary / loanAmt) > 0.33;
            }

            System.out.println("%=" + (salary / loanAmt) + ",loan is " + (accepted ? "accepted" : "declined"));
            TextMessage tmsg = queueSession.createTextMessage();
            tmsg.setText(accepted ? "accepted" : "declined");
            tmsg.setJMSCorrelationID(message.getJMSMessageID());

            //创建发送者并发送消息
            QueueSender queueSender = queueSession.createSender((Queue) message.getJMSReplyTo());
            queueSender.send(tmsg);
            System.out.println("waiting for loan requests ...");

        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    public void exit() throws JMSException {
        queueConnection.close();
        System.exit(0);
    }

    public static void main(String[] args) throws JMSException, NamingException, IOException {
        String queuecf = "queueCF";
        String reqeustQ = "loanRequestQ";

        QLender qLender = new QLender(queuecf, reqeustQ);
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("press enter to quit application:");
        stdin.readLine();
        qLender.exit();

    }

}
