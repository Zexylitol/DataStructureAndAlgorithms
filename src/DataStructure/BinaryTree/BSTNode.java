package DataStructure.BinaryTree;

public class BSTNode {
	public int data;         // 节点数据
	public BSTNode left;       // 左子树
	public BSTNode right;      // 右子树
	
	public BSTNode(int data_) {
		this.data = data_;
		this.left = null;
		this.right = null;
	}
	
	public BSTNode() {
		this.data = 0;
		this.left = null;
		this.right = null;
	}

	@Override
	public String toString() {
	    String result = data + "";
	    if (left  != null) result = left.toString() + "-" + result;
	    if (right != null) result = result + "-" + right.toString();
	    return result;
//		return "TreeNode [data=" + data + ", left=" + left + ", right=" + right + "]";
	}
	
}
