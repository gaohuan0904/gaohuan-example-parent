package com.gaohuan.pattern.sfp.principle;

/**
 * Created by acer on 2016/7/6.
 */
public class Client {
    public static void main(String[] args) {
        Product product = Factory.createProduct("A");
        product.commonMethod();
        product.customMethod();

        Chart chart = ChartFactory.getChart();
        chart.display();
    }
}
