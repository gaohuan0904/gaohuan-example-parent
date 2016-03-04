package com.gaohuan.jms.chat01;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by gh on 2016/1/25.
 */
public class QBorrower {

    private QueueConnection queueConnection;
    private QueueSession queueSession;
    private Queue responseQ;
    private Queue requestQ;


    public QBorrower(String queuecf, String requestQueue, String responseQueue) throws NamingException, JMSException {
        Context ctx = new InitialContext();
        QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) ctx.lookup(queuecf);
        queueConnection = queueConnectionFactory.createQueueConnection();
        queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

        requestQ = (Queue) ctx.lookup(requestQueue);
        responseQ = (Queue) ctx.lookup(responseQueue);

        queueConnection.start();
    }

    private void sendLoanRequest(double salary, double loanAmt) throws JMSException {
        //创建jms消息
        MapMessage msg = queueSession.createMapMessage();
        msg.setDouble("salary", salary);
        msg.setDouble("loanAmount", loanAmt);
        msg.setJMSReplyTo(responseQ);

        //创建发送者并发送消息
        QueueSender qSender = queueSession.createSender(requestQ);
        qSender.send(msg);
//        JMSType = 'car' AND color = 'blue' AND weight > 2500
        //等待响应
        String filter = "JMSCorrelationID ='" + msg.getJMSMessageID() + "'";
        QueueReceiver qReceiver = queueSession.createReceiver(responseQ, filter);

        TextMessage tmsg = (TextMessage) qReceiver.receive(3000);
        if (tmsg == null) {
            System.out.println("qlender not responseding");
        } else {
            System.out.println("loan request was " + tmsg.getText());
        }
    }

    private void exit() throws JMSException {
        queueConnection.close();
        System.exit(1);
    }

    public static void main(String[] args) throws JMSException, NamingException, IOException {
        String queuecf = "queueCF";
        String requestQ = "loanRequestQ";
        String responseQ = "loanResponseQ";

        QBorrower qBorrower = new QBorrower(queuecf, requestQ, responseQ);
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String loanRequest = stdin.readLine();
            if (loanRequest == null || loanRequest.trim().length() <= 0) {
                qBorrower.exit();
            }
            StringTokenizer st = new StringTokenizer(loanRequest, ",");
            double salary = Double.valueOf(st.nextToken().trim());
            double loanAmt = Double.valueOf(st.nextToken().trim());
            qBorrower.sendLoanRequest(salary, loanAmt);
        }
    }
}
