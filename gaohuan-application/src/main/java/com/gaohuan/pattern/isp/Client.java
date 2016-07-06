package com.gaohuan.pattern.isp;

/**
 * Created by acer on 2016/7/6.
 */
public class Client {
    public static void main(String[] args) {
        CustomerDataDisplay customerDataDisplay = new ConcreteClass();
        customerDataDisplay.dataRead();
    }
}
