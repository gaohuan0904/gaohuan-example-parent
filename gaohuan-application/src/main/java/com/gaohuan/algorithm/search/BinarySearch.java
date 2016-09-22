package com.gaohuan.algorithm.search;

/**
 * 二分查找算法
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] array = {3, 5, 7, 12, 16, 19, 20, 45};
        //int index = search(array, 19, 0, array.length - 1);
        int index = searchNotRecursion(array, 20);
        System.out.println(index);

    }

    /**
     * 递归实现
     *
     * @param array
     * @param num
     * @param low
     * @param high
     * @return
     */
    public static int search(int[] array, int num, int low, int high) {
        if (low < high) {
            int mid = (high + low) / 2;
            if (array[mid] == num) {
                return mid;
            } else if (array[mid] > num) {
                return search(array, num, low, mid - 1);
            } else {
                return search(array, num, mid + 1, high);
            }
        }
        return -1;
    }

    /**
     * @param array
     * @param num
     * @return
     */
    public static int searchNotRecursion(int[] array, int num) {
        int low = 0;
        int high = array.length - 1;
        while (low < high) {
            int mid = (high + low) / 2;
            if (array[mid] == num) {
                return mid;
            } else if (array[mid] > num) {
                high = mid - 1;
            } else if (array[mid] < num) {
                low = mid + 1;
            }
        }

        return -1;
    }
}
