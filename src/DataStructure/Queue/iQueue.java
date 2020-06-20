package DataStructure.Queue;

public interface iQueue<E> {
	int getSize();
	boolean isEmpty();
	void enQueue(E e);
	E deQueue();
	E getFront();
}
