package DataStructure.Array;


/**
 * 二次封装自己的数组
 * 基本功能 增 删 改 查
 * 泛型数组
 * 动态数组
 * @author yzz
 *
 */
public class Array<E> {

    private E[] data;
    private int size;

    /**
     * 构造函数，传入数组的容量capacity
     * @param capacity
     */
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * 无参构造函数，默认数组容量capacity=10
     */
    public Array() {
        this(10);
    }

    /**
     * 获取数组中的元素个数
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组容量
     * @return
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 数组是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向所有元素后添加一个新元素
     * O(1)
     * @param e
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 在所有元素前添加一个新元素
     * O(n)
     * @param e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 向指定位置添加元素
     * O(n/2)=O(n)
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (size == data.length) {
            // 扩容两倍
            resize(2 * data.length);
        }

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        }

        for (int i = size - 1; i >= index; i--) {
            data[i+1] = data[i];
        }

        data[index] = e;
        size++;
    }

    /**
     * 扩容数组
     * 创建一个新数组，容量为原来容量的两倍，将原数据拷贝进新数组，并改变data的引用
     * O(n)
     * @param newCapacity
     */
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * 获取index索引位置的元素
     * O(1)
     * @param index
     * @return
     */
    public E get(int index)
    {
        if (index < 0 || index >= size)   // index < size :无法查询为使用空间的元素
            throw new IllegalArgumentException("Get failed.Index is illegal");
        return data[index];
    }

    /**
     * 修改index索引位置的元素e
     * O(1)
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed.Index is illegal");
        data[index] = e;
    }

    /**
     * 查找数组中是否有元素e
     * O(n)
     * @param e
     * @return
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找数组中元素e所在的索引，如果不存在元素e，则返回-1
     * O(n)
     * @param e
     * @return
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除指定索引位置的元素，返回删除的元素
     * O(n/2)=O(n)
     * @param index
     * @return
     */
    public E remove(int index)
    {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed.Index is illegal");
        E ret = data[index];
        for (int i = index + 1; i < size; i++)
        {
            data[i - 1] = data[i];
        }
        size--;
//        data[size] = Default(E);
        // data[size] = null;  // loitering objects != memory leak
        /*
         * 如果容量比较大，我们存的数据使用的空间少，将会造成资源的浪费
         * 我们这时设置，当数组中数据的量小于容量的1/2时，将数组容量缩减一半
         *
         * 设置为1/4 : 避免复杂度震荡
         */
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return ret;
    }

    /**
     * 从数组中删除第一个元素，返回删除的元素
     * O(n)
     * @return
     */
    public E removeFirst()
    {
        return remove(0);
    }

    /**
     * 从数组中删除最后一个元素，返回删除的元素
     * O(1)
     * @return
     */
    public E removeLast()
    {
        return remove(size - 1);
    }

    /**
     * 从数组中删除元素e（只删除一个e）
     * @param e
     */
    public void removeElement(E e)
    {
        int index = find(e);
        if (index != -1)
            remove(index);
    }

    /**
     * 重写ToString
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //sb.append("MyArray:size = {" + size +"}, capacity = {" + getCapacity()+ "}\n");
        sb.append(String.format("Array:size = %d, capacity = %d\n", size, getCapacity()));
        sb.append("[");
        for (int i = 0; i < size; i++)
        {
            sb.append(data[i]);
            if (i != size - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public E getLast() {
        // TODO Auto-generated method stub
        return get(size - 1);
    }

    public E getFirst() {
        return get(0);
    }

    public void swap(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IllegalArgumentException("Index is illegal");
        }

        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }
}
