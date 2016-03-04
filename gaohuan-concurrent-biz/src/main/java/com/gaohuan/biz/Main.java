package com.gaohuan.biz;

import com.gaohuan.biz.entity.User;
import com.gaohuan.biz.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by gh on 2016/3/4 0004.
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) context.getBean("userService");
        User user = new User();
        user.setAge(25);
        user.setName("gaohuan");
        userService.save(user);
        System.out.println("成功");
        System.exit(1);
    }
}
