package com.gaohuan.shiro.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by gh on 2015/12/7.
 */
public class SpringUtils implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

    }

    public <T> T getBean(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }

    public <T> T getBean(Class<T> clz) {
        return applicationContext.getBean(clz);
    }

    public boolean containsBean(String beanName) {
        return applicationContext.containsBean(beanName);
    }

    public boolean isSingleton(String beanName) {
        return applicationContext.isSingleton(beanName);
    }

    public Class<?> getTypes(String beanName) {
        return applicationContext.getType(beanName);
    }

    public String[] getAliases(String beanName) {
        return applicationContext.getAliases(beanName);
    }
}
