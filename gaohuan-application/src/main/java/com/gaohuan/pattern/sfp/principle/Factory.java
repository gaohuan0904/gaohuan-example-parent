package com.gaohuan.pattern.sfp.principle;

/**
 * Created by acer on 2016/7/6.
 */
public class Factory {
    public static Product createProduct(String arg) {
        Product product = null;
        if (arg.equalsIgnoreCase("A")) {
            product = new ConcreteProductA();
        } else if (arg.equalsIgnoreCase("B")) {
            product = new ConcreteProductB();
        }
        return product;
    }
}
