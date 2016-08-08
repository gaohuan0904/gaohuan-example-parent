package com.gaohuan.concurrency.copyonwrite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * List遍历时，如果list的内容被修改了，会抛出ConcurrentModificationException异常
 * <p>User: GaoHuan
 * <p>Date: 2016/8/8
 */
public class ListWithNoConcurrent {
    public final static Logger logger = LoggerFactory.getLogger(ListWithNoConcurrent.class);

    public static void main(String[] args) throws InterruptedException {

        final List<String> list = new ArrayList();
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
            logger.debug("s:" + s);
        }

    }
}
