package Algorithms.Graph;

import DataStructure.Graph.UndirectedGraph.AdjListUndirGraph;

/**
 * 使用深度优先搜索检验G是否是无环图(假设不存在自环或平行边)
 * @author yzz
 * @create 2020-07-02 20:32
 */
public class Cycle {
    private boolean[] marked;
    private boolean hasCycle;
    public Cycle(AdjListUndirGraph G) {
        marked = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G,s,s);
            }
        }
    }

    private void dfs(AdjListUndirGraph G, int v, int u) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G,w,v);
            } else if (w != u) {          //已经访问过w节点，且不是其父节点，说明有环
                hasCycle = true;
            }
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }
}
