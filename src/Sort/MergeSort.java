package Sort;

import Array.Array;

/**
 * 归并排序
 * @author yzz
 *
 */
public class MergeSort {
	
	public Array<Integer> mergeSort(Array<Integer> array) {
		// 递归终止条件
		if (array.getSize() <= 1) {
			return array;
		}
		
		int mid = array.getSize() / 2;
		// 左半部分进行拆分
		Array<Integer> leftArray = new Array<Integer>(mid);
		Array<Integer> rightArray = new Array<Integer>(mid);
		for (int i = 0; i < array.getSize(); i++) {
			if (i < mid) {
				leftArray.add(i, array.get(i));
			} else {
				rightArray.add(i-mid, array.get(i));
			}
		}
		
		leftArray = mergeSort(leftArray);
		rightArray = mergeSort(rightArray);
		
		System.out.print("左： ");
		for(int a = 0; a < mid; a++) {
			System.out.print(leftArray.get(a) + " ");
		}
		System.out.print("右： ");
		for(int a = 0; a < mid; a++) {
			System.out.print(rightArray.get(a) + " ");
		}
		System.out.println();
		
		// 递归结束后
		int leftIndex = 0;
		int rightIndex = 0;
		// 进行合并
		Array<Integer> results = new Array<Integer>(2 * mid);
		int cnt = 0;
		while (leftIndex < mid && rightIndex < mid) {
			if (leftArray.get(leftIndex) < rightArray.get(rightIndex)) {
				results.add(cnt, leftArray.get(leftIndex));
				leftIndex++;
				cnt++;
			} else {
				results.add(cnt, rightArray.get(rightIndex));
				rightIndex++;
				cnt++;
			}
		}
		
		if (leftIndex < mid) {    // 把左边剩余元素加到排序结果中
			for (int t = leftIndex; t < mid; t++) {
				results.add(cnt++, leftArray.get(t));
			}			
		} 
		if (rightIndex < mid) {      // 把右边剩余元素加到排序结果中
			for (int t = rightIndex; t < mid; t++) {
				results.add(cnt++, rightArray.get(t));
			}	
		}
		
		return results;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MergeSort ms = new MergeSort();
		
		Array<Integer> arr = new Array<Integer>(8);
		arr.addFirst(8);
		arr.addFirst(1);
		arr.addFirst(4);
		arr.addFirst(6);
		arr.addFirst(2);
		arr.addFirst(3);
		arr.addFirst(5);
		arr.addFirst(7);
		
		System.out.println("排序前： " + arr.toString());
		arr = ms.mergeSort(arr);
		System.out.println("排序后： " + arr.toString());
	}

}
