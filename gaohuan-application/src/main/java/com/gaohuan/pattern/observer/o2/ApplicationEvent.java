package com.gaohuan.pattern.observer.o2;

/**
 * <p>User: acer
 * <p>Date: 2016/7/29
 * <p>Version: 1.0
 */
public class ApplicationEvent extends EventObject {

    private final long timestamp;

    public ApplicationEvent(Object source) {
        super(source);
        this.timestamp = System.nanoTime();
    }

    public long getTimestamp() {
        return timestamp;
    }
}
