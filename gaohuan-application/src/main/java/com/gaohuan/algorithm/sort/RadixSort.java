package com.gaohuan.algorithm.sort;

import java.util.Arrays;

/**
 * 基数排序是通过“分配”和“收集”过程来实现排序
 * 首先根据个位数的数值，在遍历数据时将它们各自分配到编号0至9的桶
 * 接下来将所有桶中所盛数据按照桶号由小到大（桶中由顶至底）依次重新收集串起来
 * 再进行一次分配，这次根据十位数值来分配（原理同上）
 * 如果排序的数据序列有三位数以上的数据，则重复进行以上的动作直至最高位数为止
 */
public class RadixSort {
    private static int RADIX_TEN = 10;//代表0-9共10个数

    public static void main(String[] args) {
        int[] array = {3, 1, 522, 70, 13, 2, 188, 23};
        radixSort(array);
        System.out.println(Arrays.toString(array));

    }

    /**
     * 基数排序
     *
     * @param array
     */
    public static void radixSort(int[] array) {
        int length = array.length;
        int maxDigit = maxDigit(array);
        //0，1,2(个位，十位，百位)
        for (int d = 0; d < maxDigit; d++) {
            int x;
            int[] count = new int[RADIX_TEN];
            //统计相应位置的数出现的次数
            for (int i = 0; i < length; i++) {
                x = numOfDigit(array[i], d);
                count[x]++;
            }
            //重新定义元素位置！！！！！！！！！！！
            int l = length;
            for (int i = RADIX_TEN - 1; i >= 0; i--) {
                if (count[i] > 0) {
                    l = l - count[i];
                    count[i] = l;
                }
            }
            int[] buckets = new int[length];
            //把元素设置到桶的相应位置，数组对应多个桶，每个桶按照0-9顺序依次排列
            for (int i = 0; i < length; i++) {
                x = numOfDigit(array[i], d);
                buckets[count[x]++] = array[i];
            }
            //使用新的位置替换原数组
            for (int i = 0; i < length; i++) {
                array[i] = buckets[i];
            }
        }
    }

    /**
     * 最大数位数
     *
     * @param array
     * @return
     */
    public static int maxDigit(int[] array) {
        int max = maxNum(array);
        int digit = 1;
        while (max > 10) {
            digit++;
            max = max / 10;
        }

        return digit;
    }

    /**
     * 最大数
     *
     * @param array
     * @return
     */
    public static int maxNum(int[] array) {
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        return max;
    }

    /**
     * 当前位的具体数，如十位，百位。
     *
     * @param value
     * @param d
     * @return
     */
    public static int numOfDigit(int value, int d) {
        return (int) (value / Math.pow(10, d) % 10);
    }

}
