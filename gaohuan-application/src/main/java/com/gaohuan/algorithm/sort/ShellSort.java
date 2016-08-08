package com.gaohuan.algorithm.sort;

/**
 * 希尔排序：插入排序的变种，取一个步长作为间隔分成数个组，每个组进行插入排序，减小步长直到1。不稳定
 * Created by huan on 2016/8/3.
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] a = new int[]{49, 38, 65, 97, 26, 13, 27, 49, 55, 4, 12};
        shellSort(a);
        for (int i : a) {
            System.out.println(i);
        }
    }

    public static void shellSort(int[] a) {
        int n = a.length;
        //设置步长，取中，直到步长为1
        for (int gap = n / 2; gap > 0; gap /= 2) {
            //按照步长分组
            for (int i = 0; i < gap; i++) {
                for (int j = i; j < n; j = j + gap) {
                    insertSort(a, gap);
                }
            }
        }


    }

    //直接插入排序
    public static void insertSort(int a[], int gap) {
        for (int i = 0; i < a.length - gap; i += gap) {
            int k = i;
            int t = a[k + gap];//待插入数据
            //大于要插入的元素
            while (k >= 0 && a[k] > t) {
                a[k + gap] = a[k];//后移
                k = k - gap;//定位到上一个带后移的位置
            }
            a[k + gap] = t;
        }
    }
}


