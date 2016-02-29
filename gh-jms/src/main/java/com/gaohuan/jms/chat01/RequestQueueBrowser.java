package com.gaohuan.jms.chat01;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;

import java.util.Enumeration;

import org.apache.activemq.command.ActiveMQMapMessage;

/**
 * Created by gh on 2016/1/26.
 */
public class RequestQueueBrowser {
    public static void main(String[] args) {
        try {
            Context ctx = new InitialContext();
            QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) ctx.lookup("queueCF");
            QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
            queueConnection.start();

            //建立会话
            Queue queue = (Queue) ctx.lookup("loanRequestQ");

            QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            QueueBrowser queueBrowser = queueSession.createBrowser(queue);

            Enumeration enu = queueBrowser.getEnumeration();

            while (enu.hasMoreElements()) {
                ActiveMQMapMessage msg = (ActiveMQMapMessage) enu.nextElement();
                System.out.println("browsing:" + msg.getJMSMessageID());
            }
            queueBrowser.close();
            queueConnection.close();
            System.exit(0);

        } catch (Exception e) {
            System.exit(1);
        }
    }
}
