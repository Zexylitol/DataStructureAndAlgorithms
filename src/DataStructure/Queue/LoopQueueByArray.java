package DataStructure.Queue;

/**
 * 动态数组实现双向队列
 * @author yzz
 *
 * @param <E>
 * 【数据结构】栈的基本实现 - Maybe_ch的博客 - CSDN博客
 *  https://blog.csdn.net/Maybe_ch/article/details/83501969
 *  使用我们自己的动态数组实现栈和队列 - qq_22230709的博客 - CSDN博客
 *  https://blog.csdn.net/qq_22230709/article/details/81531026
 *  数据结构从入门到进阶-课程章节
 *  https://coding.imooc.com/class/chapter/207.html#Anchor
 */
public class LoopQueueByArray<E> implements iQueue<E> {
	private E[] data;
	private int front, tail;
	// 这里为了简单我们直接将size定义出来进行维护，其实可以使用front和tail进行维护，但是这里有resize操作可能没这么简单
	private int size;
	public LoopQueueByArray(int capacity) {
		data = (E[]) new Object[capacity + 1] ; // 在实现循环队列的时候我们需要浪费掉一个空间
		front = 0;
		tail = 0;
		size = 0;
	}
	
	public LoopQueueByArray() {
		//data = (E[]) new Object[10 + 1];
		this(10);
	}
	
	public int getCapacity() {
		return data.length - 1;
	}
	
	@Override
	public int getSize() {
		return size;
	}
	
	@Override
	public boolean isEmpty() {
		return front == tail;
	}
	
	@Override
	public void enQueue(E e) {
		if ((tail + 1) % data.length == front) {
			resize(getCapacity() * 2);
		}
		data[tail] = e;
		tail = (tail+1) % data.length;
		size++;
	}
	
	private void resize(int newCapacity) {
		E[] newData = (E[]) new Object[newCapacity + 1];
		for (int i = 0; i < size; i++) {
			// data中的元素相对于newData中的元素有一个front偏移
			// 有可能data 中的第一个元素就不是第一个元素了，
			// 由于是循环队列，数组可能会越界
			newData[i] = data[(i+front) % data.length];
		}
		data = newData;
		front = 0;
		tail = size;
	}

	/**
	 * O(1)
	 * @return
	 */
	@Override
	public E deQueue() {
		if (isEmpty()) {
			throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
		}
		E ret = data[front];
		data[front] = null;
		front = (front + 1) % data.length;
		size--;
		if (size == getCapacity()/4 && getCapacity()/2 != 0) {
			resize(getCapacity()/2);
		}
		return ret;
	}
	
	@Override
	public E getFront() {
		if (isEmpty()) {
			throw new IllegalArgumentException("Queue is empty");
		}
		return data[front];
	}

	// 其实这里栈中间的元素是不能看到的，只是我们这里为了方便查看
	@Override
	public String toString() {
		StringBuffer res = new StringBuffer();
		res.append(String.format("loopQueue: size =%d, capacity=%d\n", size,getCapacity()));
		res.append("loopQueue: ");
		res.append("front [");
		for(int i=front; i != tail; i= (i+1)%data.length) {   // i= (i+1)%data.length i循环加1
			res.append(data[i]);
			if ((i+1)%data.length != tail) {
				res.append(",");
			}
		}
		res.append("] tail");
		return res.toString();
	}
	public static void main(String[] args) {
		LoopQueueByArray<Integer> queue = new LoopQueueByArray<>();
		for (int i = 0; i < 10; i++) {
			queue.enQueue(i);
			System.out.println(queue);
			if(i % 3 == 2) {
				queue.deQueue();
				System.out.println(queue);
			}
		}
	}
}
