package com.gaohuan.algorithm;

import com.alibaba.fastjson.JSON;

/**
 * 二叉搜索树对象
 * Created by huan on 2016/8/6.
 */
public class BinarySearchTree {

    /**
     * 二叉搜索节点对象
     */
    public static class BinaryNode {
        private int data;
        private BinaryNode left;
        private BinaryNode right;
        private BinaryNode parent = null;

        public BinaryNode(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public BinaryNode getLeft() {
            return left;
        }

        public void setLeft(BinaryNode left) {
            this.left = left;
        }

        public BinaryNode getRight() {
            return right;
        }

        public void setRight(BinaryNode right) {
            this.right = right;
        }

    }

    /**
     * 根节点
     */
    private BinaryNode rootNode;

    /**
     * 查找节点
     *
     * @param i
     * @return
     */
    public BinaryNode search(int i) {
        BinaryNode current = rootNode;
        while (current != null && current.data != i) {

            if (i < current.data) {//查找左子树
                current = current.left;
            } else {//查找右子树
                current = current.right;
            }
        }
        return current;
    }

    /**
     * 插入节点
     *
     * @param i
     * @return
     */
    public BinaryNode insert(int i) {
        BinaryNode newNode = new BinaryNode(i);
        BinaryNode current = rootNode;
        //根节点为空
        if (current == null) {
            rootNode = newNode;
            return rootNode;
        }
        for (; ; ) {
            if (i < current.data) {
                if (current.left == null) {
                    current.left = newNode;
                    newNode.parent = current;
                    break;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = newNode;
                    newNode.parent = current;
                    break;
                }
                current = current.right;
            }
        }
        return newNode;
    }

    /**
     * 移除元素,分3种情况
     *
     * @param i
     */
    public void remove(int i) {
        BinaryNode current = rootNode;
        while (current != null && current.data != i) {
            if (i < current.data) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        //没找到，返回
        if (current == null) {
            return;
        }
        //1、直接移除
        if (current.left == null && current.right == null) {
            /*1、叶子节点*/
            adjustNode(current, null);
        } else if (current.left == null && current.right != null) {
             /*2、右边子树不为空的情况*/
            adjustNode(current, current.right);
        } else if (current.left != null && current.right == null) {
            /*3、左边子树不为空的情况*/
            adjustNode(current, current.left);

        } else if (current.left != null && current.right != null) {
            /*两边子树都不为空的时候,查找右子树中最小的元素用来替换删除的元素*/

            //找到右侧的最小节点,最小节点没有左子树
            BinaryNode rightMinNode = minNode(current.right);

            //首先在右侧的子树中移除最小节点
            adjustNode(rightMinNode, rightMinNode.right);

            //把要移除的节点的子树复制到最小节点
            rightMinNode.left = current.left;
            rightMinNode.right = current.right;
            if (current.left != null) {
                current.left.parent = rightMinNode;
            }
            if (current.right != null) {
                current.right.parent = rightMinNode;
            }
            //移除节点，使用最小节点替换
            adjustNode(current, rightMinNode);
        }

    }

    /**
     * 替换节点
     *
     * @param node
     * @param newNode
     */
    public void adjustNode(BinaryNode node, BinaryNode newNode) {
        //替换的是根节点直接返回
        if (rootNode == node) {
            rootNode = newNode;
            return;
        }
        //其他情况
        BinaryNode parent = node.parent;
        if (parent.left == node) {
            parent.left = newNode;
        }
        if (parent.right == node) {
            parent.right = newNode;
        }
        if (newNode != null) {
            newNode.parent = parent;
        }
    }

    /**
     * 查找最小节点
     *
     * @param current
     * @return
     */
    public BinaryNode minNode(BinaryNode current) {
        if (current == null) {
            throw new IllegalArgumentException("节点为空");
        }
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    /**
     * 输出节点
     *
     * @param binaryNode
     */
    public void print(BinaryNode binaryNode) {
        System.out.println(JSON.toJSONString(binaryNode));
    }

    public BinaryNode getRootNode() {
        return rootNode;
    }


    public static void main(String[] args) {
        BinarySearchTree searchTree = new BinarySearchTree();
        searchTree.insert(3);
        searchTree.insert(5);
        searchTree.insert(2);
        searchTree.insert(6);
        searchTree.insert(1);
        searchTree.insert(4);
        searchTree.insert(9);
        searchTree.insert(7);
        searchTree.print(searchTree.getRootNode());
        searchTree.remove(1);
        searchTree.print(searchTree.getRootNode());

        BinaryNode minNode = searchTree.minNode(searchTree.getRootNode());
        System.out.println(minNode.data);
    }

}

