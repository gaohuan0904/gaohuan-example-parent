package com.gaohuan.pattern.observer.o1;

/**
 * <p>User: acer
 * <p>Date: 2016/7/29
 * <p>Version: 1.0
 */
public class Event {
    private final long timestamp;//时间发生时间

    private Object source;//事件源

    public Event() {
        this.timestamp = System.nanoTime();
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }
}
