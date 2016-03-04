package com.gaohuan.biz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.MalformedURLException;

/**
 * Created by gh on 2016/3/4 0004.
 */
public class Main {

    public static void main(String[] args) throws MalformedURLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        /*
        UserService userService = (UserService) context.getBean("userService");
        User user = new User();
        user.setAge(25);
        user.setName("gaohuan");
        userService.save(user);
        */
        Example example = (Example) context.getBean("example");
        example.addLink("高欢", "www.baidu.com");
        System.out.println("成功");
        System.exit(1);
    }
}
