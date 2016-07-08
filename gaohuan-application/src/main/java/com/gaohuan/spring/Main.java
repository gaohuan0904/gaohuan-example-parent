package com.gaohuan.spring;

import com.gaohuan.spring.config.AppConfig;
import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by acer on 2016/7/8.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        HelloBean helloBean = context.getBean(HelloBean.class);
        helloBean.sayHello();
    }
}
