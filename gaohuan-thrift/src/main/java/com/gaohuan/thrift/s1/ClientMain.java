package com.gaohuan.thrift.s1;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by huan on 2016/9/6.
 */
public class ClientMain {
    public static final Logger logger = LoggerFactory.getLogger(ClientMain.class);
    public static final String SERVER_IP = "localhost";
    public static final int SERVER_PORT = 8090;
    public static final int TIMEOUT = 3000;


    public static void main(String[] args) {
        ClientMain.start("gaohuan");
    }

    public static void start(String username) {
        TTransport tTransport = null;
        try {
            tTransport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
            TProtocol tProtocol = new TBinaryProtocol(tTransport);
            HelloWorldService.Client client = new HelloWorldService.Client(tProtocol);
            tTransport.open();
            String result = client.sayHello(username);
            logger.info("result:" + result);
        } catch (Exception e) {
            logger.error("start client error!", e);
        } finally {
            if (tTransport != null) {
                tTransport.close();
            }
        }
    }
}
