package BinaryTree;

import Stack.ArrayStack;

/**
 * 深度优先搜索
 * @author yzz
 *
 */
public class DepthFirstSearch {
	
	/**
	 * 深度优先遍历(stack)
	 * 先序遍历（中 左 右）
	 * @param root_
	 */
	public void DFS(BSTNode root_) {
		ArrayStack<BSTNode> sb = new ArrayStack<BSTNode>(7);
		
		sb.push(root_);
		
		while(!sb.isEmpty()) {
			BSTNode node = sb.peak();
			System.out.print(node.data + " ");
			sb.pop();
			
			if (node.right != null) {
				sb.push(node.right);
			}
			
			if (node.left != null) {
				sb.push(node.left);
			}
			
		}
		System.out.println();
		
	}

	public static void main(String[] args) {
		
		DepthFirstSearch dfs = new DepthFirstSearch();
		
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
		
		dfs.DFS(bst.getRoot());
	}

}
