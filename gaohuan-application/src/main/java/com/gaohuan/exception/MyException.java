package com.gaohuan.exception;

import java.util.zip.DataFormatException;

/**
 * Created by gh on 2016/1/12.
 */
public class MyException {

    public static void main(String[] args) {
        try {
            throw new DataFormatException("测试1");
//            throw new NullPointerException("测试");
        } catch (Exception e) {
            printStackTrace(e);
//            throw  e;
        }
    }

    public static void printStackTrace(Throwable thr) {
        System.out.println(ExceptionUtil.getStackTrace(thr));

    }
}
