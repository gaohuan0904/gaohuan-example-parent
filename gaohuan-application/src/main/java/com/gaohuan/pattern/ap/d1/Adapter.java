package com.gaohuan.pattern.ap.d1;

/**
 * 类适配器
 * <p>User: GaoHuan
 * <p>Date: 2016/8/10
 */
public class Adapter extends Adaptee implements Target {

    @Override
    public void handle() {
        /*调用被适配对象的方法*/
        print();
    }
}
