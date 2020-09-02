package Algorithms.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 利用栈完成一个逆波兰计算器
 * 逆波兰表达式又叫做后缀表达式
 * eg: 4*5 - 8 + 60 + 8 / 2  ----->  4 5 * 8 - 60 + 8 2 / +
 * https://www.jb51.net/article/170831.htm
 * @create 2020-09-02 20:31
 */
public class PolandNotation {

    public static void main(String[] args) {
        //4*5-8+60+8/2
        String expression = "4 5 * 8 - 60 + 8 2 / +";
        List<String> list = getStrList(expression);
        System.out.println(list);
        //计算值，得结果
        int res = calc(list);
        System.out.println(res);
    }

    /**
     * 遍历表达式，并将遍历的结果放入list中
     * @param exp 表达式
     * @return
     */
    public static List<String> getStrList(String exp){
        String arr[] = exp.split(" ");   //将字符串遍历得到数组
        List<String> list = new ArrayList<>();
        for(String str : arr){
            list.add(str);
        }
        return list;
    }

    //计算表达式
    public static int calc(List<String> list ){
        //创建存放字符串的栈
        Stack<String> stack = new Stack<>();
        //遍历list
        for (int i = 0;i<list.size();i++){
            //正则表达式匹配是否是数字
            if(list.get(i).matches("\\d+")){
                stack.push(list.get(i));                 //是数字则放入栈中
            }else {
                int num2 = Integer.parseInt(stack.pop());//弹出数字1
                int num1 = Integer.parseInt(stack.pop());//弹出数字2
                int res = 0;
                //进行运算
                if(list.get(i).equals("+")){
                    res = num1 + num2;
                }else if(list.get(i).equals("-")){
                    res = num1 - num2;
                }else if(list.get(i).equals("*")){
                    res = num1 * num2;
                }else if(list.get(i).equals("/")){
                    res = num1/num2;
                }else {
                    throw new RuntimeException("不是操作符号！");
                }
                stack.push(""+res);
            }
        }
        //留在栈中的值就是最后的计算表达式结果
        return Integer.parseInt(stack.pop());
    }
}


