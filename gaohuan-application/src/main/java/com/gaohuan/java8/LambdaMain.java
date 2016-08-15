package com.gaohuan.java8;

import java.util.ArrayList;
import java.util.Collections;

/**
 * <p>User: GaoHuan
 * <p>Date: 2016/8/12
 */
public class LambdaMain {

    /**
     * lambda表达式使用
     * 函数式接口：仅定义一个抽象方法的接口
     * 把Lambda表达式赋值给函数式接口，相当于匿名接口实现
     * lambda表达式的参数的类型和数量必须与抽象方法的兼容
     */
    public static void test1() {
        MyNumber myNumber = () -> 123.45;
        double value = myNumber.getValue();
        System.out.println(value);
        myNumber = () -> Math.random() * 100;
        value = myNumber.getValue();
        System.out.println(value);

        NumericTest isEven = (n) -> (n % 2) == 0;
        if (isEven.test(10)) {
            System.out.println("10 is even");
        }
        if (!isEven.test(9)) {
            System.out.println("9 is not  even");
        }
        NumericTest isNonNeg = (n) -> n >= 0;
        if (isNonNeg.test(1)) {
            System.out.println(" 1 is non-negative");
        }
        if (!isNonNeg.test(-1)) {
            System.out.println("-1 is negative");
        }

    }

    /**
     * 块lambda表达式
     * 必须显示使用return语句来返回值
     */
    public static void test2() {
        NumericFunc factorial = (n) -> {
            int result = 1;
            for (int i = 0; i <= n; i++) {
                result = i * result;
            }
            return result;
        };
        System.out.println("the factorial of 3 is " + factorial.func(3));

        StringFunc reverse = (str) -> {
            String result = "";
            int i;
            for (i = str.length() - 1; i >= 0; i--) {
                result += str.charAt(i);
            }
            return result;
        };
        System.out.println(reverse.func("lambda"));

    }

    /**
     * 静态方法引用
     * 类似于：匿名函数内调用静态方法
     */
    public static void test3() {
        String inStr = "lambda add power to java";
        String outStr;
        outStr = stringOps(MyStringOps::strReverse, inStr);
        System.out.println(outStr);
    }

    /**
     * 实例方法引用
     * ClassName::instanceMethodName，第一个参数匹配调用对象，
     * 也就是说调用第一个参数的实例方法，传入第二个参数
     */
    public static void test4() {
        HighTemp[] weekDayHighs = {
                new HighTemp(90),
                new HighTemp(12),
                new HighTemp(14),
                new HighTemp(-1),
                new HighTemp(13),
        };

        int count = counter(weekDayHighs, HighTemp::sameTemp, new HighTemp(12));
        System.out.println("count:" + count);
        /*等同于下面的代码*/
        MyFunc myFunc = new MyFunc<HighTemp>() {
            @Override
            public boolean func(HighTemp v1, HighTemp v2) {
                return v1.sameTemp(v2);
            }
        };
        count = counter(weekDayHighs, myFunc, new HighTemp(12));
        System.out.println("count:" + count);

        count = counter(weekDayHighs, HighTemp::lessThanTemp, new HighTemp(19));
        System.out.println("count:" + count);

    }

    public static void test5() {
        ArrayList<MyClass> list = new ArrayList<>();
        list.add(new MyClass(1));
        list.add(new MyClass(2));
        list.add(new MyClass(4));
        list.add(new MyClass(7));
        list.add(new MyClass(3));
        MyClass maxObj = Collections.max(list, LambdaMain::compareMc);
        System.out.println("Maximum value is :" + maxObj.getVal());
    }

    public static void test6() {

    }


    static int compareMc(MyClass a, MyClass b) {
        return a.getVal() - b.getVal();
    }


    static <T> int counter(T[] vals, MyFunc<T> f, T v) {
        int count = 0;
        for (int i = 0; i < vals.length; i++) {
            if (f.func(vals[i], v)) {
                count++;
            }
        }
        return count;
    }


    static String stringOps(StringFunc sf, String s) {
        return sf.func(s);
    }

    static class MyStringOps {

        static String strReverse(String str) {
            String result = "";
            int i;
            for (i = str.length() - 1; i >= 0; i--) {
                result += str.charAt(i);
            }
            return result;
        }
    }

    interface StringFunc {
        String func(String n);
    }


    interface NumericFunc {
        int func(int n);
    }

    interface MyNumber {
        double getValue();
    }

    interface NumericTest {
        boolean test(int n);
    }

    interface MyFunc<T> {
        boolean func(T v1, T v2);
    }

    static class HighTemp {
        private int h;

        public HighTemp(int h) {
            this.h = h;
        }

        boolean sameTemp(HighTemp highTemp) {
            return h == highTemp.h;
        }

        boolean lessThanTemp(HighTemp highTemp) {
            return h < highTemp.h;
        }
    }

    static class MyClass {
        private int val;

        public MyClass(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

    }


    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
    }
}
