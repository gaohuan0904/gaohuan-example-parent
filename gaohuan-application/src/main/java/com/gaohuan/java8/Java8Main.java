package com.gaohuan.java8;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * jdk7 多重捕获异常
 * <p>User: GaoHuan
 * <p>Date: 2016/8/12
 */
public class Java8Main {
    public static void main(String[] args) {
        test2();
    }

    public static void test1() {
        Package[] packages;
        packages = Package.getPackages();
        for (int i = 0; i < packages.length; i++) {
            System.out.println(packages[i].getName());
            System.out.println(packages[i].getImplementationTitle());
            System.out.println(packages[i].getImplementationVendor());
            System.out.println(packages[i].getImplementationVersion());
        }

    }

    public static void test2() {
        ArrayList<Double> sqrs = new ArrayList<>();
        ArrayList<Double> vals = new ArrayList<>();
        vals.add(1.0);
        vals.add(2.0);
        vals.add(3.0);
        vals.add(4.0);
        vals.add(5.0);
        Consumer out = (n) -> System.out.println(n);
        Consumer sqrt = (n) -> sqrs.add(Math.sqrt((Double) n));

        doFunc(vals, out);

        doFunc(vals, sqrt);

        doFunc(sqrs, out);


    }

    public void test3() {

    }

    public static void doFunc(List list, Consumer consumer) {
        Spliterator spliterator = list.spliterator();
        while (spliterator.tryAdvance(consumer)) ;
        System.out.println();
    }

}
