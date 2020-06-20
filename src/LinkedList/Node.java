package LinkedList;


public class Node<E> {

	private E data;
	private Node<E> next;
	
	public Node(E data) {
		this.data = data;
		this.next = null;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public Node<E> getNext() {
		return next;
	}

	public void setNext(Node<E> next) {
		this.next = next;
	}

	/**
	 * 链表节点的构造函数
	 * 使用arr为参数，创建一个链表，当前的Node为链表头节点
	 * @param arr
	 */
	public Node(E[] arr) {
		if(arr == null || arr.length == 0) {
			throw new IllegalArgumentException("arr cannot be empty");
		}
		this.data = arr[0];
		Node cur = this;
		for(int i = 0; i < arr.length; i++) {
			cur.next = new Node(arr[i]);
			cur = cur.next;
		}
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		Node cur = this;
		while(cur != null) {
			res.append(cur.data + "->");
			cur = cur.next;
		}
		res.append("null");
		return res.toString();
	}
}
