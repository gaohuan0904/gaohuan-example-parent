package com.gaohuan.algorithm.sort;

import java.util.Arrays;

/**
 * 堆的定义如下：具有n个元素的序列（h1,h2,...,hn),当且仅当满足（hi>=h2i,hi>=2i+1）或（hi<=h2i,hi<=2i+1）(i=1,2,...,n/2)时称之为堆。
 * 在这里只讨论满足前者条件的堆。由堆的定义可以看出，堆顶元素（即第一个元素）必为最大项（大顶堆）。
 * 完全二叉树可以很直观地表示堆的结构。堆顶为根，其它为左子树、右子树。
 * 初始时把要排序的数的序列看作是一棵顺序存储的二叉树，调整它们的存储序，使之成为一个堆，这时堆的根节点的数最大。
 * 然后将根节点与堆的最后一个节点交换。然后对前面(n-1)个数重新调整使之成为堆。依此类推，直到只有两个节点的堆，并对它们作交换，最后得到有n个节点的有序序列。
 * 从算法描述来看，堆排序需要两个过程，一是建立堆，二是堆顶与堆的最后一个元素交换位置。
 * 所以堆排序有两个函数组成。一是建堆的渗透函数，二是反复调用渗透函数实现排序的函数
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] data = {38, 48, 21, 29, 13, 68, 55, 82, 89, 12};
        heapSort(data);
    }

    /**
     * 堆排序
     */
    public static void heapSort(int[] data) {
        int length = data.length;
        for (int i = length - 1; i > 0; i--) {
            //构建堆
            buildHeap(data, i);
            //交换数据
            swap(data, 0, i);

        }
        System.out.println(Arrays.toString(data));

    }

    /**
     * 构建堆
     *
     * @param data
     * @param lastIndex
     */
    public static void buildHeap(int[] data, int lastIndex) {
        //从后向前构建堆
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
            int k = i;
            //主要作用是以当前点为基础重新构建堆
            while (k * 2 + 1 <= lastIndex) {
                int biggerIndex = k * 2 + 1;

                if (biggerIndex < lastIndex && data[biggerIndex] < data[biggerIndex + 1]) {
                    biggerIndex++;
                }
                if (data[biggerIndex] > data[k]) {
                    swap(data, k, biggerIndex);
                    k = biggerIndex;
                } else {
                    break;
                }
            }

            /*
            //方式2 递归
            buildSubHeap(data, k, lastIndex);
            */
        }

    }

    public static void buildSubHeap(int[] data, int k, int lastIndex) {
        int l = 2 * k + 1;
        if (l > lastIndex) {
            return;
        }
        int r = 2 * k + 2;
        int biggerIndex = k;
        if (l <= lastIndex && data[l] > data[biggerIndex]) {
            biggerIndex = l;
        }
        if (r <= lastIndex && data[r] > data[biggerIndex]) {
            biggerIndex = r;
        }
        if (biggerIndex != k) {
            swap(data, biggerIndex, k);
            k = biggerIndex;
            buildSubHeap(data, k, lastIndex);
        }
    }

    /**
     * 交换数据
     *
     * @param data
     * @param i
     * @param j
     */
    public static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;

    }

}
