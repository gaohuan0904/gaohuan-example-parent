package com.gaohuan.java8;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * jdk7 多重捕获异常
 * <p>User: GaoHuan
 * <p>Date: 2016/8/12
 */
public class Java8Main {

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

    /**
     * 遍历文件
     */
    public static void test3() {
        SimpleFileVisitor fileVisitor = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file);
                return super.visitFile(file, attrs);
            }
        };

        try {
            Files.walkFileTree(Paths.get("D:\\idea-work\\gaohuan-example-parent\\gaohuan-application\\src\\main\\java\\com\\gaohuan\\java8"), fileVisitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 锁
     * 锁的工作原理：
     * 在访问共享资源之前，申请用户保护资源的锁，当资源访问完成时释放锁。
     * 如果当某个线程正在使用锁时，另一个线程尝试申请锁，会被挂起。
     */
    public static void test4() {
    }

    /**
     * 字符转字节
     *
     * @param c
     * @return
     */
    public static byte[] charToByte(char c) {
        //Unicode常用1平面下，一个unicode对应2个字节
        byte[] b = new byte[2];
        b[0] = (byte) ((c & 0xff00) >> 8);//取高8位，然后右移8位
        b[1] = (byte) (c & 0xff);//取低8位
        return b;
    }

    public static void doFunc(List list, Consumer consumer) {
        Spliterator spliterator = list.spliterator();
        while (spliterator.tryAdvance(consumer)) ;
        System.out.println();
    }

    public static void main(String[] args) {

        test3();
    }

}
