package com.gaohuan.spring.config;

import com.gaohuan.spring.HelloBean;
import org.springframework.beans.factory.config.PropertyResourceConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * 练习spring注解读取配置文件和加载注解配置
 */
@Configuration
@PropertySource(value = "classpath:spring-demo-config.properties", ignoreResourceNotFound = true)
public class AppConfig {

    @Bean
    public HelloBean helloBean() {
        return new HelloBean();
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
