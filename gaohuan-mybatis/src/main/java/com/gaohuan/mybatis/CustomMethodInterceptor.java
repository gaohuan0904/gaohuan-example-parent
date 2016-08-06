package com.gaohuan.mybatis;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.time.StopWatch;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * <p>User: acer
 * <p>Date: 2016/8/2
 * <p>Version: 1.0
 */
public class CustomMethodInterceptor implements MethodInterceptor {
    private Set<String> methodSet = new HashSet<>();

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // 执行方法
        Object object = invocation.proceed();
        // 方法名称
        String methodName = invocation.getMethod().getName();
        if (!methodSet.contains(methodName)) {
            methodSet.add(methodName);
            System.out.println(methodName);

        }

        // 获取计时器计时时间
        return object;
    }
}
