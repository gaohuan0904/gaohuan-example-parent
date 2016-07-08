package com.gaohuan.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

/**
 * Created by acer on 2016/7/8.
 */
public class HelloBean {

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Autowired
    private Environment environment;

    public void sayHello() {
        System.out.println("hello bean");
        System.out.println(username);
        System.out.println(password);
    }
}
