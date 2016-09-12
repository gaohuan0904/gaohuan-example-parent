package com.gaohuan.proxy.spring;

/**
 * Created by g.h on 2016/9/9.
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "Hi " + name;
    }
}
