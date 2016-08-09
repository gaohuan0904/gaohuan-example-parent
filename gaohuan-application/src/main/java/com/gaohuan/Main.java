package com.gaohuan;

import com.gaohuan.spring.HelloBean;
import org.apache.commons.lang.ArrayUtils;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gh on 2016/5/24 0024.
 */
public class Main {
    public static void main(String[] args) {
        /*
        try {
            Class<HelloWorld> t = (Class<HelloWorld>) Class.forName("com.gaohuan.HelloWorld");
            HelloWorld h = t.newInstance();
            h.hello();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonthValue());
        System.out.println(localDate.getDayOfMonth());

    }

}
