package Vector;

import java.util.Enumeration;

import java.util.Vector;

/**
 * Java Vector 类 | 菜鸟教程
 * https://www.runoob.com/java/java-vector-class.html
 * @author yzz
 *
 */

public class VectorDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// initial size is 3, increment is 2
		Vector<Integer> v = new Vector<Integer>(3, 2);
		System.out.println("Initial size: " + v.size());            // Returns the number of components in this vector.
	    System.out.println("Initial capacity: " + v.capacity());
		
	    v.addElement(1);         //  将指定的组件添加到此向量的末尾，将其大小增加 1
	    v.addElement(2);
	    v.addElement(3);
	    v.addElement(4);

	    System.out.println("Capacity after five additions: " + v.capacity());
	    
	    System.out.println("First element: " + v.firstElement());
	    System.out.println("Last element: " +  v.lastElement());
	    
	    if(v.contains(3))
	         System.out.println("Vector contains 3.");
	 // enumerate the elements in the vector.
		Enumeration<Integer> vEnum = v.elements();
		System.out.println("\nElements in vector:");
		while(vEnum.hasMoreElements())
			System.out.print(vEnum.nextElement() + " ");
		System.out.println();
	}

}
