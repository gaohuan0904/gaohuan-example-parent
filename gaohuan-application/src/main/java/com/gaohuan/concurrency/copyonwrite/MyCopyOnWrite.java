package com.gaohuan.concurrency.copyonwrite;

import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>User: GaoHuan
 * <p>Date: 2016/8/9
 */
public class MyCopyOnWrite {

    private ReentrantLock lock = new ReentrantLock();
    private volatile Object[] array = new Object[10];

    public MyCopyOnWrite() {
    }

    public void set() {
        lock.lock();

    }

    public Object get() {
        return null;
    }
}
