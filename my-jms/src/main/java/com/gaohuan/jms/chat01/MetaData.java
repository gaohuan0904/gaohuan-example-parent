package com.gaohuan.jms.chat01;

import javax.jms.ConnectionMetaData;
import javax.jms.JMSException;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.Enumeration;

/**
 * Created by gh on 2016/1/25.
 */
public class MetaData {
    public static void main(String[] args) throws NamingException, JMSException {
        Context ctx = new InitialContext();
        QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) ctx.lookup("queueCF");
        QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
        ConnectionMetaData connectionMetaData = queueConnection.getMetaData();

        System.out.println("jms version:" + connectionMetaData.getJMSVersion());
        System.out.println("jms provider:" + connectionMetaData.getJMSProviderName());
        System.out.println("supported:");
        Enumeration e = connectionMetaData.getJMSXPropertyNames();
        while (e.hasMoreElements()) {
            System.out.println(e.nextElement());
        }
        System.exit(1);

    }
}
