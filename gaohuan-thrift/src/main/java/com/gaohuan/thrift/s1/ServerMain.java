package com.gaohuan.thrift.s1;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by huan on 2016/9/6.
 */
public class ServerMain {
    public static final Logger logger = LoggerFactory.getLogger(ServerMain.class);
    public static final int SERVER_PORT = 8090;

    public static void main(String[] args) {
        ServerMain.start();
    }

    public static void start() {
        try {
            logger.info("server start...");
            TProcessor processor = new HelloWorldService.Processor(new HelloWorldImpl());
            TServerSocket serverSocket = new TServerSocket(SERVER_PORT);
            TServer.Args tArgs = new TServer.Args(serverSocket);
            tArgs.processor(processor);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            TServer server = new TSimpleServer(tArgs);
            server.serve();
        } catch (TTransportException e) {
            logger.error("server start error", e);
        }
    }
}
