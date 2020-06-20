package NewBinaryTree.AVL;

import java.util.ArrayList;

/**
 * @author yzze
 * @create 2020-05-14 16:22
 */
public class AVLTree<K extends Comparable<K>, V>  {
    private class Node{
        public K key;
        public V value;
        public Node left,right;
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 获得节点node的高度
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // 计算节点node的平衡因子
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    // 判断该二叉树是否是一颗二分搜索树
    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i-1).compareTo(keys.get(i)) > 0) {  // 中序遍历：升序
                return false;
            }
        }
        return true;
    }
    private void inOrder(Node node, ArrayList<K> keys) {
        if (node == null) {
            return ;
        }

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    // 判断该二叉树是否是一颗平衡二叉树
    public boolean isBalanced() {
        return isBalanced(root);
    }
    private boolean isBalanced(Node node) {
        if(node == null) {
            return true;
        }

        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    /**
     * 对节点y进行右旋转操作，返回旋转后新的根节点x
     *           y                          x
     *         /  \                      /    \
     *        x   T4                    z      y
     *      /  \       --------->     /  \    / \
     *     z   T3                   T1   T2 T3  T4
     *   /  \
     * T1   T2
     * @param y
     * @return
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;

        x.right = y;
        y.left = T3;

        // 更新height
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

        return x;
    }

    /**
     * 对节点y进行左旋转操作，返回旋转后新的根节点x
     *     y                             x
     *   /  \                         /    \
     * T1    x                       y      z
     *      / \     ------------>  /  \   /  \
     *     T2  z                  T1  T2 T3  T4
     *        / \
     *       T3 T4
     * @param y
     * @return
     */
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;

        x.left = y;
        y.right = T2;

        // 更新height
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

        return x;
    }

    /**
     * 向二分搜索树中添加新的元素(key, value)
     * @param key
     * @param value
     */
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

        // 更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);

        /**
         * 平衡维护
         */
        // 左子树过长 右旋转 LL
        if (balanceFactor > 1  && getBalanceFactor(node.left) >= 0) {  // 向左倾斜
            return rightRotate(node);
        }
        // 右子树过长 左旋转 RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) { // 向右倾斜
            return leftRotate(node);
        }

        // LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
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

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

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
     * 从二分搜索树中删除元素为e的节点
     * @param
     */
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
        Node retNode;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {  //key.compareTo(node.key) == 0
            //待删除节点左子树为空情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            }
            //待删除节点右子树为空情况
            else if(node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else {
                //左右子树均不为空
                //方法：找到比待删除节点大的最小节点，即待删除节点右子树的最小节点
                //用这个节点顶替待删除节点的位置
                Node successor = minimum(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null;
                retNode = successor;
            }
        }

        if (retNode == null) {
            return null;
        }

        // 更新height
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        /**
         * 平衡维护
         */
        // 左子树过长 右旋转 LL
        if (balanceFactor > 1  && getBalanceFactor(retNode.left) >= 0) {  // 向左倾斜
            return rightRotate(retNode);
        }
        // 右子树过长 左旋转 RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) { // 向右倾斜
            return leftRotate(retNode);
        }

        // LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        // RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;

    }

    public static void main(String[] args) {

    }
}

