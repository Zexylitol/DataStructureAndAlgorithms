package LinkedList;

public class ReversingLinkedList {
	
	/**
	 * 给定一个链表，每 k 个节点一组进行翻转，请返回翻转后的链表
	 * k 是一个正整数，它的值小于或等于链表的长度
	 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序
	 * @param head
	 * @param k
	 * @return
	 */
	public Node<Object> reverseKGroup(Node<Object> head, int k) {
		
		if (head == null || head.getNext() == null || k <= 1) {
			return head;
		}
		
		Node<Object> dummy = new Node<Object>("");
		dummy.setNext(head);
		Node<Object> pointer = dummy;           // pointer 是一个引用变量 理解：pointer = dummy.getNext与 pointer.setNext的区别  前者是pointer指向新的地址 后者修改了dummy指向地址的数据      
		
		while (pointer != null) {
			// 记录上一个子链表的尾 也就是未反转前的头
			/*
			 * 作为已反转的子链表与未反转的子链表之间的桥梁
			 * eg : 1->2->3->4->5->6->7 k = 3
			 * 首次循环：lastGroup.data = "" next = 1  dummy.data = "" next = 1
			 * 第二次循环： 3->2->1->4->5->6->
			 *         lastGroup.data = 1  next = 4  dummy.data = 3  next = 2 1 4 5 6 7
			 * 第三次循环： 3->2->1->6->5->4->7
			 *         lastGroup,data = 4  next = 7  dummy.data = 3  next = 2 1 6 5 4 7
			 */
			Node<Object> lastGroup = pointer;            
			
			int i = 0;
			for(i = 0;i < k; i++) {
				pointer = pointer.getNext();
				if (pointer == null) {
					break;
				}
			}
			
			// 如果当前子链表的节点数满足k，就进行反转
			// 反之，说明程序到尾了，节点数不够，不用反转
			if(i == k) {
				// 记录下一个子链表的头
				Node<Object> nextGroup = pointer.getNext();
				
				// 反转当前子链表，reverse 函数返回反转后子链表的头
				Node<Object> reversedList = reverse(lastGroup.getNext(), nextGroup);
				
				// lastGroup 是上一个子链表的尾，其 next 指向当前反转子链表反转前的头
				// 但是因为当前链表已经被反转，所以它指向的是反转后的链表的尾
				pointer = lastGroup.getNext();       // pointer指向反转后的链表的尾
				
				// 将上一个链表的尾连向反转后链表的头
				lastGroup.setNext(reversedList);           // 相当于修改dummy.setNext();
				
				// 当前反转后的链表的尾连向下一个未反转前的子链表的头
				pointer.setNext(nextGroup);
			}
		}
		
		return dummy.getNext();
	}
	
	/**
	 * 
	 * @param head
	 * @param tail
	 * @return
	 */
	private Node<Object> reverse(Node<Object> head, Node<Object> tail) {
		
		if (head == null || head.getNext() == null) {
			return head;
		}
		
		Node<Object> prev = null, temp = null;
		while((head != null) && (head != tail)) {
			temp = head.getNext();
			head.setNext(prev);
			prev = head;
			head = temp;
		}		
		return prev;
	}
	
	/**
	 * 反转子链表 返回反转后的子链表的头
	 * @param head_
	 * @param tail_
	 * @return
	 */
	public Node<Object> myReverse(Node<Object> head_, Node<Object> tail_) {
				
		Node<Object> pCurNode = head_;
		Node<Object> pPrevNode = null;
		while (pCurNode != tail_) {
			Node<Object> pNextNode = pCurNode.getNext();
			pCurNode.setNext(pPrevNode);                      // 原先的头结点的next会指向空
			pPrevNode = pCurNode;
			pCurNode = pNextNode;
		}		
		return pPrevNode;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReversingLinkedList rll = new ReversingLinkedList();
		
		SingleLinkedList<Object> sll = new SingleLinkedList<Object>();
		sll.addHeadNode("A");
		sll.addNode(0, "B");
		sll.addNode(1, "C");
		sll.addNode(2, "D");
		sll.addNode(3, "E");
		sll.displayAllNodes(sll.getHead());
		Node<Object> testNode = rll.reverseKGroup(sll.getHead(), 2);
		sll.setHead(testNode);
		sll.displayAllNodes(sll.getHead());
				
		SingleLinkedList<Object> sll1 = new SingleLinkedList<Object>();
		sll1.addHeadNode("1");
		sll1.addNode(0, "2");
		sll1.addNode(1, "3");
		sll1.displayAllNodes(sll1.getHead());
		sll1.displayAllNodes(rll.myReverse(sll1.getHead(), sll1.getHead().getNext().getNext().getNext()));

	}

}
