package com.gaohuan.pattern.tmp;

import java.sql.Statement;

/**
 * <p>User: acer
 * <p>Date: 2016/7/27
 * <p>Version: 1.0
 */
public interface MyStatementCallback<T> {

    T doInStatement(Statement statement);

}
