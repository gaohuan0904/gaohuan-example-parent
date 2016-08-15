package com.gaohuan.algorithm.tree;

import com.alibaba.fastjson.JSON;

/**
 * 二叉树
 * <p>User: GaoHuan
 * <p>Date: 2016/8/11
 */
public class BinaryTree {

    /**
     * 二叉树节点
     */
    static class Node {
        private int data;
        private Node parent;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
            parent = null;
            left = null;
            right = null;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }

    /**
     * 根节点
     */
    private Node root = null;
    /**
     * 节点数
     */
    private int size = 0;

    public BinaryTree() {
    }

    public void add(int e) {
        Node newNode = new Node(e);
        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            for (; ; ) {
                if (e < current.data) {
                    if (current.left == null) {
                        current.left = newNode;
                        break;
                    }
                    current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = newNode;
                        break;
                    }
                    current = current.right;
                }
            }

        }
        size++;
    }


    public void remove(int e) {
        Node node = findNode(e, root);
        if (node != null) {
            Node parent = node.parent;
            if (parent == null) {
                Node rootRight = root.right;
                root = node.left;
                root.right = rootRight;
            } else {
                parent.left = node.left;
                parent.right = node.right;
            }
        }

    }

    private Node findNode(int e, Node node) {
        if (node == null) {
            return null;
        }
        if (node.data == e) {
            return node;
        }
        Node leftFind = findNode(e, node.left);
        if (leftFind != null) {
            return leftFind;
        }

        Node rightFind = findNode(e, node.right);
        if (rightFind != null) {
            return rightFind;
        }

        return null;
    }

    public boolean contains(int e) {
        return findNode(e, root) != null;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public void clear() {
        root = null;
        size = 0;
    }

    public void printTree() {
        System.out.println(JSON.toJSONString(root));
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.add(6);
        tree.add(3);
        tree.add(2);
        tree.add(5);
        tree.add(9);
        tree.printTree();
    }
}
