package UnionFind;

/**
 * 路径压缩
 * 利用递归 优化find方法
 * @author yzze
 * @create 2020-05-13 21:08
 */
public class UnionFind6 implements UF{
    private int[] parent;
    private int[] rank;         // rank[i]表示以i为根的集合所表示的树的层数
    // 不反应高度/深度

    public UnionFind6(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
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

        if (p != parent[p]) {
            parent[p] = find(parent[p]);
        }
        return parent[p];
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
         * 根据两个元素所在树的rank不同判断合并方向
         * 将rank低的集合合并到rank高的集合上
         * 并不实际反应节点的深度/高度值
         */
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;   // pRoot指向qRoot

        } else if (rank[qRoot] < rank[pRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }
}
