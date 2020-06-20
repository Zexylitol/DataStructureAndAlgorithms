package DataStructure.BinaryTree;

import DataStructure.BinaryTree.RedBlackTree.COLOR;

public class RedBlackNode extends BSTNode {
	
	/*
	 * 树节点颜色
	 */
	public RedBlackTree.COLOR color = COLOR.RED;      // 插入节点总是红色的
	public RedBlackNode parent;
	
	
	
}
