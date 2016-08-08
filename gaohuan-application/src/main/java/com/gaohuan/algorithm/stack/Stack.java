package com.gaohuan.algorithm.stack;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.ArrayUtils;

import java.util.EmptyStackException;

/**
 * <p>User: GaoHuan
 * <p>Date: 2016/8/8
 */
public class Stack {

    private final static int MAX_LENGTH = 10;
    private int[] repository = new int[MAX_LENGTH];
    private int top = -1;//栈顶位置
    private int size = 0;//元素数

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    /**
     * 入栈
     *
     * @param d
     */
    public void push(int d) {
        //判断待入栈的位置是否超过栈的大小限制
        if (top + 1 >= MAX_LENGTH) {
            throw new IndexOutOfBoundsException("超过最大限制范围");
        }
        repository[++top] = d;
        size++;
    }

    /**
     * 出栈
     *
     * @return
     */
    public int pop() {
        if (top < 0) {
            throw new EmptyStackException();
        }
        size--;
        return repository[top--];
    }

    public void print() {
        System.out.println(JSON.toJSONString(ArrayUtils.subarray(repository, 0, top + 1)));
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(1);
        stack.pop();
        stack.pop();
        stack.pop();
        stack.print();
        System.out.println(stack.isEmpty());
    }

}
