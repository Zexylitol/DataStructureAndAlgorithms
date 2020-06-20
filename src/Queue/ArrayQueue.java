package Queue;

import Array.Array;

/**
 * 利用动态数组实现队列
 * 数组队列问题：出队时间复杂度为 O(n) 解决方案：循环队列
 * @author yzze
 * @create 2020-04-18 17:06
 */
public class ArrayQueue<E> implements iQueue<E> {
    private Array<E> array;

    public ArrayQueue(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayQueue() {
        array = new Array<>();
    }

    /**
     * O(1)
     * @return
     */
    @Override
    public int getSize() {
        return array.getSize();
    }

    /**
     * O(1)
     * @return
     */
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    /**
     * O(1) 均摊
     * @param e
     */
    @Override
    public void enQueue(E e) {
        array.addLast(e);
    }

    /**
     * O(n)
     * @return
     */
    @Override
    public E deQueue() {
        return array.removeFirst();
    }

    /**
     * O(1)
     * @return
     */
    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [");
        for(int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize()-1) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enQueue(i);
            System.out.println(queue);
            if(i % 3 == 2) {
                queue.deQueue();
                System.out.println(queue);
            }
        }
    }
}
