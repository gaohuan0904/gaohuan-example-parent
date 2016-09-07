package com.gaohuan.concurrency.copyonwrite;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>User: GaoHuan
 * <p>Date: 2016/8/9
 */
public class MyCopyOnWrite<T> {

    private ReentrantLock lock = new ReentrantLock();
    private volatile Object[] array = new Object[0];

    public MyCopyOnWrite() {
    }

    public void set(T t) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            //复制新数组
            Object[] newElements = Arrays.copyOf(elements, len + 1);
            //添加新元素
            newElements[len] = t;
            //放回新数组
            setArray(newElements);
        } finally {
            lock.unlock();
        }


    }

    public Object get(int index) {
        return getArray()[index];
    }

    public Object[] getArray() {
        return array;
    }

    public void setArray(Object[] array) {
        this.array = array;
    }

    public void print() {
        System.out.println(Thread.currentThread().getName());
        for (Object a : array) {
            System.out.print(a + "\t");
        }
        System.out.println(Thread.currentThread().getName());


    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        MyCopyOnWrite<Integer> copyOnWrite = new MyCopyOnWrite<>();
        for (int i = 5; i < 15; i++) {
            final int n = i;
            executorService.execute(() -> {
                copyOnWrite.set(n);
                copyOnWrite.print();
            });
        }

    }
}
