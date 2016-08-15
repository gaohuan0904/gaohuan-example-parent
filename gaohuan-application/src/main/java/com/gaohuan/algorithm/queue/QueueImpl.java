package com.gaohuan.algorithm.queue;


/**
 * <p>User: GaoHuan
 * <p>Date: 2016/8/11
 */
public class QueueImpl implements Queue {

    private QueueNode first;
    private QueueNode last;
    private int size;

    static class QueueNode {
        private int item;
        private QueueNode prev;
        private QueueNode next;

        public QueueNode(QueueNode prev, int item, QueueNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    public QueueImpl() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }


    private boolean addLast(int e) {
        QueueNode node = new QueueNode(null, e, null);
        if (last == null) {
            first = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }
        size++;
        return true;
    }

    @Override
    public boolean add(int e) {
        return addLast(e);
    }

    @Override
    public boolean offer(int e) {
        return add(e);
    }

    @Override
    public int remove() {
        if (first == null) {
            throw new IllegalStateException();
        }
        int item = first.item;
        first = first.next;
        size--;
        return item;
    }

    @Override
    public int poll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int peek() {
        if (first == null) {
            throw new IllegalStateException(" the queue is empty");
        }
        return first.item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
    }
}
