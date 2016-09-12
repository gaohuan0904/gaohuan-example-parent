package com.gaohuan.proxy.spring;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * Created by g.h on 2016/9/9.
 */
public class ProxyFactoryMain {
    public static void main(String[] args) {
        Class[] interfaces = new Class[]{HelloService.class};
        ProxyFactory proxyFactory = new ProxyFactory(interfaces);
        proxyFactory.setTarget(new HelloServiceImpl());
        proxyFactory.setOpaque(true);
        //可以添加过个前置通知
        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                System.out.println("before method :" + method.getName());
            }
        });
        proxyFactory.addAdvice(new AfterReturningAdvice() {
            @Override
            public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
                System.out.println("after method :" + method.getName());
            }
        });

        proxyFactory.addAdvice(new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation methodInvocation) throws Throwable {
                System.out.println("before interceptor...");
                Object result = methodInvocation.getMethod().invoke(methodInvocation.getThis(), methodInvocation.getArguments());
                System.out.println("interceptor: " + result);
                System.out.println("after interceptor...");
                return result;
            }
        });

        HelloService helloService = (HelloService) proxyFactory.getProxy();
        String result = helloService.hello("gaohuan");
        System.out.println("result:" + result);

    }
}
