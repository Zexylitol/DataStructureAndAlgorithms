package LeetCode;

public class LeetCode990 {
    private class UnionFind {
        private int[] parent;
        private int[] rank;         // rank[i]表示以i为根的集合所表示的树的层数
        // 不反应高度/深度

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int getSize() {
            return parent.length;
        }

        private int find(int p) {

            if (p < 0 || p >= parent.length) {
                throw new IllegalArgumentException("p is out of bound.");
            }

            while (p != parent[p]) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        public boolean isConnected(int p, int q) {
            return find(p) == find(q);
        }

        public void unionElements(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);

            if (pRoot == qRoot) {
                return ;
            }


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

    public boolean equationsPossible(String[] equations) {
        // 26个英文字母
        UnionFind uf = new UnionFind(26);

        // 先让相等的字母形成连通分量
        for (String eq : equations) {
            if (eq.charAt(1) == '=') {
                char x = eq.charAt(0);
                char y = eq.charAt(3);
                uf.unionElements(x - 'a', y - 'a');
            }
        }

        // 检查不等关系是否打破相等关系的连通性
        for (String eq : equations) {
            if (eq.charAt(1) == '!') {
                char x = eq.charAt(0);
                char y = eq.charAt(3);
                if (uf.isConnected(x- 'a', y - 'a'))   return false;
            }
        }
        return true;
    }
}
