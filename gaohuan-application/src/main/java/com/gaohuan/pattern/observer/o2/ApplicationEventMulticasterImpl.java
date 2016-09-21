package com.gaohuan.pattern.observer.o2;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;

/**
 * <p>User: acer
 * <p>Date: 2016/7/29
 * <p>Version: 1.0
 */
public class ApplicationEventMulticasterImpl implements ApplicationEventMulticaster {
    private final Set<ApplicationListener> applicationListeners = new HashSet<ApplicationListener>();

    private Executor taskExecutor;


    @Override
    public void multicastEvent(ApplicationEvent applicationEvent) {
        for (ApplicationListener applicationListener : applicationListeners) {
            Executor executor = getTaskExecutor();
            if (executor != null) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        applicationListener.onApplicationEvent(applicationEvent);
                    }
                });
            } else {
                applicationListener.onApplicationEvent(applicationEvent);
            }

        }
    }

    @Override
    public void addApplicationListener(ApplicationListener applicationListener) {
        applicationListeners.add(applicationListener);
    }

    @Override
    public Set<ApplicationListener> getApplicationListeners() {
        return applicationListeners;
    }

    public void setTaskExecutor(Executor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public Executor getTaskExecutor() {
        return taskExecutor;
    }
}
