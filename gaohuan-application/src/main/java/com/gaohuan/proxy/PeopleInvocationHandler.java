package com.gaohuan.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by acer on 2016/7/22.
 */
public class PeopleInvocationHandler implements InvocationHandler {
    private People target;//要代理的目标对象
    private Interceptor interceptor;

    public PeopleInvocationHandler(People target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        interceptor.before();
        Object object = method.invoke(target, args);
        interceptor.after();

        return object;
    }

    public Interceptor getInterceptor() {
        return interceptor;
    }

    public void setInterceptor(Interceptor interceptor) {
        this.interceptor = interceptor;
    }
}
