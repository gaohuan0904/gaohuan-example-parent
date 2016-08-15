package com.gaohuan.java8.lambda.constructor;

/**
 * <p>User: GaoHuan
 * <p>Date: 2016/8/12
 */
public interface Factory<T> {
    T create(int n);
}
