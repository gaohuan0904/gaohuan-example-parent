package com.gaohuan.pattern.observer.o1;

/**
 * <p>User: acer
 * <p>Date: 2016/7/29
 * <p>Version: 1.0
 */
public interface Observer {

    public String getName();

    public void onEvent(Event event);
}
