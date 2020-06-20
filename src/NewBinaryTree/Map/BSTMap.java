package NewBinaryTree.Map;

/**
 * @author yzze
 * @create 2020-04-28 17:47
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {
    private class Node{
        public K key;
        public V value;
        public Node left,right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向二分搜索树中添加新的元素(key, value)
     * @param key
     * @param value
     */
    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    /**
     * 向以root为根的二分搜索树中插入元素(key, value)
     * 返回插入新节点后二分搜索树的根
     * @param node
     * @param key
     * @param value
     * @return
     */
    private Node add(Node node, K key, V value){
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else { //key.compareTo(node.key) == 0
            node.value = value;
        }

        return node;
    }

    /**
     * 返回以node为根节点的二分搜索树中，Key所在的节点
     * @param node
     * @param key
     * @return
     */
    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node;
        } else if(key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else { //key.compareTo(node.key) > 0
            return getNode(node.right, key);
        }
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if(node == null) {
            throw new IllegalArgumentException(key + "doesn't exist!");
        }
        node.value = newValue;
    }

    /**
     * 寻找二分搜索树的最小元素
     * @return
     */
    public K minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        Node minNode = minimum(root);
        return minNode.key;
    }
    /**
     * 返回以node为根的二分搜索树的最小值所在的节点
     * @param node
     * @return
     */
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }
    /**
     *  删除最小值并返回
     * @return
     */
    public K removeMin() {
        K ret = minimum();
        root = removeMin(root);
        return ret;
    }
    /**
     * 删除掉以node为根的二分搜索树中的最小节点
     * 返回删除节点后新的二分搜索树的根
     * @param node
     * @return
     */
    private Node removeMin(Node node) {

        //递归的终止条件: 当前节点没有左子树了，那么就是最小节点了
        //如果是最小节点，我们要做的是删除当前节点，但是当前节点很可能是有右子树的
        //我们先把该节点的右子树节点保存，然后就删除掉该右子树节点，最后把右子树节点返回即可
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null; // 左节点为空了，让右子树也为空，相当于脱离了树
            size--;
            return rightNode;  // 返回右子树是为了后面的绑定操作
        }

        node.left = removeMin(node.left);

        return node;           // 删除后，根节点依然是node，返回即可
    }
    /**
     * 从二分搜索树中删除元素为e的节点
     * @param
     */
    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root,key);
            return node.value;
        }
        return null;
    }

    /**
     * 删除以node为根的二叉搜索树中键为key的节点，递归算法
     * 返回删除节点后更新的二叉搜索树的根
     * @param node
     * @param key
     * @return
     */
    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {  //key.compareTo(node.key) == 0
            //待删除节点左子树为空情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            //待删除节点右子树为空情况
            if(node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            //左右子树均不为空
            //方法：找到比待删除节点大的最小节点，即待删除节点右子树的最小节点
            //用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);  // 在removeMin中进行了size--;
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
