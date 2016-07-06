package com.gaohuan.pattern.srp;

import java.sql.Connection;
import java.util.List;

/**
 * 未使用单一职责
 */
public class CustomerDataChart {
    /**
     * 获取数据库连接
     *
     * @return
     */
    public Connection getConnection() {
        return null;
    }

    /**
     * 查询客户
     *
     * @return
     */
    public List findCustomers() {
        return null;
    }

    /**
     * 创建图表
     */
    public void createChart() {

    }

    /**
     * 显示图表
     */
    public void displayChart() {

    }
}
