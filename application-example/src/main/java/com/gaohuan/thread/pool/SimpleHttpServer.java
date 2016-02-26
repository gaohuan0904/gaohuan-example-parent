package com.gaohuan.thread.pool;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 使用自定义线程池的简单server
 */
public class SimpleHttpServer {
    static ThreadPool threadPool = new DefaultThreadPool(20);

    static String basePath = "D:/idea_proect/my-example/application-example/src/main/java/com/gaohuan/thread/pool/";

    static ServerSocket serverSocket;

    static int port = 8080;

    public static void setPort(int port) {
        if (port > 0) {
            SimpleHttpServer.port = port;
        }
    }

    public static void setBasePath(String basePath) {
        if (basePath != null && new File(basePath).exists() && new File(basePath).isDirectory()) {
            SimpleHttpServer.basePath = basePath;
        }
    }

    public static void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = null;
        while ((socket = serverSocket.accept()) != null) {
            threadPool.execute(new HttpRequestHandler(socket));
        }
        serverSocket.close();

    }

    public static void main(String[] args) throws IOException {
        System.out.println("服务器启动---端口["+port+"]");
        SimpleHttpServer.start();
    }


}
