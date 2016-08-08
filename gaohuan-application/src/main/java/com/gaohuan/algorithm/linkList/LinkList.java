package com.gaohuan.algorithm.linkList;

import com.alibaba.fastjson.JSON;

/**
 * 链表对象-链接结构
 * <p>User: GaoHuan
 * <p>Date: 2016/8/8
 * <p>Version: 1.0
 */
public class LinkList {

    /**
     * 链表节点
     */
    public static class LinkNode {
        private int data;
        private LinkNode next;

        public LinkNode(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public LinkNode getNext() {
            return next;
        }

        public void setNext(LinkNode next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "LinkNode{" +
                    "data=" + data +
                    '}';
        }
    }

    /**
     * 头节点
     */
    private LinkNode head;
    /**
     * 尾节点
     */
    private LinkNode tail;
    /**
     * 链接大小
     */
    private int size;

    public LinkList() {
        this.head = null;
        this.tail = head;
        this.size = 0;
    }

    /**
     * 尾部添加节点
     *
     * @param i
     * @return
     */
    public LinkNode addLast(int i) {
        LinkNode newNode = new LinkNode(i);
        if (tail == null) {
            head = newNode;
            tail = head;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
        return newNode;
    }

    /**
     * 判断是否包含元素
     *
     * @param i
     * @return
     */
    public boolean contains(int i) {
        LinkNode current = head;
        while (current != null) {
            if (current.data == i) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * 获取指定索引下的及节点
     *
     * @param index
     * @return
     */
    public LinkNode get(int index) {
        return indexOf(index);
    }

    /**
     * 设置指定索引下的节点值
     *
     * @param index
     * @param data
     */
    public void set(int index, int data) {
        LinkNode node = indexOf(index);
        node.data = data;
    }

    /**
     * 根据索引值查找节点
     *
     * @param i
     * @return
     */
    private LinkNode indexOf(int i) {
        if (i < 0 || i >= size) {
            throw new IllegalArgumentException("参数超出范围");
        }
        LinkNode node = head;
        int index = 0;
        while (node != null && index != i) {
            node = node.next;
            index++;
        }
        return node;
    }

    /**
     * 删除元素
     *
     * @param i
     * @return
     */
    public LinkNode delete(int i) {
        LinkNode node = head;
        LinkNode parent = null;
        while (node != null && node.data != i) {
            parent = node;
            node = node.next;
        }
        LinkNode delNode = node;
        if (node != null) {
            if (node == head) {
                head = head.next;
            } else {
                node = node.next;
                parent.next = node;
            }
            size--;
        }
        return delNode;
    }

    /**
     * 获取链表大小
     *
     * @return
     */
    public int size() {
        return size;
    }

    public void print() {
        System.out.println(JSON.toJSONString(head));
    }

    public boolean isEmpty() {
        return size == 0;
    }

}
