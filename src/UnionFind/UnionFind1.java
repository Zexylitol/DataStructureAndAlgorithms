package UnionFind;

/**
 * 第一版UnionFind
 * @author yzze
 * @create 2020-05-13 17:11
 */
public class UnionFind1 implements UF{
    private int[] id;                                       // 节点x的父节点是id[x]
    public UnionFind1(int size) {
        id = new int[size];

        for (int i = 0; i < id.length; i++) {
            id[i] = i;  // 此时 每个元素都所属不同的集合
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }

    /**
     * 查找元素p所对应的集合编号
     * O(1)
     * @param p
     * @return
     */
    private int find(int p) {
        if (p < 0 || p >= id.length) {
            throw new IllegalArgumentException("p is out of bound.");
        }
        return id[p];
    }

    /**
     * 元素p和元素q是否属于同一个集合
     * O(1)
     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p)==find(q);
    }

    /**
     * 合并元素pq所属的集合
     * O(n)
     * @param p
     * @param q
     */
    @Override
    public void unionElements(int p, int q) {
        int pId = find(p);
        int qId = find(q);

        if (pId == qId) {
            return ;
        }

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) {
                id[i] = qId;
            }
        }
    }
}
