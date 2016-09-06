package com.gaohuan.thrift.s1;

import org.apache.thrift.TException;

/**
 * Created by huan on 2016/9/6.
 */
public class HelloWorldImpl implements HelloWorldService.Iface {
    @Override
    public String sayHello(String username) throws TException {
        return "Hi," + username + "  welcome!";
    }
}
