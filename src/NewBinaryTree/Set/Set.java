package NewBinaryTree.Set;

/**
 * @author yzze
 * @create 2020-04-25 18:39
 */
public interface Set<E> {
    void add(E e);
    void remove(E e);
    boolean contains(E e);
    int getSize();
    boolean isEmpty();
}
