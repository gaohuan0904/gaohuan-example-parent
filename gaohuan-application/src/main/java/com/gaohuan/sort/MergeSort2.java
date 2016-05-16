package com.gaohuan.sort;

import java.util.Arrays;

/**
 * 归并排序
 * 1、分隔到足够小并排序
 * 2、合并有序的子序列
 * 3、采用分治法
 * 4、时间复杂度(O(n*logn)
 */
public class MergeSort2 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        final int num = 1000000;
        for (int i = 0; i < num; i++) {
            int[] array = {3, 1, 5, 7, 9, 10, 2, 8, 2, 12, 23, 12, 34, 1234, 111, 234, 12, 53, 52, 4312, 12, 231, 22, 34, 543, 45, 23, 1234, 542, 213, 462, 2345};
            mergeSort(array);
        }
        System.out.println("time:" + (System.currentTimeMillis() - start));

    }

    public static int[] mergeSort(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        int left = 0;
        int right = array.length;
        int mid = (left + right) / 2;
        int[] lArray = Arrays.copyOfRange(array, left, mid);
        int[] rArray = Arrays.copyOfRange(array, mid, right);

        lArray = mergeSort(lArray);
        rArray = mergeSort(rArray);
        return merge(lArray, rArray);

    }

    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        int l = left.length;
        int r = right.length;
        int n = 0;
        int m = 0;
        while (n < l && m < r) {
            if (left[n] > right[m]) {
                result[i++] = right[m++];
            } else {
                result[i++] = left[n++];
            }
        }

        if (n < l) {
            System.arraycopy(left, n, result, i, l - n);
        }
        if (m < r) {
            System.arraycopy(right, m, result, i, r - m);
        }

        return result;
    }

    public static void print(int[] array) {
        for (int i : array) {
            System.out.println(i);
        }
    }

}
