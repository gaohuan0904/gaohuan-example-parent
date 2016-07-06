package com.gaohuan.pattern.isp.principle;

/**
 * Created by acer on 2016/7/6.
 */
public class Client {

    public static void main(String[] args) {
        ConcreteClass concreteClass = new ConcreteClass();
        ((DataHandler) concreteClass).dataRead();
        ((ChartHandler) concreteClass).createChart();

    }
}
