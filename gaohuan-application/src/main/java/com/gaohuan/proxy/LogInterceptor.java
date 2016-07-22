package com.gaohuan.proxy;

/**
 * Created by acer on 2016/7/22.
 */
public class LogInterceptor implements Interceptor {
    @Override
    public void before() {
        System.out.println("before log.");
    }

    @Override
    public void after() {
        System.out.println("after log.");

    }
}
