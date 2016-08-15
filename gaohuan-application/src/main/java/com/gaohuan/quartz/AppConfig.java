package com.gaohuan.quartz;

import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;

import java.util.Arrays;

/**
 * spring- quartz 注解配置
 */
@Configuration
@ComponentScan(basePackages = "com.gaohuan.quartz.springquartz", excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class})})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {

    @Bean
    public QuartzJob quartzJob() {
        return new QuartzJob();
    }

    /**
     * JobDetail
     *
     * @return
     */
    @Bean
    public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean() {
        MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();
        methodInvokingJobDetailFactoryBean.setTargetObject(quartzJob());
        methodInvokingJobDetailFactoryBean.setTargetMethod("doWork");
        return methodInvokingJobDetailFactoryBean;
    }

    /**
     * Trigger
     *
     * @return
     */
    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean() {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
        cronTriggerFactoryBean.setCronExpression("10,15,20,25,30,35,40,45,50,55 * * * * ?");
        return cronTriggerFactoryBean;
    }

    /**
     * SchedulerFactory
     *
     * @return
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setTriggers(((Trigger[]) Arrays.asList(cronTriggerFactoryBean().getObject()).toArray()));
        return schedulerFactoryBean;
    }

    @Bean
    public Scheduler scheduler() {
        return schedulerFactoryBean().getObject();
    }


}
