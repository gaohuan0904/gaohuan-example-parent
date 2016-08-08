package com.gaohuan.algorithm.linkList;

/**
 * <p>User: acer
 * <p>Date: 2016/8/8
 * <p>Version: 1.0
 */
public class Main {
    public static void main(String[] args) {
        LinkList linkList = new LinkList();
        linkList.addLast(3);
        linkList.addLast(4);
        linkList.addLast(1);
        linkList.addLast(2);
        linkList.print();
        System.out.println(linkList.size());
        System.out.println(linkList.isEmpty());
        System.out.println(linkList.contains(4));
        System.out.println(linkList.get(linkList.size() - 1));
        System.out.println(linkList.delete(1));
        linkList.print();
    }
}
