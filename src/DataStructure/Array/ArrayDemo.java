package DataStructure.Array;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayDemo {

    /**
     * 数组作为函数的参数
     * @param array
     */
    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    /**
     * 数组作为函数的返回值
     * @param list
     * @return
     */
    public static int[] reverse(int[] list) {
        int[] result = new int[list.length];

        for (int i = 0, j = result.length - 1; i < list.length; i++, j--) {
            result[j] = list[i];
        }
        return result;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // 数组大小
        int size = 10;
        // 定义数组
        double[] myList = new double[size];
        myList[0] = 5.6;
        myList[1] = 4.5;
        myList[2] = 3.3;
        myList[3] = 13.2;
        myList[4] = 4.0;
        myList[5] = 34.33;
        myList[6] = 34.0;
        myList[7] = 45.45;
        myList[8] = 99.993;
        myList[9] = 11123;
        // 计算所有元素的总和
        double total = 0;
        for (int i = 0; i < size; i++) {
            total += myList[i];
        }
        System.out.println("总和为： " + total);
        // 查找最大元素
        double max = myList[0];
        for (int i = 1; i < myList.length; i++) {
            if (myList[i] > max) max = myList[i];
        }
        System.out.println("Max is " + max);

        printArray(new int[]{3, 1, 2, 6, 4, 2});

        // Arrays
        int[] list = {2,1,5,3,8};
        // 排序
        Arrays.sort(list);
        printArray(list);
        // 二分查找
        System.out.println("The index of 3 in list : " + Arrays.binarySearch(list, 3));
        System.out.print("在整个数组中寻找：");
        System.out.println(Arrays.binarySearch(list,5));
        System.out.print("在局部范围内寻找：");
        System.out.println(Arrays.binarySearch(list,2,4,3));

        ArrayList<Integer> intList = new ArrayList<>();
        intList.add(100);


    }

}
