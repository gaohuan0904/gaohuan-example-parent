package com.gaohuan.pattern.ap.d2;

/**
 * <p>User: GaoHuan
 * <p>Date: 2016/8/10
 */
public class Client {
    public static void main(String[] args) {
        Target target = new Adapter(new Adaptee());
        target.handle();
    }
}
