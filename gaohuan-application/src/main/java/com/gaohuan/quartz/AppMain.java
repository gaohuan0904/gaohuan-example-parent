package com.gaohuan.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by gh on 2015/12/14.
 */
public class AppMain {

    private static final AnnotationConfigApplicationContext ctx;

    static {
        ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();
    }


    public static void main(String[] args) throws SchedulerException {

        Scheduler scheduler = getBean("scheduler", Scheduler.class);
        if (scheduler != null) {
            scheduler.start();
        }

    }

    public static <T> T getBean(String beanName, Class<T> clazz) {
        return (T) ctx.getBean(beanName, clazz);

    }
}


