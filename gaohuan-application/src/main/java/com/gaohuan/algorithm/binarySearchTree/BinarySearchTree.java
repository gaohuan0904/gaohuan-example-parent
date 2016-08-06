package com.gaohuan.algorithm.binarySearchTree;


import com.alibaba.fastjson.JSON;
import org.apache.tomcat.jni.BIOCallback;

/**
 * 二叉搜索(查询)树
 * <p>User: acer
 * <p>Date: 2016/8/5
 * <p>Version: 1.0
 */
public class BinarySearchTree<T extends Comparable> {

    public static class BinaryNode<T extends Comparable> {
        T data;
        BinaryNode<T> left;
        BinaryNode<T> right;

        public BinaryNode(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public BinaryNode<T> getLeft() {
            return left;
        }

        public void setLeft(BinaryNode<T> left) {
            this.left = left;
        }

        public BinaryNode<T> getRight() {
            return right;
        }

        public void setRight(BinaryNode<T> right) {
            this.right = right;
        }
    }

    /**
     * 根节点
     */
    private BinaryNode<T> rootNode;

    /**
     * 查找
     * 树深（N) O（lgN)
     * 1、从root节点开始
     * 2、比当前节点小，则找其左节点
     * 3、比当前节点大，则找其有节点
     * 4、未找到返回null
     *
     * @param t
     * @return
     */
    public BinaryNode search(T t) {
        BinaryNode current = rootNode;
        while ((current != null) && (t.compareTo(current.data) != 0)) {
            //在左侧
            if (t.compareTo(current.data) < 0) {
                current = current.left;
            } else {//在右侧
                current = current.right;
            }
        }
        return current;
    }

    /**
     * 删除：分三种情况
     * 1、叶子节点，没有左右子树，直接删除
     * 2、只包含左子树或者右子树，直接删除，然后把其左或右子树设置为父节点的左或右子树
     * 3、如果要删除的元素的左右树都不为空的时候，查找待删除节点的右子树中最小的元素
     * 并用来替换要删除的元素
     *
     * @param t
     */
    public void remove(T t) {
        BinaryNode current = rootNode;
        if (current == null) {
            return;
        }
        BinaryNode parent = null;
        boolean isLeft = false;
        boolean isRight = false;
        while ((current != null)) {
            parent = current;
            //在左侧
            if (t.compareTo(current.data) < 0) {
                isLeft = true;
                current = current.left;
            } else {//在右侧
                isRight = true;
                current = current.right;
            }
        }

        //1、叶子节点
        if (current.left == null && current.right == null) {
        } else if (current.left == null && current.right != null) {

        } else if (current.right == null && current.left != null) {

        } else if (current.left != null && current.right != null) {

        }

    }


    /**
     * 清除元素
     */

    public void clear() {
        rootNode = null;
    }

    /**
     * 插入
     * 1、从root节点开始
     * 2、如果root为空，则插入到root
     * 3、插入值小于当前节点，找左节点
     * 4、插入值大于当前节点，找有节点
     *
     * @param t
     * @return
     */
    public BinaryNode<T> insert(T t) {
        //构造一个节点
        BinaryNode newNode = new BinaryNode(t);
        BinaryNode current = rootNode;
        //为初始化，则设置成根节点
        if (current == null) {
            rootNode = newNode;
            return newNode;
        }
        while (true) {
            if (t.compareTo(current.data) < 0) {
                if (current.left == null) {
                    current.left = newNode;
                    return newNode;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = newNode;
                    return newNode;
                }
                current = current.right;
            }
        }
    }

    /**
     * 打印
     *
     * @param node
     */
    public void printTree(BinaryNode node) {
        System.out.println(JSON.toJSONString(node));
    }


    public BinaryNode<T> getRootNode() {
        return rootNode;
    }

    public void setRootNode(BinaryNode<T> rootNode) {
        this.rootNode = rootNode;
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(3);
        tree.insert(8);
        tree.insert(1);
        tree.insert(4);
        tree.insert(6);
        tree.insert(2);
        tree.insert(10);
        tree.insert(9);
        tree.insert(20);
        tree.insert(25);
        tree.printTree(tree.getRootNode());
        BinaryNode node = tree.search(8);
        tree.printTree(node);

    }
}
