package Algorithms.Sort;

import DataStructure.Array.Array;

/**
 * 选择排序
 * @author yzz
 *
 */
public class SelectSort {
	
	public void selectSort(Array<Integer> array) {
		for (int i = 0; i < array.getSize() - 1; i++) {
			for (int j = i + 1; j < array.getSize(); j++) {
				if (array.get(i) > array.get(j)) {
					int tmp = array.get(i);
					array.set(i, array.get(j));
					array.set(j, tmp);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SelectSort ss = new SelectSort();
		
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
		ss.selectSort(arr);
		System.out.println("排序后： " + arr.toString());
	}

}
