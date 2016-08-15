package com.gaohuan.java8.lambda.constructor;

/**
 * <p>User: GaoHuan
 * <p>Date: 2016/8/12
 */
public class ConstructorRefDemo {

    public static void main(String[] args) {
        Factory<ClassTwo> f1 = ClassTwo::new;
        ClassTwo classTwo = f1.create(100);
        System.out.println("val in mc is :" + classTwo.getVal());
        Factory<ClassOne> f2 = ClassOne::new;
        ClassOne classOne = f2.create(120);
        System.out.println("val in mc is :" + classOne.getVal());

    }
}
