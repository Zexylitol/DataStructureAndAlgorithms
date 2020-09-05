package Algorithms.Sort;

import java.util.Arrays;

/**
 * 快速排序
 *
 *  平均时间   最差情形   稳定度   额外空间      备注
 *  O(nlogn) O(n^2)   不稳定    O(nlogn)    n大时较好
 *
 */
public class QuickSort {

	/**
	 *
	 * @param arr          待排序序列
	 * @param leftIndex    待排序序列左边的index
	 * @param rightIndex   待排序序列右边的index
	 */
	public static void quickSort(int[] arr, int leftIndex, int rightIndex) {
		int i = leftIndex;
		int j = rightIndex;
		int pivot = arr[(i + j) / 2];        // 取中间的值作为一个支点

		while (i < j) {
			while(arr[i] < pivot) i++;      // 向右移动，直到找到大于支点的元素
			while(arr[j] > pivot) j--;      // 向左移动，直到找到小于支点的元素

			// 交换两个元素，左边小于支点，右边大于支点
			if (i <= j) {
				if (i != j) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
				i++;
				j--;
			}
			// 递归左边 进行快速排序
			if (leftIndex < j) {
				quickSort(arr, leftIndex, j);
			}

			// 递归右边 进行快速排序
			if (rightIndex > i) {
				quickSort(arr, i, rightIndex);
			}
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] arr = {-9,78,0,23,-567,70,-1,900,4561};
		
		System.out.println("排序前： " + Arrays.toString(arr));
		quickSort(arr, 0, arr.length - 1);
		System.out.println("排序后： " + Arrays.toString(arr));
	}

}
