package com.gaohuan.algorithm.quicksort;

import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;

/**
 * 快速排序：
 * 1、选择一个基数，通过一趟排序，将数据分为两部分，使得排序列表的左侧元素都小于它，右侧元素都大于它
 * 2、对左侧和右侧分别按照 1 的步骤处理。
 * 3、重复1、2的步骤知道全部数据有序
 * <p>
 * <p>User: acer
 * <p>Date: 2016/8/1
 * <p>Version: 1.0
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] n = new int[]{2, 1, 3, 6, 4};
        swap(n);
        quickSort(n, 0, n.length - 1);
        for (int i : n) {
            System.out.println(i);
        }


    }

    public static void quickSort(int[] a, int left, int right) {
        if (left < right) {
            int mid = partition(a, left, right);
            quickSort(a, left, mid - 1);//递归排序左侧
            quickSort(a, mid + 1, right);//递归排序左侧
        }
    }

    /**
     * 取一个基数，使得左侧的所有元素小于它，右侧的所有元素大于它,并返回这个基数位置
     *
     * @param a
     * @param left
     * @param right
     * @return
     */
    public static int partition(int a[], int left, int right) {
        int pivot = a[left];//取左侧第一个为基点
        while (left < right) {
            //比较右侧元素，如果大于基数，左移标记
            while (left < right && a[right] >= pivot) {
                right--;
            }
            //找到第一个右侧不大于基点的元素，跟基点交换，然后基点左移一位
            if (left < right) {
                a[left++] = a[right];
            }
            //比较左侧元素，如果小于基数，右移标记
            while (left < right && a[left] <= pivot) {
                left++;
            }
            //左侧比较时发现小于基数，跟右侧标记交换
            if (left < right) {
                a[right--] = a[left];
            }
        }
        //left==right时，把基数放回去
        a[left] = pivot;
        return left;
    }

    public static void swap(int[] n) {
        int a = n[0];
        int b = n[n.length / 2];
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        n[0] = a;
        n[n.length / 2] = b;

    }

    public void test() {
    }


}
