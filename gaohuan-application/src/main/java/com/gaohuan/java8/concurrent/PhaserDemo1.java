package com.gaohuan.java8.concurrent;

import java.util.concurrent.Phaser;

/**
 * <p>User: GaoHuan
 * <p>Date: 2016/8/17
 */
public class PhaserDemo1 {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(1);
        int currentPhase;
        System.out.println("Starting...");
        new Thread(new PhaserRun(phaser, "A")).start();
        new Thread(new PhaserRun(phaser, "B")).start();
        new Thread(new PhaserRun(phaser, "C")).start();

        currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("phase " + currentPhase + " complete");

        currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("phase " + currentPhase + " complete");

        currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("phase " + currentPhase + " complete");
        phaser.arriveAndDeregister();
        if (phaser.isTerminated()) {
            System.out.println("the phase is terminate.");
        }


    }

    static class PhaserRun implements Runnable {

        Phaser phaser;
        String name;

        public PhaserRun(Phaser phaser, String name) {
            this.phaser = phaser;
            this.name = name;
            phaser.register();
        }

        @Override
        public void run() {
            System.out.println("Thread " + name + " beginning phase one");
            phaser.arriveAndAwaitAdvance();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Thread " + name + " beginning phase two");
            phaser.arriveAndAwaitAdvance();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread " + name + " beginning phase three");
            //结束
            phaser.arriveAndDeregister();

        }
    }
}
