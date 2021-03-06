package DataStructure.UnionFind;

/**
 * 优化第二版的unionElements方法
 * 基于size的优化
 * @author yzze
 * @create 2020-05-13 20:19
 */
public class UnionFind3 implements UF {
	private int[] parent;
	private int[] sz;         // sz[i]表示以i为根的集合中元素个数

	public UnionFind3(int size) {
		parent = new int[size];
		sz = new int[size];
		for (int i = 0; i < size; i++) {
			parent[i] = i;
			sz[i] = 1;
		}
	}

	@Override
	public int getSize() {
		return parent.length;
	}

	/**
	 * 查找元素p所对应的集合编号
	 * O(h)，h为树的高度
	 * @param p
	 * @return
	 */
	private int find(int p) {

		if (p < 0 || p >= parent.length) {
			throw new IllegalArgumentException("p is out of bound.");
		}

		while (p != parent[p]) {
			p = parent[p];
		}

		return p;
	}

	/**
	 * 元素p和元素q是否属于同一个集合
	 * O(h)
	 * @param p
	 * @param q
	 * @return
	 */
	@Override
	public boolean isConnected(int p, int q) {
		return find(p) == find(q);
	}

	/**
	 * 合并元素pq所属的集合
	 * O(h)
	 * @param p
	 * @param q
	 */
	@Override
	public void unionElements(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);

		if (pRoot == qRoot) {
			return ;
		}

		/*
		 * 让元素个数比较少的根节点指向元素个数比较多的根节点
		 * 避免形成链表
		 */
		if (sz[pRoot] < sz[qRoot]) {
			parent[pRoot] = qRoot;   // pRoot指向qRoot
			sz[qRoot] += sz[pRoot];
		} else {
			parent[qRoot] = pRoot;
			sz[pRoot] += sz[qRoot];
		}
	}
}
