package DataStructure.LinkedList;

/**
 * 
 * java实现单链表 - telang - 博客园
 * https://www.cnblogs.com/_popc/p/4025684.html
 * 链表的原理及java实现 - 刘凌枫羽 - 博客园
 * https://www.cnblogs.com/llfy/p/9395936.html
 *
 * Java实现链表主要依靠引用传递，引用可以理解为地址
 * @author yzz
 *
 */

public class SingleLinkedList<E> {	
	

	private int size;                  //链表节点的个数	
	
	private Node<E> head;                 // 头结点
		
	public Node<E> getHead() {
		return head;
	}

	public void setHead(Node<E> head) {
		this.head = head;
	}

	public int getSize() {
		return size;
	}

	
	public SingleLinkedList() {
		size = 0;
		head = null;
	}
		
	/**
	 * 在表头添加节点
	 * @param data
	 */
	public void addHeadNode(E data) {
		Node<E> newNode = new Node<E>(data);
		newNode.setNext(head);
		head = newNode;
		size++;
	}
	
	/**
	 * 删除头结点 并返回（删除掉的）头结点
	 * @return
	 */
	public Node<E> deleteHeadNode() {
		if(isEmpty()) {
			System.out.println("The single list is empty!!");
			return null;
		}
		Node<E> tempNode = head;
		head = tempNode.getNext();
		size--;
		return tempNode;		
	}
	
	/**
	 * 在任意位置插入节点（不能插入头结点）
	 * 在index的后面插入, index可从0开始
	 */
	public void addNode(int index, E data) {
		
		if(index < 0 || index > size-1) {
			return ;
		}
		
		Node<E> newNode = new Node<E>(data);
		Node<E> currentNode = head;
		Node<E> preNode = head;
		int pos = 0;
		while(pos != index) {                             //  pos != index-1
			preNode = currentNode;
			currentNode = currentNode.getNext();
			pos++;
		}
		newNode.setNext(currentNode.getNext());
		
		currentNode.setNext(newNode);
		
		size++;
	}
	
	/**
	 * 删除任意位置节点(可删除头节点也可删除尾节点)
	 * 删除index位置节点，index可从0开始
	 * 返回删除的节点
	 */
	public Node<E> deleteNode(int index) {
		
		if(index < 0 || index > size-1) {
			return null;
		}
		
		Node<E> curNode = head;
		Node<E> preNode = head;
		int pos = 0;
		while(pos != index) {
			preNode = curNode;
			curNode = curNode.getNext();
			pos++;
		}
		if (curNode == head) {
			head = head.getNext();			
		} else 
		{
			preNode.setNext(curNode.getNext());			
		}
		
		size--;
		
		return curNode;
		
	}
	
	/**
	 * 显示所有节点数据
	 */
	public void displayAllNodes(Node<E> head_) {
		Node<E> curNode = head_;
		while(curNode != null) {			
			System.out.print(curNode.getData() + " -> ");
			curNode = curNode.getNext();
		}
		System.out.println();
	}
	
	/**
	 * 根据位置查找节点信息
	 */
	public Node<E> findNodeByIndex(int index) {
		Node<E> curNode = head;
		int pos = 0;
		while(pos != index) {
			curNode = curNode.getNext();
			pos++;
		}
		return curNode;
	}
	
	/**
	 * 判断链表是否为空
	 */
	public boolean isEmpty() {
		return (size == 0);
	}
	
	/**
	 * 反转链表 并返回反转后的头节点
	 * @param head_
	 * @return
	 */
	public Node<E> ReverseIteratively(Node<E> head_) {
	        Node<E> pReversedHead = head_;
	        Node<E> pNode = head_;
	        Node<E> pPrev = null;
	        while (pNode != null) {
	            Node<E> pNext = pNode.getNext();
	            if (pNext == null) {
	                pReversedHead = pNode;
	            }
	            pNode.setNext(pPrev);	            
	            pPrev = pNode;
	            pNode = pNext;
	        }
	        head = pReversedHead;	       
	        return head;
	 }
	
	
	public static void main(String[] args) {
		SingleLinkedList<Object> sll = new SingleLinkedList<Object>();
		Object obj1 = null, obj2 = null, obj3 = null;
		obj1 = new String("A");
		obj2 = new String("B");
		obj3 = new String("C");
		
		Object obj4 = new String("D");
		
		sll.addHeadNode(obj1);
		
		sll.addNode(0, obj2);
		sll.addNode(1, obj3);
		sll.addNode(2, obj4);
		
		sll.displayAllNodes(sll.getHead());
		
		System.out.println("size = " + sll.size);
		
		Node<Object> newNode = sll.findNodeByIndex(3);
		System.out.println(newNode.getData());
		
		sll.deleteNode(1);
		sll.displayAllNodes(sll.getHead());
		
		System.out.println("size = " + sll.size);
	}
	
}
