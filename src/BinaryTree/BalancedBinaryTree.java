package BinaryTree;

/**
 * 平衡二叉树
 * 1. 必须是二叉搜索树
 * 2. 每个节点的左子树和右子树的高度差至多为1
 * @author yzz
 *
 */
public class BalancedBinaryTree extends BinarySearchTree {

    /**
     * 返回平衡二叉树的高度
     * @return
     */
    public int height() {
        return height((AVLNode) this.root);
    }

    /**
     * 获取节点高度
     * @param node
     * @return
     */
    private int height(BSTNode node) {
        if (node != null && node instanceof AVLNode) {
            return (((AVLNode) node).height);
        }
        return 0;
    }

    /**
     * 获取左、右子树的高度差绝对值
     * @param left_
     * @param right_
     * @return
     */
    public int getHeightDifference(BSTNode left_, BSTNode right_) {
        return Math.abs(height(left_) - height(right_));
    }

    /**
     * 左旋操作（“麻烦节点”在“发现者”右子树的右边，叫RR插入,需要RR旋转/右单旋）
     * @param node -- “发现者”
     * @return 新根节点
     * 			80            左旋             90
     * 		60 		90        -->        80       120
     * 			  85  120             60    85   100
     * 				 100
     */
    private AVLNode leftRotation(AVLNode node) {
        AVLNode rChild = (AVLNode) node.right;       // 取“发现者”的右节点
        node.right = rChild.left;                            // 平衡树也是搜索树
        rChild.left = node;      // rChild成为此子树的根节点
        // 更新节点rChild和node节点的高度
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        rChild.height = Math.max(height(rChild.left), height(rChild.right)) + 1;
        return rChild;
    }

    /**
     * 右旋操作 （“麻烦节点”在“发现者”左子树的左边，叫LL插入，需要LL旋转/左单旋）
     * @param node -- “发现者”
     * @return 新根节点
     * 			 100           右旋            85
     * 		85		  120      -->       60  	  100
     * 	60 	   90                          80   90   120
     * 	  80
     */
    private AVLNode rightRotation(AVLNode node) {
        AVLNode lChild = (AVLNode) node.left;         // 取“发现者”的左节点
        node.left = lChild.right;                             // 平衡树也是搜索树
        lChild.right = node;
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        return lChild;
    }

    /**
     * 先右旋后左旋(“麻烦节点”在“发现者”右子树的左边)
     * 绕某元素的右子节点右旋转，接着再绕该元素自己左旋转
     * @param node -- “发现者”
     * @return 新根节点
     * 			80               右旋          80                 左旋           85
     * 		60 		100          -->      60       85            -->        80     100
     * 			  85   120                           100                  60     90   120
     * 				90                             90   120
     */
    private AVLNode rightLeftRotationav(AVLNode node) {
        // 将node节点的右儿子作为根结点进行右旋
        node.right = rightRotation((AVLNode)node.right);
        return leftRotation(node);
    }

    /**
     * 先左旋后右旋（“麻烦节点”在“发现者”左子树的右边）
     * 绕某元素的左子节点左旋转，接着再绕该元素自己右旋转
     * @param node
     * @return
     * 			100              左旋           100               右旋            90
     * 		80       120         -->        90     120           -->         80     100
     *   60    90                        80                                60  85       120
     *       85                        60  85
     */
    private AVLNode leftRightRotation(AVLNode node) {
        node.left = leftRotation((AVLNode)node.left);
        return rightRotation(node);
    }

    /**
     * 生成根节点
     * @param data_
     * @return
     */
    public AVLNode generateTreeNode(int data_) {
        AVLNode treeNode = new AVLNode();
        treeNode.data = data_;
        treeNode.left = null;
        treeNode.right = null;
        treeNode.height = 1;
        return treeNode;
    }

    @Override
    public void insert(int data_) {
        this.root = insert((AVLNode)this.root, data_);
    }

    /**
     * 插入节点
     * @param root_
     * @param data_
     * @return 调整后的子树的根节点
     */
    private AVLNode insert(AVLNode root_, int data_) {

        if (root_ == null) {                        // 原树为空
            root_ = generateTreeNode(data_);
        } else if(data_ < root_.data) {
            root_.left = insert((AVLNode)root_.left, data_);       // 插入左子树
            if (getHeightDifference(root_.left, root_.right) == 2) {
                if (data_ < root.left.data) {
                    // 右旋
                    root_ = rightRotation(root_);
                } else {
                    // 左-右
                    root_ = leftRightRotation(root_);
                }
            }
        } else if (data_ > root_.data) {
            root_.right = insert((AVLNode)root_.right, data_);
            if (getHeightDifference(root_.left,root_.right) == 2) {
                if (data_ > root_.right.data) {
                    // 左旋
                    root_ = leftRotation(root_);
                } else {
                    // 右-左
                    root_ = rightLeftRotationav(root_);
                }
            }
        }
        // 更新树的高度
        root_.height = Math.max(height(root_.left), height(root_.right)) + 1;
        return root_;
    }


    /**
     * 删除节点
     * @param root_
     * @param data_
     * @return 新树的根节点
     * @note 理解递归 ： 画层层调用框图
     */
    public AVLNode delete(AVLNode root_, int data_) {
        AVLNode tmp;
        if (root_ == null) {                                     // 没有找到待删除的节点直接返回
            return null;
        } else if (data_ < root_.data) {
            //删除左子树导致不平衡及右子树需要进行调整
            root_.left = delete(root_.left, data_);              // 左子树 递归 删除
            if (getHeightDifference(root_.left, root_.right) == 2) {
                //以右子树为根节点判断其左右子树的高度
                AVLNode nodeRight = (AVLNode) root_.right;
                if (height(nodeRight.left) > height(nodeRight.right)) {
                    root_ = rightLeftRotationav(root_);
                } else {
                    root_ = leftRotation(root_);
                }
            }
        } else if (data_ > root_.data) {
            root_.right = delete(root_.right, data_);            // 右子树 递归 删除
            if (getHeightDifference(root_.left, root_.right) == 2) {
                AVLNode nodeLeft = (AVLNode)root_.left;
                if (height(nodeLeft.right) > height(nodeLeft.left)) {
                    root_ = leftRightRotation(root_);
                } else {
                    root_ = rightRotation(root_);
                }
            }
        } else {                                                 // 找到要删除的节点
            if (root_.left != null && root_.right != null) {     // 被删除节点有左右两个子节点
                // 待删除节点的左子树比右子树高
                if (height(root_.left) > height(root_.right)) {
                    tmp = (AVLNode) findMax(root_.left);     // 在左子树中找最大的元素填充删除节点
                    root_.data = tmp.data;
                    root_.left = delete(root_.left, tmp.data);   // 在删除节点的左子树中删除最大元素
                } else {
                    tmp = (AVLNode)findMin(root_.right);
                    root_.data = tmp.data;
                    root_.right = delete(root_.right, tmp.data);   // 在删除节点的右子树中删除最小元素
                }

            } else {                                             // 被删除节点有一个或无子节点
                if (root_.left == null) {                        // 有右孩子或无子节点
                    root_ = (AVLNode)root_.right;
                } else if (root_.right == null) {                // 有左孩子或无子节点
                    root_ = (AVLNode)root_.left;
                }

            }
        }
        return root_;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        BalancedBinaryTree avl = new BalancedBinaryTree();
        avl.insert(80);
        avl.insert(60);
        avl.insert(100);
        avl.insert(85);
        avl.insert(120);

        System.out.print("先序遍历： ");
        avl.preOrderTraversal(avl.getRoot());
        System.out.println();

        System.out.print("中序遍历： ");
        avl.inOrderTraversal(avl.getRoot());
        System.out.println();

        System.out.print("后序遍历： ");
        avl.postOrderTraversal(avl.getRoot());
        System.out.println();

        avl.insert(90);
        System.out.print("插入元素：90 先序遍历： ");
        avl.preOrderTraversal(avl.getRoot());
        System.out.println();

        AVLNode newNode = (AVLNode)avl.find(85);
        System.out.println("元素：85 的左节点： " + newNode.left.data + " 右节点： " + newNode.right.data);

    }

}
