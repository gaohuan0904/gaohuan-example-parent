package com.gaohuan.pattern.crp.principle;

import java.sql.Connection;

/**
 * Created by acer on 2016/7/6.
 */
public class OracleDbUtil extends DbUtil {
    @Override
    public Connection getConnection() {
        System.out.println("oracle");
        return null;
    }
}
