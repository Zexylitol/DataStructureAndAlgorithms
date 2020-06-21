package DataStructure.BinaryTree;

import DataStructure.Queue.ArrayLoopQueue;

/**
 * 利用队列实现二叉树的广度优先搜索(BFS)
 * @author yzz
 *
 */
public class BreadthFirstSearch {
	
	/**
	 * 广度优先搜索（层序遍历）
	 * 
	 * @param node
	 */
	public void BFS(BSTNode root_) {
		if (root_ == null) {
			return;
		}
		
		// 创建循环队列
		ArrayLoopQueue<BSTNode> lq = new ArrayLoopQueue<BSTNode>(7);
		
		lq.enQueue(root_);
		
		System.out.print("广度优先搜索： ");
		while (!lq.isEmpty()) {
			BSTNode node = lq.getFront();
			
			System.out.print(node.data + " ");
			
			if (node.left != null) {
				lq.enQueue(node.left);
			}
			
			if (node.right != null) {
				lq.enQueue(node.right);
			}
			
			lq.deQueue();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		
		BreadthFirstSearch bfs = new BreadthFirstSearch();
		
		// 创建二叉搜索树
		BinarySearchTree bst = new BinarySearchTree();
		
		/*
		 * 生成树：
		 *             40
		 *         35      50
		 *       32  36  45   60
		 */
		bst.insert(40);
		bst.insert(35);
		bst.insert(32);
		bst.insert(50);
		bst.insert(45);
		bst.insert(60);
		bst.insert(36);
		
		bfs.BFS(bst.root);
		
	}

}
