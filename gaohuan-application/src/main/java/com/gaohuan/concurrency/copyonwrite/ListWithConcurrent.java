package com.gaohuan.concurrency.copyonwrite;

import com.gaohuan.pattern.lod.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList 修改时会把数据copy一份再修改，然后再把原来的数组引用指向新的数组。
 * <p>User: GaoHuan
 * <p>Date: 2016/8/8
 */
public class ListWithConcurrent {
    public final static Logger logger = LoggerFactory.getLogger(ListWithConcurrent.class);

    public static void main(String[] args) throws InterruptedException {

        final CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        Thread t = new Thread(new Runnable() {
            int count = 1;

            @Override
            public void run() {
                while (true) {
                    list.add((count++) + "");
                    logger.debug("count:" + count);
                }
            }
        });
        //守护线程，当所有非守护线程结束时，守护线程才会退出
        t.setDaemon(true);
        t.start();
        Thread.currentThread().sleep(3);
        for (String s : list) {
            logger.debug("s:" + s + ",hashCode of list:" + list.hashCode());
        }

    }
}
