package com.gaohuan.algorithm.queue;

/**
 * <p>User: GaoHuan
 * <p>Date: 2016/8/11
 */
public interface Queue {

    boolean add(int e);

    boolean offer(int e);

    int remove();

    int poll();

    int peek();

    int size();

    boolean isEmpty();

    void clear();
}
