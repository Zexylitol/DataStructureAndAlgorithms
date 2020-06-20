package DataStructure.Stack;

import java.util.Random;
import java.util.Stack;

/**
 * 数组栈和链表栈的比较
 * @author yzze
 * @create 2020-04-20 18:03
 */
public class Compare {

    /**
     * 测试使用stack运行opCount个push和pop操作所需要的时间，单位：秒
     * @param stack
     * @param opCount
     * @return
     */
    private static double testStack(iStack<Integer> stack, int opCount) {
        long startTime = System.nanoTime();

        Random random = new Random();
        for(int i = 0; i < opCount; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for(int i = 0; i < opCount; i++) {
            stack.pop();
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int opCount = 1000000;

        ArrayStack<Integer> stackByArray = new ArrayStack<>();
        double time1 = testStack(stackByArray, opCount);

        StackByLinkedList<Integer> stackByLinkedList = new StackByLinkedList<>();
        double time2 = testStack(stackByLinkedList, opCount);

        System.out.println("ArrayStack: " + time1 + " s");
        System.out.println("LinkedListStack: " + time2 + " s");

        // 其实这个时间比较很复杂，因为StackByLinkedList中包含更多的new操作
    }
}
