package DataStructure.NewBinaryTree.Set;

import DataStructure.LinkedList.LinkedList;

/**
 * @author yzze
 * @create 2020-05-01 15:13
 */
public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> list;

    public LinkedListSet() {
        list = new LinkedList<E>();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * O(n)
     * @param e
     * @return
     */
    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    /**
     * 不能有重复元素
     * O(n)
     * @param e
     */
    @Override
    public void add(E e) {
        if(!list.contains(e)) {
            list.addFirst(e);
        }
    }

    /**
     * O(n)
     * @param e
     */
    @Override
    public void remove(E e) {
        list.removeElement(e);
    }
}
