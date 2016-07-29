package com.gaohuan.pattern.observer.o1;

/**
 * <p>User: acer
 * <p>Date: 2016/7/29
 * <p>Version: 1.0
 */
public class ObserverImpl implements Observer {

    private String observerName;

    public ObserverImpl(String observerName) {
        this.observerName = observerName;
    }

    @Override
    public void onEvent(Event event) {
        System.out.println(observerName + ":接收到事件，正在处理...");
        System.out.println("事件产生时间:" + event.getTimestamp());
    }

    public String getName() {
        return observerName;
    }
}
