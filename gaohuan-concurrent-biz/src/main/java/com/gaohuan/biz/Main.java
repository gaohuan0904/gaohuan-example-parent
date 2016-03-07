package com.gaohuan.biz;

import com.gaohuan.biz.business.UserBusiness;
import com.gaohuan.biz.common.BusinessResult;
import com.gaohuan.biz.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.net.MalformedURLException;

/**
 * Created by gh on 2016/3/4 0004.
 */
public class Main {

    public static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws MalformedURLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        /*
        UserService userService = (UserService) context.getBean("userService");
        User user = new User();
        user.setAge(25);
        user.setName("gaohuan");
        userService.save(user);
        */

        UserBusiness userBusiness = context.getBean(UserBusiness.class);

        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        //线程池所使用的缓冲队列
        poolTaskExecutor.setQueueCapacity(200);
        //线程池维护线程的最少数量
        poolTaskExecutor.setCorePoolSize(10);
        //线程池维护线程的最大数量
        poolTaskExecutor.setMaxPoolSize(1000);
        //线程池维护线程所允许的空闲时间
        poolTaskExecutor.setKeepAliveSeconds(30000);
        poolTaskExecutor.initialize();


        //测试1
        for (int i = 0; i < 10; i++) {
            poolTaskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10; j++) {
                        User user = new User("高欢-" + j, 25);
                        BusinessResult<String> result = userBusiness.signIn(user);
                    }
                }
            });
        }


        for (int i = 0; i < 10; i++) {
            poolTaskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 2; j++) {
                        userBusiness.signIn(null);
                    }
                }
            });
        }


        System.out.println("-------------运行成功---------------");
//        System.exit(1);
    }
}
