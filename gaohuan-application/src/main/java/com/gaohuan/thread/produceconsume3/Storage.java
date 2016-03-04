package com.gaohuan.thread.produceconsume3;

import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by huan on 2016/2/24.
 */
public class Storage {

    private final int MAX_SIZE = 100;

    private LinkedBlockingDeque<Object> list = new LinkedBlockingDeque<Object>(100);

    /**
     * 生产num个产品
     *
     * @param num
     */
    public void produce(int num) {

        if (list.size() == MAX_SIZE) {
            System.out.println("[库存数量]: " + list.size() + "\t ,暂时不能执行生产任务！");
        }


        //生产条件满足的情况下，生产num个产品
        for (int i = 0; i < num; i++) {
            try {
                list.put(new Object());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("生产-[现仓储数量]:" + list.size());
        }

    }

    /**
     * 消费num个商品
     *
     * @param num
     */
    public void consume(int num) {

        if (list.size() == 0) {
            System.out.println("[库存数量]:" + list.size() + "\t 暂时不能执行消费任务");

        }

        //消费条件满足情况下，消费num个产品
        for (int i = 0; i < num; i++) {
            try {
                list.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("消费-[现存储数量]:" + list.size());
        }


    }

    public int getMAX_SIZE() {
        return MAX_SIZE;
    }


}
