package com.gaohuan.java8.concurrent;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <p>User: GaoHuan
 * <p>Date: 2016/8/24
 */
public class StreamDemo {

    public static void main(String[] args) {
        test4();
    }

    public static void test1() {
        List<Integer> list = new ArrayList();
        list.add(7);
        list.add(1);
        list.add(3);
        list.add(17);
        list.add(2);
        list.add(5);

        Stream<Integer> stream = list.stream();
        Optional<Integer> minVal = stream.min(Integer::compare);
        if (minVal.isPresent()) {
            System.out.println("minVal:" + minVal.get());
        }

        stream = list.stream();
        Optional<Integer> maxVal = stream.max(Integer::compare);
        if (maxVal.isPresent()) {
            System.out.println("maxVal:" + maxVal.get());
        }
        stream = list.stream().sorted();
        System.out.println("sorted stream:");
        stream.forEach((n) -> System.out.println(n));

        stream = list.stream().sorted().filter((n) -> (n % 2) == 1);
        System.out.println("filter:");
        stream.forEach((n) -> System.out.println(n));

        stream = list.stream().sorted().filter((n) -> (n % 2) == 1).filter((n) -> n > 5);
        System.out.println("filter:");
        stream.forEach((n) -> System.out.println(n));

        Optional<Integer> optional = list.stream().reduce((a, b) -> a * b);
        if (optional.isPresent()) {
            System.out.println("optional:" + optional.get());
        }
        int val = list.stream().reduce(1, (a, b) -> a * b);
        System.out.println("val:" + val);
        int even = list.stream().reduce(1, (a, b) -> {
            if (b % 2 == 0) {
                return a * b;
            } else {
                return a;
            }
        });
        System.out.println("even:" + even);
        /*
           并行流：
           任何操作必须是无状态的，必须是不干预的，并且具有关联性。
         */

        optional = list.parallelStream().reduce((a, b) -> a * b);
        if (optional.isPresent()) {
            System.out.println("parallel:" + optional.get());
        }
    }

    public static void test2() {
        List<Double> list = new ArrayList();
        list.add(7.0);
        list.add(1.0);
        list.add(3.0);
        list.add(17.0);
        list.add(2.0);
        list.add(5.0);
        Stream<Double> stream = list.stream().map((a) -> Math.sqrt(a));
//        stream.forEach((n) -> System.out.println(n));
        double val = stream.reduce(1.0, (a, b) -> a * b);
        System.out.println("val:" + val);

        IntStream intStream = list.stream().mapToInt((a) -> (int) Math.ceil(a));
        System.out.println("IntStream:");
        intStream.forEach((n) -> System.out.println(n));

    }

    static class NamePhoneEmail {
        String name;
        String phoneNum;
        String email;

        public NamePhoneEmail(String name, String phoneNum, String email) {
            this.name = name;
            this.phoneNum = phoneNum;
            this.email = email;
        }
    }

    static class NamePhone {
        String name;
        String phoneNum;

        public NamePhone(String name, String phoneNum) {
            this.name = name;
            this.phoneNum = phoneNum;
        }
    }

    public static void test3() {
        ArrayList<NamePhoneEmail> list = new ArrayList<>();
        list.add(new NamePhoneEmail("e", "1111-5555", "111@11.com"));
        list.add(new NamePhoneEmail("a", "2222-5555", "222@11.com"));
        list.add(new NamePhoneEmail("b", "3333-5555", "333@11.com"));
        list.add(new NamePhoneEmail("c", "4444-5555", "444@11.com"));
        list.add(new NamePhoneEmail("d", "5555-5555", "555@11.com"));

        Stream<NamePhone> stream = list.stream().map((a) -> new NamePhone(a.name, a.phoneNum));
        List<NamePhone> namePhoneList = stream.collect(Collectors.toList());
        namePhoneList.forEach((n) -> System.out.println(n.name + ":" + n.phoneNum));

        stream = list.stream().map((a) -> new NamePhone(a.name, a.phoneNum));
        Set<NamePhone> namePhoneSet = stream.collect(Collectors.toSet());
        namePhoneSet.forEach((n) -> System.out.println(n.name + ":" + n.phoneNum));

    }

    public static void test4() {
        List<Double> list = new ArrayList();
        list.add(7.0);
        list.add(1.0);
        list.add(3.0);
        list.add(17.0);
        list.add(2.0);
        list.add(5.0);
        System.out.println("iterator:");
        Iterator<Double> iterator = list.stream().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("spliterator:");
        Spliterator<Double> spliterator = list.spliterator();
        while (spliterator.tryAdvance((n) -> System.out.println(n))) ;

    }
}
