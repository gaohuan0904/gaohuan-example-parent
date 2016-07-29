package com.gaohuan.pattern.observer.o2;

import java.io.Serializable;

/**
 * <p>User: acer
 * <p>Date: 2016/7/29
 * <p>Version: 1.0
 */
public class EventObject implements Serializable {

    private static final long serialVersionUID = 6176308934663470737L;

    protected Object source;

    public EventObject(Object source) {
        this.source = source;
    }
}
