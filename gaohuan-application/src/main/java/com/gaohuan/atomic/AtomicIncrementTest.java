package com.gaohuan.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by acer on 2016/7/4.
 */
public class AtomicIncrementTest {

    public static final AtomicInteger aci = new AtomicInteger(0);
    public final int id;

    public AtomicIncrementTest() {
        this.id = aci.getAndIncrement();
    }

    public static void main(String[] args) {
        AtomicIncrementTest test1 = new AtomicIncrementTest();
        AtomicIncrementTest test2 = new AtomicIncrementTest();
        AtomicIncrementTest test3 = new AtomicIncrementTest();
        AtomicIncrementTest test4 = new AtomicIncrementTest();

    }
}
