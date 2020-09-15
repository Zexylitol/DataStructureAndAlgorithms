package DataStructure.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 弃用java中的Stack类，使用Deque接口实现stack!
 * https://www.zhihu.com/question/25372706/answer/1252100096
 * @author yzz
 * @create 2020-09-15 20:18
 */
public class DequeStack<E> implements iStack<E>  {

    private final Deque<E> deque = new ArrayDeque<E>();
    @Override
    public int getSize() {
        return deque.size();
    }

    @Override
    public boolean isEmpty() {
        return deque.isEmpty();
    }

    @Override
    public void push(E e) {
        deque.addFirst(e);
    }

    @Override
    public E pop() {
        return deque.removeFirst();
    }

    @Override
    public E peak() {
        return deque.getLast();
    }
}
