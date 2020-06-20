package Stack;

/**
 * 栈的接口
 * @author yzz
 *
 * @param <E>
 */
public interface iStack<E> {
	int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peak();
}
