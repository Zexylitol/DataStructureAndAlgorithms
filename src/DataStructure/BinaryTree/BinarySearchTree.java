package DataStructure.BinaryTree;

/**
 * 二叉查找树
 * 	1. 非空左子树的所有键值小于其根结点的键值
 *  2. 非空右子树的所有键值大于其根结点的键值
 *  3. 左、右子树都是二叉搜索树
 * 查找效率取决于树的高度
 * 查找、删除和插入操作的时间复杂度都是O(logn)底数为2
 * 
 * 缺陷：如果插入数据有序（从大到小或从小到大），左子树或右子树会变得特别长，查找性能大打折扣，几乎就是线性查找，时间复杂度为O(n)
 */

public class BinarySearchTree {
	
	protected BSTNode root;
	
	public BSTNode getRoot() {
		return root;
	}

	public void setRoot(BSTNode root) {
		this.root = root;
	}
	
	/**
	 * 获取树的高度
	 * @return
	 */
	public int height() {
		return height(this.root);
	}
	private int height(BSTNode node) {
		if (node == null) {
			return 0;
		}
		return 1+Math.max(height(node.left), height(node.right));
	}


	/**
	 * 从二叉搜索树BST中查找元素data_，返回其所在节点的地址
	 * 查找所需要的最大次数等同于二叉查找树的高度
	 * @param data_
	 * @return 节点地址
	 */
	public BSTNode find(int data_) {
		
		BSTNode curNode = root;
		while(curNode != null) {
			if (curNode.data > data_) {                // 向右子树中移动，继续查找
				curNode = curNode.left;
			} else if (curNode.data < data_) {         // 向左子树中移动，继续查找
				curNode = curNode.right;
			} else {
				return curNode;                        // 查找成功，返回节点的地址
			}
		}
		return null;                                   // 查找失败
	}
	
	/**
	 * 查找最大元素
	 * 最大元素一定是在树的最右分支的端节点上
	 * @return
	 */
	public BSTNode findMax(BSTNode root_) {
		
		if (root_ == null) {
			return null;
		}
		BSTNode curnNode = root_;
		while(curnNode.right != null) {
			curnNode = curnNode.right;
		}
		return curnNode;
	}
	
	/**
	 * 查找最小元素
	 * 最小元素一定是在树的最左分支的端节点上
	 * @param root_
	 * @return
	 */
	public BSTNode findMinWithRecursion(BSTNode root_) {
		
		BSTNode tmpNode = root_;
		if (tmpNode == null) return null;            // 空的二叉树，返回null
		else if (tmpNode.left == null) return tmpNode;  // 找到最左叶节点并返回
		else return findMinWithRecursion(tmpNode.left);		        // 沿左分支继续查找
	}
	public BSTNode findMin(BSTNode root_) {
		
		if (root_ == null) {
			return null;
		}
		BSTNode curNode = root_;
		while (curNode.left != null) {
				curNode = curNode.left;
		}		
		return curNode;
	}
	
	/**
	 * 插入节点
	 * @param data_
	 * @return
	 */
	private BSTNode insert(BSTNode root_, int data_) {
		BSTNode curNode = null;
		if (root_ == null) {                        // 原树为空     
			root_ = new BSTNode(data_);
		} else {			
			curNode = root_;			
			while(curNode != null) {				
				if (curNode.data > data_) {		               // 当前值比插入值大，搜索左子树			
					if (curNode.left == null) {                // 左子树为空，直接将新值插入到该节点
						curNode.left = new BSTNode(data_);
						break;
					}
					curNode = curNode.left;
				} else if (curNode.data < data_) {              // 当前值比插入值小，搜索右子树	
					if (curNode.right == null) {                // 右子树为空，直接将新值插入到该节点
						curNode.right = new BSTNode(data_);
						break ;
					}
					curNode = curNode.right;
				} else {                                        // data_已存在
					break ;
				}
			}
		}
		return root_;
		
	}
	public void insert(int data_) {		
		this.root = insert(this.root, data_);
	}

	
	/**
	 * 删除节点(递归)
	 * 返回值：根节点
	 * @param root_
	 * @param data_
	 */
	public BSTNode delete(BSTNode root_, int data_) {
		BSTNode tmp;
		if (root_ == null) {
			return null;
		} else if (data_ < root_.data) {
			root_.left = delete(root_.left, data_);              // 左子树 递归 删除
		} else if (data_ > root_.data) {
			root_.right = delete(root_.right, data_);            // 右子树 递归 删除
		} else {                                                 // 找到要删除的节点
			if (root_.left != null && root_.right != null) {     // 被删除节点有左右两个子节点
				tmp = findMin(root_.right);                      // 在右子树中找最小的元素填充删除节点
				root_.data = tmp.data;
				root_.right = delete(root_.right, root_.data);   // 在删除节点的右子树中删除最小元素
			} else {                                             // 被删除节点有一个或无子节点				
				if (root_.left == null) {                        // 有右孩子或无子节点
					root_ = root_.right;
				} else if (root_.right == null) {                // 有左孩子或无子节点
					root_ = root_.left;
				}
			}
		}
		return root_;
	}
	
	/**
	 * 中序遍历（左中右）
	 * @param root_
	 */
	public void inOrderTraversal(BSTNode root_) {
		if (root_ != null) {
			inOrderTraversal(root_.left);
			System.out.print(root_.data + " ");
			inOrderTraversal(root_.right);
		}
	}
	
	/**
	 * 先序遍历（中左右）
	 * @param root_
	 */
	public void preOrderTraversal(BSTNode root_) {
		if (root_ != null) {
			System.out.print(root_.data + " ");
			preOrderTraversal(root_.left);
			preOrderTraversal(root_.right);
		}
	}
	
	/**
	 * 后序遍历（左右中）
	 * @param root_
	 */
	public void postOrderTraversal(BSTNode root_) {
		if (root_ != null) {
			postOrderTraversal(root_.left);
			postOrderTraversal(root_.right);
			System.out.print(root_.data + " ");
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinarySearchTree bst = new BinarySearchTree();
		
		/*
		 * 生成树：
		 *                40
		 *         35               50
		 *     32       36      45     60
		 * 30  
		 */
		bst.insert(40);
		bst.insert(35);
		bst.insert(32);
		bst.insert(50);
		bst.insert(45);
		bst.insert(60);
		bst.insert(36);
		bst.insert(30);

		System.out.print("先序遍历： ");
		bst.preOrderTraversal(bst.getRoot());
		System.out.println();
		
		System.out.print("中序遍历： ");
		bst.inOrderTraversal(bst.getRoot());
		System.out.println();
		
		System.out.print("后序遍历： ");
		bst.postOrderTraversal(bst.getRoot());
		System.out.println();
		
		bst.delete(bst.getRoot(), 30);
		System.out.print("删除元素：30 先序遍历： ");
		bst.preOrderTraversal(bst.getRoot());
		System.out.println();
		
		BSTNode newNode = bst.delete(bst.getRoot(), 60);
		System.out.print("删除元素：60 先序遍历： ");
		bst.preOrderTraversal(bst.getRoot());
		System.out.println();
		System.out.println("delete返回： " + newNode.data + " " + newNode.left.data + " " + newNode.right.data);
	}
	
}
