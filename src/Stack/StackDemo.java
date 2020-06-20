package Stack;

import java.util.EmptyStackException;

/**
 * Java Stack 类  只定义了默认构造函数，用来创建一个空栈
 * 栈是Vector的一个子类，它实现了一个标准的后进先出的栈
 * Java Stack 类 | 菜鸟教程
 * https://www.runoob.com/java/java-stack-class.html
 * @author yzz
 *
 */

import java.util.Stack;

public class StackDemo {
	
	static void showpush(Stack<String> st, String str) {
		st.push(str);
		System.out.println("push(" + str + ")");
	}

	static void showpop(Stack<String> st) {
		System.out.println("pop -> " + st.pop());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack<String> st = new Stack<String>();
		showpush(st, "A");
        showpush(st, "B");
        showpush(st, "C");
       
        showpop(st);
        showpop(st);
        showpop(st);
        try {
            showpop(st);
        } catch (EmptyStackException e) {
            System.out.println("empty stack");
        }
	}

}
