package DataStructure.BinaryTree;

/**
 * 红黑树
 * 1.   节点是红色或黑色
 * 2.	根是黑色
 * 3.	所有叶子都是黑色[注意：这里叶子节点，是指为空(NIL或NULL)的叶子节点！]
 * 4.   从每个叶子到根的所有路径上不能有两个连续的红色节点
 * 5.	从任一节点到其每个叶子的所有简单路径都包含相同数目的黑色节点 
 * 
 * @author yzz
 *
 */
public class RedBlackTree extends BinarySearchTree {
	
	public static enum COLOR {
		RED, BLACK
	}
	
	/**
	 * 向红黑树插入节点
	 * @param root_
	 * @param data_
	 */
	private RedBlackNode insert(RedBlackNode root_,int data_) {
		RedBlackNode node = null;	
		if (root_ == null) {
			root_ = new RedBlackNode();
			root_.data = data_;
			root_.color = COLOR.BLACK;
			root_.left = null;
			root_.right = null;
			root_.parent = null;
		} else {
			node = root_;			
			while(node != null) {				
				if (node.data > data_) {		               // 当前值比插入值大，搜索左子树			
					if (node.left == null) {                // 左子树为空，直接将新值插入到该节点						
						node.left = new RedBlackNode();	
						node.left.data = data_;
						((RedBlackNode)node.left).parent = node;
						insertFixUp((RedBlackNode)node.left);
						break;
					}
					node = (RedBlackNode)node.left;
				} else if (node.data < data_) {              // 当前值比插入值小，搜索右子树	
					if (node.right == null) {                // 右子树为空，直接将新值插入到该节点
						node.right = new RedBlackNode();
						node.right.data = data_;
						((RedBlackNode)node.right).parent = node;
						insertFixUp((RedBlackNode)node.right);
						break ;
					}
					node = (RedBlackNode)node.right;
				} else {                                        // data_已存在
					break ;
				}
			}
		}
		return root_;
	}	
	public void insert(int data_) {
		this.root = insert((RedBlackNode) this.root, data_);
	}
	
	// 红黑树修正
	private void insertFixUp(RedBlackNode node) {
		// 定义父节点及祖父节点
		RedBlackNode parent ;  
		RedBlackNode gparent;
		
		// 需要修正的条件：父节点存在，且父节点的颜色是红色
		while (((parent = parentOf(node)) != null)  && (isRed(parent))) {
			gparent = parentOf(parent);                                  // 获得祖父节点
			if (parent == gparent.left) {                                // 若父节点是祖父节点的左子节点，下面的else相反
				
				RedBlackNode uncle = (RedBlackNode)gparent.right;        // 获得叔叔节点
				
				// case1: 叔叔节点也是红色
				if (uncle != null && isRed(uncle)) {
					setBlack(parent);                                    // 把父节点和叔叔节点涂黑
					setBlack(uncle);
					setRed(gparent);                                     // 把祖父节点涂红
					node = gparent;                                      // 把位置放到祖父节点处
												                         // 将当前节点指向其祖父节点，再次从新的当前节点开始算法
					continue;                                            // 继续while循环，重新判断
				}
				
				// case2: 叔叔节点是黑色，且当前节点是右子节点
				if (node == parent.right) {
					leftRotation(parent);                                // 从父节点出左旋
					RedBlackNode tmp = parent;                           // 然后将父节点和自己调换一下，为下面右旋做准备
					parent = node;
					node = tmp;
				}
				
				//case3:叔叔节点是黑色，且当前节点是左子节点
//				if (isBlack(uncle) && node == parent.left) {
					setBlack(parent);
					setRed(gparent);
					rightRotation(gparent);
//				}
			} else {                                                     // //若父节点是祖父节点的右子节点，与上面的情况完全相反，本质是一样的
				RedBlackNode uncle = (RedBlackNode)gparent.left; 
				
				// case1:叔叔节点也是红色的
	            if(uncle != null && isRed(uncle)){
	                setBlack(parent);
	                setBlack(uncle);
	                setRed(gparent);
	                node = gparent;
	                continue;
	            }
	            // case2:叔叔节点是黑色的，且当前节点是左子节点
	            if (node == parent.left ) {
	            	rightRotation(parent);
	                RedBlackNode tmp = parent;
	                parent = node;
	                node = tmp;
	            }
	            
	            // case3:叔叔节点是黑色的，且当前节点是右子节点
//	            if (isBlack(uncle) && node == parent.right) {
	                setBlack(parent);
	                setRed(gparent);
	                leftRotation(gparent);
//	            }
			}
		}
		setBlack((RedBlackNode)this.root);    // 将根节点设置为黑色
	}
	
	/**
	 * 求node 节点的后继节点
	 * 
	 * @param node
	 * @return
	 */
	protected BSTNode successorTreeNode(BSTNode node) {

		// 判断只对红黑树作用
		if (!(node instanceof RedBlackNode)) {
			throw new RuntimeException("not support exception !! ");
		}

		if (node.right != null) {
			return findMin(node.right);
		}
		BSTNode parentNode = ((RedBlackNode) node).parent;
		while (parentNode != null && node == parentNode.right) {
			node = parentNode;
			parentNode = ((RedBlackNode) node).parent;
		}
		return parentNode;

	}

	
	/**
	 * 删除红黑树中的节点
	 * @param data_
	 */
	public void delete(int data_) {
		RedBlackNode node = (RedBlackNode)find(data_);
		if (node != null) {
			delete(node);
		}
	}
	private void delete(RedBlackNode delNode) {
		//进行节点的删除操作
		RedBlackNode pNode = null;
		RedBlackNode qNode = null;
		if (delNode.left == null || delNode.right == null) {
			// 左孩子或者右孩子不为空,将待删除节点赋值给pNode节点
			pNode = delNode;
		} else {
			// 否则找到待删除节点的后继节点(及按照中序遍历待删除节点的后继节点)
			pNode = (RedBlackNode) successorTreeNode(delNode);
		}
		// 判断pNode节点的孩子节点是否为空
		if (pNode.left != null) {
			// 左孩子不为空
			qNode = (RedBlackNode) pNode.left;
		} else {
			// 右孩子不为空
			qNode = (RedBlackNode) pNode.right;
		}

		// 改变节点子父节点的指针指向
		if (qNode != null) {
			qNode.parent = pNode.parent;
		}
		// 若节点pNode的父节点为空,则pNode节点原本为根节点
		if (pNode.parent == null) {
			this.root = qNode;
		} else if (pNode == (RedBlackNode) pNode.parent.left) {
			// pNode 节点是其父节点的左孩子节点,pNode 就是待删除的节点
			pNode.parent.left = qNode;
		} else {
			pNode.parent.right = qNode;
		}

		// pNode 节点与待删除节点不同,则替换对应的值,待删除节点直接变成pNode节点
		if (pNode != delNode) {
			delNode.data = pNode.data;
		}

		// 若当前待删除节点是黑色节点,pNode
		if (pNode.color == COLOR.BLACK && qNode != null) {
			deleteFixUp(qNode);
		}

		// 进行节点的删除操作,解除引用
		pNode.left = null;
		pNode.right = null;
		pNode.data = 0;
		pNode.parent = null;
		pNode = null;
	}

	
	/**
	 * node表示待修正的节点
	 * @param node
	 * @param parent
	 */
	private void deleteFixUp(RedBlackNode node) {
		while (node != root && node.color == COLOR.BLACK) {
			// 节点不是根节点,且节点颜色为黑色
			RedBlackNode qNode = null;
			// RedBackTreeNode<T> pNode = null;
			if (node == node.parent.left) {
				// 节点为左子树节点,取节点的兄弟节点
				qNode = (RedBlackNode) node.parent.right;
				// 若兄弟节点为红色节点
				if (qNode.color == COLOR.RED) {
					// 则将兄弟节点设置成黑色
					qNode.color = COLOR.BLACK;
					// 节点的父节点设置成红色节点
					((RedBlackNode) node.parent).color = COLOR.RED;
					// 已pNode父节点进行左旋操作
					leftRotation(((RedBlackNode) node.parent));					
					// qNode 指向节点父节点的右孩子节点
					qNode = (RedBlackNode) node.parent.right;
				}
				// qNode 节点为红色了后,它的两个孩子节点为黑色,违反红黑树性质
				if (((RedBlackNode) qNode.left).color == COLOR.BLACK
						&& ((RedBlackNode) qNode.right).color == COLOR.BLACK) {
					qNode.color = COLOR.RED;
					// 当前节点指向node节点的父节点
					node = (RedBlackNode) node.parent;
				} else {
					// qNode 节点的右孩子节点为红色
					if (((RedBlackNode) qNode.right).color == COLOR.BLACK) {
						((RedBlackNode) qNode.left).color = COLOR.BLACK;
						qNode.color = COLOR.RED;
						rightRotation(qNode);
						qNode = (RedBlackNode) node.parent.right;
					}
					qNode.color = ((RedBlackNode) node.parent).color;
					((RedBlackNode) node.parent).color = COLOR.BLACK;
					((RedBlackNode)qNode.right).color = COLOR.BLACK;
					// 翻转操作
					leftRotation((RedBlackNode) node.parent);
					node = (RedBlackNode) root;
				}
			} else {
				// 节点为右孩子节点，取节点的兄弟节点
				qNode = (RedBlackNode) node.parent.left;
				if (qNode.color == COLOR.RED) {
					qNode.color = COLOR.BLACK;
					((RedBlackNode) node.parent).color = COLOR.RED;
					rightRotation((RedBlackNode) node.parent);
					qNode = (RedBlackNode) node.parent.left;
				}

				// qNode 节点为红色了后,它的两个孩子节点为黑色,违反红黑树性质
				if (((RedBlackNode) qNode.left).color == COLOR.BLACK
						&& ((RedBlackNode) qNode.right).color == COLOR.BLACK) {
					qNode.color = COLOR.RED;
					// 当前节点指向node节点的父节点
					node = (RedBlackNode) node.parent;
				} else {

					// qNode 节点的右孩子节点为红色
					if (((RedBlackNode) qNode.left).color == COLOR.BLACK) {
						((RedBlackNode) qNode.right).color = COLOR.BLACK;
						qNode.color = COLOR.RED;
						leftRotation(qNode);
						qNode = (RedBlackNode) node.parent.left;
					}
					qNode.color = ((RedBlackNode) node.parent).color;
					((RedBlackNode) node.parent).color = COLOR.BLACK;
					((RedBlackNode) qNode.left).color = COLOR.BLACK;
					// 翻转操作
					rightRotation((RedBlackNode) node.parent);
					node = (RedBlackNode) root;

				}

			}
		}
		node.color = COLOR.BLACK;		
	}

	
	/*
	 * 左旋示意图：对节点y进行右旋
	 *        p                   p
	 *       /                   /
	 *      y                   x
	 *     / \                 / \
	 *    x  ry   ----->      lx  y
	 *   / \                     / \
	 * lx  rx                   rx ry
	 * 右旋做了三件事：
	 * 1. 将x的右子节点赋给y的左子节点,并将y赋给x右子节点的父节点(x右子节点非空时)
	 * 2. 将y的父节点p(非空时)赋给x的父节点，同时更新p的子节点为x(左或右)
	 * 3. 将x的右子节点设为y，将y的父节点设为x
	 */
	private void rightRotation(RedBlackNode y) {
		//1. 将y的左子节点赋给x的右子节点，并将x赋给y左子节点的父节点(y左子节点非空时)
		RedBlackNode x = (RedBlackNode)y.left;
	    y.left = x.right;
	    if(x.right != null){
	        ((RedBlackNode)(x.right)).parent = y;
	    }
	     
	    //2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
	    x.parent = y.parent;
	    if(y.parent == null){
	        this.root = x;//如果y的父节点为空(即y为根节点)，则旋转后将x设为根节点
	    }else{
	        if(y == y.parent.left){//如果y是左子节点
	            y.parent.left = x;//则将x也设置为左子节点
	        }else{
	            y.parent.right = x;//否则将x设置为右子节点
	        }
	    }
	     
	    //3. 将x的左子节点设为y，将y的父节点设为y
	    x.right = y;
	    y.parent = x;
	}
	
	/* 
	 * 左旋示意图：对节点x进行左旋 
	 *     p                       p 
	 *    /                       / 
	 *   x                       y 
	 *  / \                     / \ 
	 * lx  y      ----->       x  ry 
	 *    / \                 / \ 
	 *   ly ry               lx ly 
	 * 左旋做了三件事： 
	 * 1. 将y的左子节点赋给x的右子节点,并将x赋给y左子节点的父节点(y左子节点非空时) 
	 * 2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右) 
	 * 3. 将y的左子节点设为x，将x的父节点设为y 
	 */
	private void leftRotation(RedBlackNode x) {
		 //1. 将y的左子节点赋给x的右子节点，并将x赋给y左子节点的父节点(y左子节点非空时)
		RedBlackNode y = (RedBlackNode)x.right;
	    x.right = y.left;
	    if(y.left != null){
	        ((RedBlackNode)(y.left)).parent = x;
	    }
	     
	    //2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
	    y.parent = x.parent;
	    if(x.parent == null){
	        this.root = y;//如果x的父节点为空(即x为根节点)，则将y设为根节点
	    }else{
	        if(x == x.parent.left){//如果x是左子节点
	            x.parent.left = y;//则也将y设为左子节点 
	        }else{
	            x.parent.right = y;//否则将y设为右子节点 
	        }
	    }
	     
	    //3. 将y的左子节点设为x，将x的父节点设为y
	    y.left = x;
	    x.parent = y;
	}
	
	/**
	 * 判断节点颜色
	 * @param node
	 * @return
	 */
	private boolean isRed(RedBlackNode node) {
		return (node.color == COLOR.RED);
	}
	
	private boolean isBlack(RedBlackNode node) {
		return (node.color == COLOR.BLACK);
	}
	
	/**
	 * 设置节点颜色
	 * @param node
	 */
	private void setBlack(RedBlackNode node) {
		node.color = COLOR.BLACK;
	}
	
	/**
	 * 设置节点颜色
	 * @param node
	 */
	private void setRed(RedBlackNode node) {
		node.color = COLOR.RED;
	}	
	private void setColor(RedBlackNode node, RedBlackTree.COLOR color) {
		if (node != null) {
			node.color = color;
		}
	}
	
	/**
	 * 寻找节点父节点
	 * @param node
	 * @return
	 */
	private RedBlackNode parentOf(RedBlackNode node) {
		return (node.parent);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RedBlackTree rbt = new RedBlackTree();
		
		/**
		 * Red/Black Tree Visualization     https://www.cs.usfca.edu/~galles/visualization/RedBlack.html
		 */
//		rbt.insert(101);
		rbt.insert(32);
		rbt.insert(65);
		rbt.insert(23);
		rbt.insert(56);
		rbt.insert(35);
		
		System.out.print("先序遍历： ");
		rbt.preOrderTraversal(rbt.getRoot());
		System.out.println();
		
		System.out.println("元素：35 的颜色 ： " + ((RedBlackNode)rbt.find(35)).color);
		System.out.println("元素：23 的颜色 ： " + ((RedBlackNode)rbt.find(23)).color);
		System.out.println("元素：65 的颜色 ： " + ((RedBlackNode)rbt.find(65)).color);
		
		System.out.println("最大元素： " + rbt.findMax(rbt.root));
		System.out.println("最小元素： " + rbt.findMin(rbt.root));
		
		System.out.print("删除元素：35 先序遍历： ");       
		rbt.delete(35);
		rbt.preOrderTraversal(rbt.getRoot());          
		System.out.println();
		
		System.out.print("删除元素：32 先序遍历： ");       
		rbt.delete(32);
		rbt.preOrderTraversal(rbt.getRoot());          
		System.out.println();
		System.out.println("元素：23 的颜色 ： " + ((RedBlackNode)rbt.find(23)).color);
		System.out.println("元素：65 的颜色 ： " + ((RedBlackNode)rbt.find(65)).color);
		
		System.out.print("删除元素：56 先序遍历： ");       
		rbt.delete(56);
		rbt.preOrderTraversal(rbt.getRoot());          // 奇怪 删除有两个子节点的节点代码效果和网站上的不同
		System.out.println();
	}

}
