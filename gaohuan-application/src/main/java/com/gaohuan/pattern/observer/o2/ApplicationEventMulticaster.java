package com.gaohuan.pattern.observer.o2;

import java.util.Set;

/**
 * 事件付不起
 * <p>User: acer
 * <p>Date: 2016/7/29
 * <p>Version: 1.0
 */
public interface ApplicationEventMulticaster {

    public void multicastEvent(ApplicationEvent applicationEvent);

    public void addApplicationListener(ApplicationListener applicationListener);

    public Set<ApplicationListener> getApplicationListeners();
}
