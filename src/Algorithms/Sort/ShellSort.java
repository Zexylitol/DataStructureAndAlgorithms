package Algorithms.Sort;

import java.util.Arrays;

/**
 * 希尔排序
 *
 *  平均时间  最差情形  稳定度  额外空间  备注
 *  O(nlogn) O(n^2)  不稳定  O(1)    s是所选分组
 *          (1<s<2)
 *
 * @author yzz
 * @create 2020-09-04 10:57
 */
public class ShellSort {
    public static void shellSort(int[] arr) {
        // 增量每次都/2
        for (int step = arr.length / 2; step > 0; step /= 2) {
            // 从增量那组开始进行插入排序，直至完毕
            for (int i = step; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                // j - step代表与它同组隔壁的元素
                while( j - step >= 0 && arr[j-step] > temp) {
                    arr[j] = arr[j - step];
                    j = j - step;
                }
                arr[j] = temp;
            }
        }
    }
    public static void main(String[] args) {
        int[] arr = {8,9,1,7,2,3,5,4,6,0};
        System.out.println("排序前： " + Arrays.toString(arr));
        shellSort(arr);
        System.out.println("排序后： " + Arrays.toString(arr));
    }
}
