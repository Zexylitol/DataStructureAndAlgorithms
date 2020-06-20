package DataStructure.Stack;

import DataStructure.Array.Array;

/**
 * 使用动态数组实现栈
 * @author yzz
 *
 */
public class ArrayStack<E> implements iStack<E> {
	Array<E> array;
	
	public ArrayStack(int capacity){
		array = new Array<E>(capacity);
	}
	
	public ArrayStack() {
		array = new Array<E>();
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
	public void push(E e) {
		array.addLast(e);
	}

	/**
	 * O(1) 均摊
	 * @return
	 */
	@Override
	public E pop() {
		return array.removeLast();
	}

	/**
	 * O(1)
	 * @return
	 */
	@Override
	public E peak() {
		return array.getLast();
	}

	@Override
	public String toString() {
		StringBuffer res = new StringBuffer();
		res.append("stack: [");
		for (int i = 0; i < array.getSize(); i++) {
			res.append(array.get(i));
			if ( i != array.getSize() - 1) {
				res.append(",");
			}
		}
		res.append("]top");
		return res.toString();
	}
	
	
}
