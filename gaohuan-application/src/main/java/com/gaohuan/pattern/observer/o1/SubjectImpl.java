package com.gaohuan.pattern.observer.o1;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>User: acer
 * <p>Date: 2016/7/29
 * <p>Version: 1.0
 */
public class SubjectImpl implements Subject {
    //主题包含观察者
    public List<Observer> observers = new ArrayList<Observer>();

    public void pushEvent(Event event) {
        System.out.println("主题状态改变,正在通知观察者...");
        for (Observer observer : observers) {
            observer.onEvent(event);
        }
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        System.out.println(observer.getName() + "被移除了");
        observers.remove(observer);
    }
}
