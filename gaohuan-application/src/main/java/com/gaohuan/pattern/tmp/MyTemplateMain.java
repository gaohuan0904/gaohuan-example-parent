package com.gaohuan.pattern.tmp;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * 模板方法
 * <p>User: acer
 * <p>Date: 2016/7/27
 * <p>Version: 1.0
 */
public class MyTemplateMain {
    public static void main(String[] args) {
        MyTemplate template = new MyTemplate();
        final String sql = "select 1 ";
        template.execute(new MyStatementCallback<Boolean>() {
            @Override
            public Boolean doInStatement(Statement statement) {
                boolean result = false;
                try {
                    result = statement.execute(sql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return result;
            }
        });
    }
}
