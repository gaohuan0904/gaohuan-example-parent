package com.gaohuan.pattern.ap.d2;

/**
 * 对象适配器，具有对被适配对象的引用
 * <p>User: GaoHuan
 * <p>Date: 2016/8/10
 */
public class Adapter implements Target {

    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void handle() {
        /*调用被适配对象的方法*/
        adaptee.print();
    }
}
