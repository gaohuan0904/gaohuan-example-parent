package com.gaohuan.algorithm;

/**
 * Created by huan on 2016/8/6.
 */
public class Main {
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

        BinarySearchTree.BinaryNode minNode = searchTree.minNode(searchTree.getRootNode());
        System.out.println(minNode.getData());
    }
}
