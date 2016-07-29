package com.gaohuan.pattern.tmp;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>User: acer
 * <p>Date: 2016/7/27
 * <p>Version: 1.0
 */
public class MyTemplate implements MyJdbcOperations {

    public <T> T execute(MyStatementCallback<T> callback) {
        Connection connection = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return callback.doInStatement(statement);
    }

}
