package Algorithms.Graph;

import DataStructure.Graph.UndirectedGraph.AdjListUndirGraph;

/**
 * 使用深度优先搜索解决双色问题
 * 双色问题：能够用两种颜色将图的所有顶点着色，使得任意一条边的
 *         两个端点的颜色都不相同。等价于这是一幅二分图(bipartite graph)
 * @author yzz
 * @create 2020-07-02 20:45
 */
public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = true;

    public TwoColor(AdjListUndirGraph G) {
        marked = new boolean[G.V()];
        color = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, s);
            }
        }
    }

    private void dfs(AdjListUndirGraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                color[w] = !color[v];
                dfs(G, w);
            } else if (color[w] == color[v]) {
                isTwoColorable = false;
            }
        }
    }

    public boolean isBipartite() {
        return isTwoColorable;
    }

}
