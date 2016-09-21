package com.gaohuan.algorithm.sort;


import java.util.Arrays;

/**
 * 归并排序(合并排序)
 * 1、分隔到足够小并排序
 * 2、合并有序的子序列
 * 3、采用分治法
 * 4、时间复杂度(O(n*logn)
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] array = {3, 1, 5, 7, 13, 2};
        sort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 合并排序入口（分隔左边和右边)
     *
     * @param array
     * @param left
     * @param right
     */
    public static void sort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            //排序左边
            sort(array, left, mid);
            //排序右边
            sort(array, mid + 1, right);
            //左侧序列最大值小于等于右侧序列最小值，不需要排序 (优化1)
            if (array[mid] <= array[mid + 1]) {
                return;
            }
            //合并左边和右边
            merge(array, left, mid, right);
        }

    }

    /**
     * 合并操作
     *
     * @param array
     * @param left
     * @param mid
     * @param right
     */
    public static void merge(int[] array, int left, int mid, int right) {
        int nLength = right - left + 1;
        //用来存放左边和右边元素的数组
        int[] tmp = new int[nLength];//(优化2)
        //左元素标记位
        int lMark = left;
        //右元素标记位
        int rMark = mid + 1;
        // 临时数组的标记位置
        int i = 0;
        while (lMark <= mid && rMark <= right) {
            //把左右元素比较中较小的那个依次放入临时数组
            if (array[lMark] < array[rMark]) {
                tmp[i++] = array[lMark++];
            } else {
                tmp[i++] = array[rMark++];
            }
        }
        //如果上个循环中左右元素不对称，把剩下的元素拷贝过去
        while (lMark <= mid) {
            tmp[i++] = array[lMark++];
        }
        while (rMark <= right) {
            tmp[i++] = array[rMark++];
        }
        //重置临时标记位置
        i = 0;
        //把排序号的临时数组复制到原数组相应位置(从left到right的位置)
        while (i < nLength) {
            array[left++] = tmp[i++];
        }

    }

}
