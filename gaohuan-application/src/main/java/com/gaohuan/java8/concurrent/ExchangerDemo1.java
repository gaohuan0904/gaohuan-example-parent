package com.gaohuan.java8.concurrent;

import java.util.concurrent.Exchanger;

/**
 * 简化2个线程之间的数据交换，简单的进行等待，直到2个独立的线程调用exchange()方法为止。
 * 交换线程提供的数据。
 */
public class ExchangerDemo1 {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<String>();
        new Thread(new UseString(exchanger)).start();
        new Thread(new MakeString(exchanger)).start();

    }

    static class MakeString implements Runnable {
        Exchanger<String> exchanger;
        String str;

        public MakeString(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
            this.str = new String();
        }

        @Override
        public void run() {
            char ch = 'A';
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 5; j++) {
                    str += ch++;
                }
                try {
                    System.out.println(Thread.currentThread().getName() + ":开始交换");
                    str = exchanger.exchange(str);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    static class UseString implements Runnable {
        Exchanger<String> exchanger;
        String str;

        public UseString(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                try {
                    str = exchanger.exchange(new String());
                    System.out.println(Thread.currentThread().getName() + " UseString:" + str);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
