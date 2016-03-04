package com.gaohuan.jms;

import org.apache.activemq.broker.BrokerService;

/**
 * Created by gh on 2016/1/25.
 */
public class BrokerServiceMain {
    public static void main(String[] args) throws Exception {
        BrokerService broker = new BrokerService();

        // configure the broker
        broker.addConnector("tcp://localhost:61616");

        broker.start();
    }
}
