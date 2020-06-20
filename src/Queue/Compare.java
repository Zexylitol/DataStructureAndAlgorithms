package Queue;

import java.util.Random;

/**
 * 数组队列和循环队列的比较
 * @author yzze
 * @create 2020-04-18 19:00
 */
public class Compare {
    //测试使用q运行opCount个euQueue和deQueue操作所需要的时间，单位：秒
    public static double testQueue(iQueue<Integer> q, int opCount) {
        long startTime = System.nanoTime();  //单位：纳秒

        Random random = new Random();
        for(int i = 0; i < opCount; i++) {
            q.enQueue(random.nextInt(Integer.MAX_VALUE));
        }
        for(int i = 0; i < opCount; i++){
            q.deQueue();
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int opCount = 100000;

        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue, opCount);
        System.out.println("QueueByArray, time: "+ time1 + " s");

        LoopQueueByArray<Integer> loopQueue = new LoopQueueByArray<>();
        double time2 = testQueue(loopQueue, opCount);
        System.out.println("LoopQueueByArray, time: "+ time2 + " s");

        LinkedListQueue<Integer> queueByLinkedList = new LinkedListQueue<>();
        double time3 = testQueue(queueByLinkedList, opCount);
        System.out.println("QueueByLinkedList, time: "+ time3 + " s");

    }
}
