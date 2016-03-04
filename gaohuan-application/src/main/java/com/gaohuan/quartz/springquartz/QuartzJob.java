package com.gaohuan.quartz.springquartz;

import java.util.Date;

/**
 * Created by gh on 2015/12/14.
 */
public class QuartzJob {

    public void doWork() {
        System.out.println("hello quartz " + new Date());
    }
}
