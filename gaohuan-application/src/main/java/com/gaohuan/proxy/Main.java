package com.gaohuan.proxy;

import java.lang.reflect.Proxy;

/**
 * jdk 动态代理
 */
public class Main {
    public static void main(String[] args) {
        PeopleInvocationHandler invocationHandler = new PeopleInvocationHandler(new PeopleImpl());
        invocationHandler.setInterceptor(new LogInterceptor());
        People peopleProxy = (People) Proxy.newProxyInstance(People.class.getClassLoader(), new Class[]{People.class}, invocationHandler);
        peopleProxy.say();
    }
}
