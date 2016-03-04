package com.gaohuan.thread.produceconsume2;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by huan on 2016/2/24.
 */
public class Storage {

    private final int MAX_SIZE = 100;

    private LinkedList<Object> list = new LinkedList<Object>();

    private final Lock lock = new ReentrantLock();

    //仓库满的条件变量
    private final Condition full = lock.newCondition();

    //仓库空的条件变量
    private final Condition empty = lock.newCondition();

    /**
     * 生产num个产品
     *
     * @param num
     */
    public void produce(int num) {
        //获取锁
        lock.lock();
        while (list.size() + num > MAX_SIZE) {
            System.out.println("[要生产的产品数量]:" + num + "\t[库存数量]: " + list.size() + "\t ,暂时不能执行生产任务！");

            try {
                full.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //生产条件满足的情况下，生产num个产品
        for (int i = 0; i < num; i++) {
            list.add(new Object());
        }

        System.out.println("[已经生产的产品数量]:" + num + "\t [现仓储数量为]:" + list.size());
        full.signalAll();
        empty.signalAll();
        //释放锁
        lock.unlock();

    }

    /**
     * 消费num个商品
     *
     * @param num
     */
    public void consume(int num) {

        lock.lock();
        //仓库数量不足
        while (list.size() < num) {
            System.out.println("[要消费的数量]:" + num + "\t [库存数量]:" + list.size() + "\t 暂时不能执行消费任务");
            try {
                empty.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //消费条件满足情况下，消费num个产品
        for (int i = 0; i < num; i++) {
            list.remove();
        }

        System.out.println("[已经消费产品数]:" + num + "\t [现存储数量]:" + list.size());
        full.signalAll();
        empty.signalAll();
        lock.unlock();

    }

    public int getMAX_SIZE() {
        return MAX_SIZE;
    }

    public LinkedList<Object> getList() {
        return list;
    }

    public void setList(LinkedList<Object> list) {
        this.list = list;
    }


}
