package com.gaohuan.java8.concurrent;

import java.util.concurrent.Semaphore;

/**
 * <p>User: GaoHuan
 * <p>Date: 2016/8/17
 */
public class SemaphoreDemo2 {
    private static final Q q = new Q();

    static class Q {
        public static int n;
        public static Semaphore consumer = new Semaphore(0);
        public static Semaphore producer = new Semaphore(1);

        void get() {
            try {
                consumer.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("get: " + n);
            producer.release();//通知生产者消费完了一个，可以生产了

        }

        void put(int n) {
            try {
                producer.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.n = n;
            System.out.println("put: " + n);
            consumer.release();//通知消费释放一个许可信号

        }
    }


    static class ConsumerThread implements Runnable {
        Q q;

        public ConsumerThread(Q q) {
            this.q = q;
        }

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                q.get();
            }
        }
    }

    static class ProducerThread implements Runnable {
        Q q;

        public ProducerThread(Q q) {
            this.q = q;
        }

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                q.put(i);
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ProducerThread(q)).start();
        new Thread(new ConsumerThread(q)).start();


    }

}
