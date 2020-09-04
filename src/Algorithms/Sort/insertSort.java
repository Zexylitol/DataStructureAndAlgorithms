package Algorithms.Sort;

import java.util.Arrays;

/**
 * 插入排序
 *
 *  平均时间  最差情形  稳定度  额外空间  备注
 *  O(n^2)   O(n^2)  稳定    O(1)    大部分已排序时较好
 *
 * @author yzz
 * @create 2020-09-04 10:15
 */
public class insertSort {

    public void insertSort(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];       // 待插入的数
            insertIndex = i - 1;
            // 给insertVal找到插入的位置
            // 1. insertIndex >= 0 保证在给insertVal找插入位置，不越界
            // 2. insertVal < arr[insertIndex]待插入的数还没有找到插入的位置，需要将arr[insertIndex]后移
            while(insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex]; // 大数后移
                insertIndex--;
            }

            // 退出while循环时，说明找到插入位置：insertIndex+1
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {101,34,119,1,-1,89};
        insertSort is = new insertSort();
        System.out.println("排序前： " + Arrays.toString(arr));
        is.insertSort(arr);
        System.out.println("排序后： " + Arrays.toString(arr));
    }
}
