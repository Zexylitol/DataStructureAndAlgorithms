package Algorithms.Sort;

import DataStructure.Array.Array;


/**
 * 冒泡排序
 *
 *  平均时间  最差情形  稳定度  额外空间  备注
 *  O(n^2)   O(n^2)  稳定   O(1)     n小时较好
 *
 * @author yzz
 *
 * 2019/08/17
 */
public class BubbleSort {

    public void bubbleSort(Array<Integer> array) {
        for (int i = 0; i < array.getSize() - 1; i ++) {
            boolean isChange = false;
            for (int j = 0; j < array.getSize() - 1 - i; j++) {
                // 比较相邻两个元素的大小，前一个大于后一个就交换
                if (array.get(j) > array.get(j+1)) {
                    Integer tmp = array.get(j);
                    array.set(j, array.get(j+1));
                    array.set(j+1, tmp);
                    isChange = true;
                }
            }
            if (!isChange) {
                // 如果某次未发生数据交换，说明数据已排序
                break;
            }
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        BubbleSort bs = new BubbleSort();
        Array<Integer> arr = new Array<Integer>(8);
        arr.addFirst(7);
        arr.addFirst(1);
        arr.addFirst(4);
        arr.addFirst(6);
        arr.addFirst(2);
        arr.addFirst(3);
        arr.addFirst(5);
        arr.addFirst(7);

        System.out.println("排序前： " + arr.toString());
        bs.bubbleSort(arr);
        System.out.println("排序后： " + arr.toString());

    }

}
