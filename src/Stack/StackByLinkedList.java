package Stack;

import LinkedList.LinkedList;

/**
 * @author yzze
 * @create 2020-04-20 17:50
 */
public class StackByLinkedList<E> implements iStack<E> {

    private LinkedList<E> list;

    public StackByLinkedList() {
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

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peak() {
        return list.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: top ");
        res.append(list);
        return res.toString();
    }

    public static void main(String[] args) {
        StackByLinkedList<Integer> stack = new StackByLinkedList<Integer>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);

        }
        System.out.println(stack);

        stack.pop();
        System.out.println(stack);
    }
}
