package com.gaohuan.algorithm.queue;

/**
 * <p>User: GaoHuan
 * <p>Date: 2016/8/11
 */
public class QueueMain {
    public static void main(String[] args) {
        Queue queue = new QueueImpl();
        queue.add(1);
        queue.add(3);
        queue.add(5);
        queue.add(7);
        queue.add(9);
        queue.add(10);
        System.out.println(queue.size());
        int item = queue.remove();
        System.out.println(queue.size());
        queue.clear();
        System.out.println(queue.size());

    }
}
