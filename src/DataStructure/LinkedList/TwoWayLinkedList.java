package DataStructure.LinkedList;

/**
 * 双向循环链表
 * @author yzz
 *
 */
public class TwoWayLinkedList {
	
	private DoubleNode head;                       // 链表头
	private DoubleNode tail;                       // 链表尾
	private int size;                              // 链表的节点个数
	
	public TwoWayLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
	 * 双向循环链表
	 */
	public void headToTail() {
		head.prior = tail;
		tail.next = head;
	}
	
	/**
	 * 添加头节点
	 * @param data
	 */
	public void addHead(Object data) {
		DoubleNode newNode = new DoubleNode(data);
		
		if (size == 0) {
			head = newNode;
			tail = newNode;
			headToTail();
		} else {
			head.prior = newNode;
			newNode.next = head;
			head = newNode;
			headToTail();
		}
		size++;
	}
	
	/**
	 * 在链表尾添加节点
	 * @param data
	 */
	public void addTail(Object data) {
		DoubleNode newNode = new DoubleNode(data);
		if (size == 0) {
			head = newNode;
			tail = newNode;
			headToTail();
		} else {
			newNode.prior = tail;
			tail.next = newNode;
			tail = newNode;
			headToTail();
		}
		size++;
	}
	
	/**
	 *  在指定位置插入元素
	 * @param data
	 * @param index
	 */
	public void insertNodeByIndex(Object data, int index) {
		if (index >= size) {
			return;
		}
		
		DoubleNode newNode = new DoubleNode(data);
		if (index == 0) {
			newNode.next = head;
			head.prior = newNode;
			head = newNode;
			headToTail();
		} else {
			DoubleNode curNode = head;
			int pos = 0;
			while(pos != index) {
				curNode = curNode.next;
				pos++;
			}
			newNode.prior = curNode.prior;
			newNode.prior.next = newNode;
			newNode.next = curNode;
			curNode.prior = newNode;
		}
		size++;
	}
	
	/**
	 * 删除链表头 并返回该节点
	 * @return
	 */
	public DoubleNode deleteHead() {
		
		if (size != 0) {
			DoubleNode tmpNode = head;
			head = head.next;
			headToTail();
			size--;
			return tmpNode;
		} else {
			return null;
		}
		
	}
	
	/**
	 * 删除链表尾
	 * @return
	 */
	public DoubleNode deleteTail() {
		if (size != 0) {
			DoubleNode tmpNode = tail;
			tail = tail.prior;
			headToTail();
			size--;
			return tmpNode;
		} else {
			return null;
		}
	}
	
	/**
	 * 删除指定位置元素, 并返回删除的节点
	 */
	public DoubleNode deleteByIndex(int index) {
		if (size == 0 || index >= size) {
			return null;
		}		

		DoubleNode curNode = head;
		if (index == 0) {
			head = head.next;
			headToTail();			
		} else {
			
			int pos = 0;
			while(pos != index) {
				curNode = curNode.next;
				pos++;
			}
			curNode.next.prior = curNode.prior;
			curNode.prior.next = curNode.next;			
		}
		size--;
		return curNode;
	}
	
	/**
	 * 获得链表的节点个数
	 * @return
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * 判断链表是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return (size == 0);
	}
	
	/**
	 * 输出链表元素
	 */
	public void showAllNode() {
		if (size > 0) {
			DoubleNode node = head;
			int length = size;
			System.out.print("[");
			while(length > 0) {
				System.out.print(node.data + "->");
				node = node.next;
				length--;
			}
			System.out.println("]");
		} else {
			System.out.println("[]");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TwoWayLinkedList twll = new TwoWayLinkedList();
		Object obj1 = new String("A");
		Object obj2 = new String("B");
		Object obj3 = new String("C");
		Object obj4 = new String("D");
		twll.addHead(obj1);
		twll.addHead(obj2);
		//twll.addHead(obj3);
		twll.addTail(obj3);
		twll.addHead(obj4);	
		twll.insertNodeByIndex("E", 0);
		twll.insertNodeByIndex("F", 3);
		
		twll.showAllNode();
		
		DoubleNode new3 = twll.deleteByIndex(0);
		System.out.println(new3.data);
		twll.showAllNode();
		
		System.out.println(twll.tail.data);
		System.out.println(twll.head.data);
//		System.out.println(twll.tail.prior.data);
//		System.out.println(twll.tail.next.data);
		
		DoubleNode new1 = twll.deleteHead();
		System.out.println(new1.data);
		
		DoubleNode new2 = twll.deleteTail();
		System.out.println(new2.data);
		twll.showAllNode();
	}

}
