package com.gaohuan.pattern.bridge;

/**
 * Implementor子类
 */
public class Engine2000 implements Engine {
    @Override
    public void installEngine() {
        System.out.println("安装引擎2000发动机");
    }
}
