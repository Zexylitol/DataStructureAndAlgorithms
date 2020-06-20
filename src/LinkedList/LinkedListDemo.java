package LinkedList;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Java LinkedList类   
 * http://www.51gjie.com/java/656.html
 * LinkedList类是双向列表,列表中的每个节点都包含了对前一个和后一个元素的引用，基于链表的
 * @author yzz
 *
 */

public class LinkedListDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> a = new LinkedList<String>();		
		a.add("Amy");         //将指定元素添加到此列表的结尾
	    a.add("Carl");
	    a.add("Erica");
	    
	    List < String > b = new LinkedList < >();
	    b.add("Bob");
	    b.add("Doug");
	    b.add("Frances");
	    b.add("Gloria");
	    
	    ListIterator < String > aIter = a.listIterator();
	    Iterator < String > bIter = b.iterator();
	    
	    while (bIter.hasNext()) {
	        if (aIter.hasNext()) aIter.next();
	        aIter.add(bIter.next());
	    }
	    System.out.println(a);
	    
	    bIter = b.iterator();
	    while (bIter.hasNext()) {
	        bIter.next();
	        if (bIter.hasNext()) {
	            bIter.next();
	            bIter.remove();
	        }
	    }
	    System.out.println(b);
	    
	    a.removeAll(b);

	    System.out.println(a);
	}

}
