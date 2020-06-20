package Queue;

import Heap.MaxHeap;

/**
 * 基于最大堆的优先队列
 * @author yzze
 * @create 2020-05-09 13:00
 */
public class PriorityQueue<E extends Comparable<E>> implements iQueue<E> {
    private MaxHeap<E> maxHeap;

    public PriorityQueue() {
        maxHeap = new MaxHeap<>();
    }

    @Override
    public int getSize() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public E getFront() {
        return maxHeap.findMax();
    }

    @Override
    public void enQueue(E e) {
        maxHeap.add(e);
    }

    @Override
    public E deQueue() {
        return maxHeap.extractMax();
    }
}
