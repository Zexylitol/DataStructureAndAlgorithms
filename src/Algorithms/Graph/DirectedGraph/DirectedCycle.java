package Algorithms.Graph.DirectedGraph;

import DataStructure.Graph.DirectedGraph.Digraph;

import java.util.Stack;

/**
 * 利用DFS检测有向环
 * @author yzz
 * @create 2020-07-04 16:55
 */
public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;  // 有向环中的所有顶点（如果存在）

    private boolean[] onStack;     // 递归调用的栈上的所有顶点

    public DirectedCycle(Digraph G) {
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (this.hasCycle()) {
                return ;
            } else if(!marked[v]) {
                edgeTo[w] = v;
                dfs(G,w);
            } else if (onStack[w]) {             // 当找到一条边 v->w 且 w 在栈中时，就找到了一个有向环
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
