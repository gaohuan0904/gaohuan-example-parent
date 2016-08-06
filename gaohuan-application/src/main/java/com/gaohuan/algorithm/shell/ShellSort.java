package com.gaohuan.algorithm.shell;

/**
 * 希尔排序：是一种插入排序，是直接插入排序的增强版
 * 基本思想：记录按照步长（gap)分组，对每组记录按照直接插入排序方法进行排序。
 * 随着步长减小，知道步长为1，整个数据合成为1组，构成有序记录。
 * <p>
 * <p>User: acer
 * <p>Date: 2016/8/3
 * <p>Version: 1.0
 */
public class ShellSort {

    public static void main(String[] args) {
        int a[] = {49, 38, 65, 97, 26, 13, 27, 49, 55, 4};
        shellSort(a, a.length);
        for (int i : a) {
            System.out.println(i);
        }

    }

    public static void shellSort(int a[], int n) {

        //步长每次减半，直到为0
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = 0; i < gap; i++) {
                for (int j = i + gap; j < n; j += gap) {
                    //上一个元素坐标
                    int k = j - gap;
                    //如果后一个元素小于前一个元素
                    if (a[j] < a[k]) {
                        int temp = a[j];
                        //如果上一个元素坐标大于0并且上一个元素大于当前元素
                        while (k >= 0 && a[k] > temp) {
                            //把上一个元素的赋值给后一个元素,也就是后移数据
                            a[k + gap] = a[k];
                            k = k - gap;
                        }
                        a[k + gap] = temp;//把要插入的数据放到正确的位置
                    }
                }
            }
        }
    }
}



