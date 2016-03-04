package com.gaohuan.dubbo.demo.provider;

import com.gaohuan.dubbo.demo.DemoService;

/**
 * Created by gh on 2016/2/29 0029.
 */
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
