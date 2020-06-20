package DataStructure.Stack;

import java.util.Stack;

public class testStack {
	
	/**
	 * 利用自定义栈实现字符串逆序
	 * https://www.cnblogs.com/ysocean/p/7911910.html
	 */
	public void testStack1() {
		LinkedListStack<Object> sbll = new LinkedListStack<Object>();
		String str = "How are you";
		for (int i = 0; i < str.length(); i++) {
			sbll.push(str.toCharArray()[i]);
		}
		while (!sbll.isEmpty()) {
			System.out.print(sbll.pop());
		}
	}
	
	/**
	 * 利用栈判断分隔符是否匹配（Stack类）
	 * https://www.cnblogs.com/ysocean/p/7911910.html
	 */
	public void testStack2() {
		Stack<Object> stack = new Stack<Object>();
		
		String str = "12<a[b{c}]>";
		char[] cha = str.toCharArray();
		
		for(char c : cha){
	        switch (c) {
	        case '{':
	        case '[':
	        case '<':
	            stack.push(c);
	            break;
	        case '}':
	        case ']':
	        case '>':
	            if(!stack.isEmpty()){
	                char ch = stack.pop().toString().toCharArray()[0];
	                if(c=='}' && ch != '{'
	                    || c==']' && ch != '['
	                    || c==')' && ch != '('){
	                    System.out.println("Error:"+ch+"-"+c);
	                }
	            }
	            break;
	        default:
	            break;
	        }
	    }
		
	}

	public void testStack3() {
		ArrayStack<Integer> stack = new ArrayStack<Integer>();
		for (int i = 0; i < 5; i++) {
			stack.push(i);
			System.out.println(stack);
		}
		stack.pop();
		System.out.println(stack);
	}

	/**
	 * leetcode 20.有效的括号
	 * @param s
	 * @return
	 */
	public boolean testStack4(String s) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(' || c == '[' || c == '{') {
				stack.push(c);
			} else {
				if (stack.isEmpty()) {
					return false;
				}
				char topChar = stack.pop();
				if (c == ')' && topChar != '(') {
					return false;
				}
				if (c == ']' && topChar != '[') {
					return false;
				}
				if (c == '}' && topChar != '{') {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testStack ts = new testStack();
		//ts.testStack1();
		//ts.testStack2();
		//ts.testStack3();

		String s = "([])";
		System.out.println(ts.testStack4(s));
	}

}
