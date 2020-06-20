package NewBinaryTree.Set;

import NewBinaryTree.BST;

/**
 * @author yzze
 * @create 2020-04-25 18:41
 */
public class BSTSet<E extends Comparable<E>> implements Set<E> {
    private BST<E> bst;

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty()
    {
        return bst.isEmpty();
    }

    // 不能盛放重复元素
    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }
}
