package Algorithms.Graph.DirectedGraph;

import DataStructure.Graph.DirectedGraph.Digraph;

/**
 * 顶点对可达性
 * 不适用于大型有向图，因为构造函数所需的空间和V^2成正比，所需的时间和V(V+E)成正比
 * @author yzz
 * @create 2020-07-05 15:30
 */
public class TransitiveClosure {
    private DirectedDFS[] all;
    TransitiveClosure(Digraph G) {
        all = new DirectedDFS[G.V()];
        for (int v = 0; v < G.V(); v++) {
            all[v] = new DirectedDFS(G, v);
        }
    }

    // w 是从 v 可达的吗？
    boolean reachable(int v, int w) {
        return all[v].marked(w);
    }
}
