package Sort;

import Array.Array;

/**
 * 冒泡排序
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
