package Stack;

import LinkedList.SingleLinkedList;

/**
 * 利用自定义单向链表实现栈
 * @author yzz
 * 
 * 2019/8/17  泛型化
 */
public class LinkedListStack<E> implements iStack<E>{
	
	private SingleLinkedList<E> list;
	
	public LinkedListStack() {
		list = new SingleLinkedList<E>();
	}
	
	/**
	 * push
	 */
	@Override
	public void push(E obj) {
		list.addHeadNode(obj);
	}
	
	/**
	 * pop
	 */
	@Override
	public E pop() {
		if(isEmpty()) {
			System.out.println("The stack is empty!!");
			return null;
		}
		return list.deleteHeadNode().getData();
	}
	
	/**
	 * 访问栈顶数据
	 * @return
	 */
	@Override
	public E peak() {
		return list.getHead().getData();
	}
	
	/**
	 * 判断是否为空
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	/**
	 * 获取栈内元素数量
	 */
	@Override
	public int getSize() {
		return list.getSize();
	}
	
	/**
	 * 打印栈内元素信息
	 */
	public void displayStack() {
		list.displayAllNodes(list.getHead());
	}
	
	public static void main(String[] args) {
		LinkedListStack<Object> sbll = new LinkedListStack<Object>();
		
		Object obj = sbll.pop();
		System.out.println(obj);
		
		sbll.push("A");
		sbll.push("B");
		Object obj2 = sbll.pop();
		System.out.println(obj2);
		sbll.push("C");
		sbll.push("D");
		
		sbll.displayStack();
		System.out.println("The size of stack : " + sbll.getSize());
		
		Object obj1 = sbll.pop();
		System.out.println(obj1);
		
		sbll.displayStack();
		System.out.println("The size of stack : " + sbll.getSize());
		
		Object obj3 = sbll.pop();
		System.out.println(obj3);
		
		Object obj4 = sbll.pop();
		System.out.println(obj4);
		
		Object obj5 = sbll.pop();
		System.out.println(obj5);
		
		System.out.println("The size of stack : " + sbll.getSize());
		
	}
	
}
