package com.gaohuan.java8.lambda.function;

import java.util.function.Function;

/**
 * <p>User: GaoHuan
 * <p>Date: 2016/8/15
 */
public class UseFunctionDemo {
    public static void main(String[] args) {
        Function<Integer, Integer> factorial = (n) -> {
            int result = 1;
            for (int i = 1; i <= n; i++) {
                result = i * result;
            }
            return result;
        };
        System.out.println(factorial.apply(3));
        System.out.println(factorial.apply(5));
        //returns a  function that always returns its input arguments
        Function<Integer, Integer> id = Function.identity();
        System.out.println(id.apply(3));
    }
}
