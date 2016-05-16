package com.gaohuan.sort;

/**
 * 归并排序
 * 1、分隔到足够小并排序
 * 2、合并有序的子序列
 * 3、采用分治法
 * 4、时间复杂度(O(n*logn)
 */
public class MergeSort1 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        final int num = 1000000;
        for (int i = 0; i < num; i++) {
            int[] array = {3, 1, 5, 7, 9, 10, 2, 8, 2, 12, 23, 12, 34, 1234, 111, 234, 12, 53, 52, 4312, 12, 231, 22, 34, 543, 45, 23, 1234, 542, 213, 462, 2345};
            mergeSort(array, 0, array.length - 1);
        }
        System.out.println("time:" + (System.currentTimeMillis() - start));
    }

    public static void mergeSort(int[] array, int left, int right) {

        if (left < right) {
            int mid = (left + right) / 2;
            //分隔左序列
            mergeSort(array, left, mid);
            //分隔右序列
            mergeSort(array, mid + 1, right);
            //合并
            merge(array, left, mid, right);
        }
    }

    /**
     * 合并
     * @param array
     * @param left
     * @param mid
     * @param right
     */
    private static void merge(int[] array, int left, int mid, int right) {
        int[] tmp = new int[array.length];
        int n = left;
        int m = mid + 1;
        int i = left;
        //比较
        while (n <= mid && m <= right) {
            if (array[n] < array[m]) {
                tmp[i++] = array[n++];
            } else {
                tmp[i++] = array[m++];
            }
        }
        //拷贝剩下的元素
        while (n <= mid) {
            tmp[i++] = array[n++];

        }
        //拷贝剩下的元素
        while (m <= right) {
            tmp[i++] = array[m++];
        }
        //复制到原数组
        while (left <= right) {
            array[left] = tmp[left++];
        }

    }
}
