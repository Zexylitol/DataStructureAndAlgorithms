package Algorithms.Sort;

import DataStructure.Array.Array;

public class QuickSort {
	
	/**
	 * 
	 * @param array            待排序序列
	 * @param leftIndex        待排序序列左边的index
	 * @param rightIndex       待排序序列右边的index
	 */
	public void quickSort(Array<Integer> array, int leftIndex, int rightIndex) {
		int i = leftIndex, j = rightIndex;
		// 取中间的值作为一个支点
		int pivot = array.get((i+j) / 2);
		
		while (i <= j) {
			// 向右移动，直到找到大于支点的元素
			while (array.get(i) < pivot) {
				i++;
			}
			
			// 向左移动，直到找到小于支点的元素
			while (array.get(j) > pivot) {
				j--;
			}
			
			// 交换两个元素，左边小于支点，右边大于支点
			if (i <= j) {
				if (i != j) {
					int temp = array.get(i);
					array.set(i, array.get(j));
					array.set(j, temp);
				}
				i++;
				j--;
			}
		}
		
		// 递归左边 进行快速排序
		if (leftIndex < j) {
			quickSort(array, leftIndex, j);
		}
		
		// 递归右边 进行快速排序
		if (i < rightIndex) {
			quickSort(array, i, rightIndex);
		}		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		QuickSort qs = new QuickSort();
		
		Array<Integer> arr = new Array<Integer>(8);
		arr.addFirst(7);
		arr.addFirst(5);
		arr.addFirst(3);
		arr.addFirst(2);
		arr.addFirst(4);
		arr.addFirst(6);
		arr.addFirst(1);
		arr.addFirst(8);
		
		System.out.println("排序前： " + arr.toString());
		qs.quickSort(arr, 0, arr.getSize() - 1);
		System.out.println("排序后： " + arr.toString());
	}

}
