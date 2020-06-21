package DataStructure.NewBinaryTree.Set;

import DataStructure.NewBinaryTree.AVL.AVLTree;

/**
 * 增删改查：O(logn)
 * @author yzze
 * @create 2020-05-16 12:08
 */
public class  AVLSet<E extends Comparable<E>> implements Set<E> {
    private AVLTree<E, Object> avl;

    public AVLSet() {
        avl = new AVLTree<>();
    }

    @Override
    public int getSize() {
        return avl.getSize();
    }

    @Override
    public boolean isEmpty() {
        return avl.isEmpty();
    }

    @Override
    public boolean contains(E e) {
        return avl.contains(e);
    }

    @Override
    public void add(E e) {
        avl.add(e, null);
    }

    @Override
    public void remove(E e) {
        avl.remove(e);
    }
}
