package DataStructure.Heap;

import DataStructure.Array.Array;

import java.util.Random;

/**
 * @author yzze
 * @create 2020-05-05 21:19
 */
public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<E>(capacity);
    }

    public MaxHeap() {
        data = new Array<E>();
    }

    /**
     * 返回堆中的元素
     * @return
     */
    public int size() {
        return data.getSize();
    }

    /**
     * 堆是否为空
     * @return
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 返回完全二叉树的数组表示中，元素的父亲节点索引
     * @param index
     * @return
     */
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        return (index - 1) / 2;
    }

    /**
     * 返回完全二叉树的数组表示中，元素的左孩子节点索引
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 返回完全二叉树的数组表示中，元素的右孩子节点索引
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * 上浮，用于添加元素
     * @param k
     */
    private void siftUp(int k) {
        while(k>0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }
    /**
     * 向堆中添加元素
     * @param e
     */
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    /**
     * 查看堆中的最大元素
     * @return
     */
    public E findMax() {
        if(data.getSize() == 0) {
            throw new IllegalArgumentException("Cannot findMax when heap is empty.");
        }
        return data.get(0);
    }

    /**
     * 取出堆中的最大元素
     * @return
     */
    public E extractMax() {
        E ret = findMax();

        data.swap(0, data.getSize()-1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    /**
     * 下沉，用于取出最大元素
     * @param k
     */
    private void siftDown(int k) {
        while(leftChild(k) < data.getSize()) {
            int j = leftChild(k);
            // 存在右孩子节点且右孩子节点值大于左孩子节点值
            if (j+1 < data.getSize() &&
                data.get(j+1).compareTo(data.get(j)) > 0) {
                j = rightChild(k);
            }
            // data[j] 是 leftChild 和 rightChild 中的最大值
            if (data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            }
            data.swap(k, j);
            k = j;
        }
    }

    public static void main(String[] args) {
        int n = 1000;
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMax();
        }

        for (int i = 1; i < n; i++) {
            if (arr[i-1] < arr[i]) {
                throw new IllegalArgumentException("Error");
            }
        }
        System.out.println("Test MaxHeap completed.");
    }
}
