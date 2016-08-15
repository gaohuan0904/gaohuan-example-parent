package com.gaohuan.java8;

/**
 * jdk7 多重捕获异常
 * <p>User: GaoHuan
 * <p>Date: 2016/8/12
 */
public class MultiCatch {
    public static void main(String[] args) {
        int a = 10;
        int b = 0;
        int vals[] = {1, 2, 3};
        try {
//            int result = a / b;
            vals[10] = 19;
        } catch (ArithmeticException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
