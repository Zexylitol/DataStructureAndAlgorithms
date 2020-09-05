package Algorithms.Sort;

import java.util.Arrays;

/**
 * 基数排序(桶排序的扩展)：将整数按位数切割成不同的数字，然后按每个位数分别比较
 * 1. 对传统桶排序的扩展，速度很快
 * 2. 经典的空间换时间的方式
 * 3. 稳定
 * 4. 有负数的序列，不用基数排序来进行排序
 *
 * @author yzz
 * @create 2020-09-05 17:26
 */
public class RadixSort {

    public static void radixSort(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }
        int maxLength = (max + "").length();     // 最大数的位数

        // 1. 二维数组包含10个一维数组，每个数组就是一个桶
        // 2. 为防止数据溢出，每个桶的大小定为arr.length
        // 3. 空间换时间
        int[][] bucket = new int[10][arr.length];

        // 记录各个桶每次放入的数据个数
        int[] bucketElementCounts = new int[10];

        for (int i = 0, n = 1; i < maxLength; i++, n*=10) {
            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j] / n % 10; // 每个元素对应位的值
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j]; // 放入到对应的桶中
                bucketElementCounts[digitOfElement]++;
            }
            int index = 0;
            // 遍历各个桶，并将桶中数据放入原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                if (bucketElementCounts[k] != 0) {
                    for (int j = 0; j < bucketElementCounts[k]; j++) {
                        // 取出元素放入到arr
                        arr[index++] = bucket[k][j];
                    }
                }
                bucketElementCounts[k] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {53,3,542,748,14,214};
        System.out.println("排序前： " + Arrays.toString(arr));
        radixSort(arr);
        System.out.println("排序后： " + Arrays.toString(arr));
    }
}
