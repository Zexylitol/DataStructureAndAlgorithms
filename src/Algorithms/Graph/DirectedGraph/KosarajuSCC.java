package Algorithms.Graph.DirectedGraph;

import DataStructure.Graph.DirectedGraph.Digraph;

/**
 * 计算强连通分量的Kosaraju算法
 * @author yzz
 * @create 2020-07-04 21:10
 */
public class KosarajuSCC {
    private boolean[] marked;          // 已访问过的顶点
    private int[] id;                  // 强连通分量的标示符
    private int count;                 // 强连通分量的数量

    public KosarajuSCC(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for (int s : order.reversePost()) {
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    // 判断 v 和 w 是否是强连通的
    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    // v 所在的强连通分量的标示符( 0 ~ count()-1 )
    public int id(int v) {
        return id[v];
    }

    // 图中的强连通分量的总数
    public int count() {
        return count;
    }
}
