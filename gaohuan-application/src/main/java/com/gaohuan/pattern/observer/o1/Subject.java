package com.gaohuan.pattern.observer.o1;


/**
 * <p>User: acer
 * <p>Date: 2016/7/29
 * <p>Version: 1.0
 */
public interface Subject {

    public void pushEvent(Event event);

    public void addObserver(Observer observer);

    public void removeObserver(Observer observer);

}
