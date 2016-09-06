package com.gaohuan.concurrency;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 每个线程保留一份Connection对象的副本
 */
public class ThreadLocalDemo2 {
    static final ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>();
    static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    static final String DB_USERNAME = "root";
    static final String DB_PASSWORD = "password";

    public static Connection getConnection() throws SQLException {
        Connection connection = connectionHolder.get();
        if (connection == null) {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            connectionHolder.set(connection);
        }
        return connection;
    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 14; i++) {
            executorService.execute(() -> {
                try {
                    String outStr = ThreadLocalDemo2.getConnection().toString();
                    System.out.println(outStr);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }


    }

}
