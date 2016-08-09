package com.gaohuan.concurrency.copyonwrite;

import com.gaohuan.pattern.lod.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(new WriterTask(list));
        executor.execute(new ReaderTask(list));
        executor.execute(new ReaderTask(list));


    }

    static class WriterTask implements Runnable {
        private CopyOnWriteArrayList<String> list;

        public WriterTask(CopyOnWriteArrayList list) {
            this.list = list;
        }

        @Override
        public void run() {
            int count = 1;
            while (true) {
                list.add(String.valueOf(count++));
                logger.info("writer:" + count);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class ReaderTask implements Runnable {
        private CopyOnWriteArrayList<String> list;

        public ReaderTask(CopyOnWriteArrayList list) {
            this.list = list;
        }

        @Override
        public void run() {
//            while (true) {
            for (String s : list) {
                logger.info("reader:" + s + ",hasCode of List:" + list.hashCode());
            }
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }

        }
    }
}
