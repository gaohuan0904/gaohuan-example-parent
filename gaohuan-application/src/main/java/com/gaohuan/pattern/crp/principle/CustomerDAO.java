package com.gaohuan.pattern.crp.principle;

/**
 * Created by acer on 2016/7/6.
 */
public class CustomerDAO {
    private DbUtil dbUtil;

    public CustomerDAO(DbUtil dbUtil) {
        this.dbUtil = dbUtil;
    }

    public void addCustomer() {
        dbUtil.getConnection();
    }
}
