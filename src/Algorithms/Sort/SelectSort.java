package Algorithms.Sort;

/**
 * 选择排序
 *
 *  平均时间  最差情形  稳定度  额外空间  备注
 *  O(n^2)   O(n^2)  不稳定  O(1)    n小时较好
 *
 * @author yzz
 *
 */
public class SelectSort {
	
	public void selectSort(int[] arr) {
		for (int i = 0; i < arr.length - 1;i++ ) {
			int minIndex = i;
			int min = arr[i];
			for (int j = i + 1; j < arr.length; j++) {
				if (min > arr[j]) {
					min = arr[j];
					minIndex = j;
				}
			}
			if (minIndex != i) {
				arr[minIndex] = arr[i];
				arr[i] = min;
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SelectSort ss = new SelectSort();

		int[] arr = new int[]{101,34,119,1,-1,90,123};

		
		System.out.println("排序前： ");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		ss.selectSort(arr);
		System.out.println("排序后： ");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

}
