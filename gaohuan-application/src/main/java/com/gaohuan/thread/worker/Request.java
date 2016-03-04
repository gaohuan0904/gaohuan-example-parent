package com.gaohuan.thread.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * 请求对象
 * Created by gh on 2015/12/16.
 */
public class Request {
    public static final Logger LOGGER = LoggerFactory.getLogger(Request.class);


    private String name;

    private int number;

    private static final Random random = new Random();

    public Request(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public void execute() {
        LOGGER.debug(Thread.currentThread().getName() + " execute : " + this);
//        try {
//            Thread.sleep(random.nextInt(100));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
