package DataStructure.NewBinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author yzze
 * @create 2020-04-25 18:22
 */
public class BST<E extends Comparable<E>> {    // 存储的元素必须具有可比较性
    private class Node{
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向以node为根的二分搜索树中插入元素e，递归算法
     * @param node
     * @param e
     */
//    private void add(Node node, E e) {
//        if(e.equals(node.e)) {
//            return ;
//        } else if (e.compareTo(node.e) < 0 && node.left == null) {
//            node.left = new Node(e);
//            size++;
//            return;
//        } else if (e.compareTo(node.e) > 0 && node.right == null) {
//            node.right = new Node(e);
//            size++;
//            return ;
//        }
//
//        if(e.compareTo(node.e) < 0) {
//            add(node.left, e);
//        } else {
//            add(node.right, e);
//        }
//    }

    /**
     * 向以node为根的二分搜索树中插入元素e，递归算法
     * 返回插入新节点后二分搜索树的根
     * @param node
     * @param e
     * @return
     */
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }

        return node;
    }

    /**
     * 向二分搜索树中添加新的元素e
     * @param e
     */
    public void add(E e) {
        root = add(root, e);
    }

    /**
     * 寻找二分搜索树的最小元素
     * @return
     */
    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        Node minNode = minimum(root);
        return minNode.e;
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
     * 寻找二分搜索树的最大元素
     * @return
     */
    public E maxmum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        Node maxNode = maxmum(root);
        return maxNode.e;
    }
    /**
     * 返回以node为根的二分搜索树的最大值所在的节点
     * @param node
     * @return
     */
    private Node maxmum(Node node) {
        if(node.right == null) {
            return node;
        }
        return maxmum(node.right);
    }

    /**
     *  删除最小值并返回
     * @return
     */
    public E removeMin() {
        E ret = minimum();
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
     * 从二分搜索树中删除最大值所在节点
     * @return
     */
    public E removeMax() {
        E ret = maxmum();
        root = removeMax(root);
        return ret;
    }

    /**
     * 删除掉以node为根的二分搜索树中的最大节点
     * 返回删除节点后新的二分搜索树的根
     * @param node
     * @return
     */
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 看二分搜索树是否包含元素e
     * @param e
     * @return
     */
    public boolean contains(E e) {
        return contains(root, e);
    }

    /**
     * 以node为根的二分搜索树中是否包含元素e, 递归
     * @param node
     * @param e
     * @return
     */
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else { // e.compareTo(node.e, e) > 0
            return contains(node.right, e);
        }
    }

    /**
     * 从二分搜索树中删除元素为e的节点
     * @param e
     */
    public void remove(E e) {
        root = remove(root, e);
    }

    /**
     * 删除以node为根的二叉搜索树中值为e的节点，递归算法
     * 返回删除节点后更新的二叉搜索树的根
     * @param node
     * @param e
     * @return
     */
    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {  //e.compareTo(node.e) == 0
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

    /**
     * 前序遍历（中左右）
     */
    public void preOrder() {
        preOrder(root);
    }
    private void preOrder(Node node) {
        if (node == null) return;

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 前序遍历非递归实现，利用栈stack
     */
    public void preOrderNr() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        System.out.println("前序遍历：");
        while(!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.print(cur.e);

            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        System.out.println();
    }

    /**
     * 中序遍历（左中右）
     * 特点：有序
     */
    public void inOrder() {
        inOrder(root);
    }
    private void inOrder(Node node) {
        if (node == null) {
            return ;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    /**
     * 后序遍历（左右中）
     */
    public void postOrder() {
        postOrder(root);
    }
    private void postOrder(Node node) {
        if(node == null) {
            return ;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    /**
     * 层序遍历（广度优先搜索BFS）
     */
    public void levelOrder() {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        System.out.println("层序遍历：");
        while(!q.isEmpty()) {
            Node cur = q.remove();
            System.out.print(cur.e);

            if(cur.left != null)  q.add(cur.left);
            if(cur.right != null)  q.add(cur.right);
        }
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = {5,3,6,8,4,2};
        for(int num : nums) {
            bst.add(num);
        }
        bst.inOrder();
        System.out.println();
        bst.preOrder();
    }
}
