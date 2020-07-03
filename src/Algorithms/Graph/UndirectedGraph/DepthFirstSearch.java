package Algorithms.Graph.UndirectedGraph;

import DataStructure.Graph.UndirectedGraph.UndiGraph;

import java.util.Scanner;

/**
 * 深度优先搜索
 * DFS能够有效处理许多和图有关的任务：
 * 1. 连通性：两个给定的顶点是否连通？
 * 2. 单点路径：从s到v是否存在一条路径？如果有，找出这条路径
 * @author yzz
 * @create 2020-06-29 20:05
 */
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(UndiGraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(UndiGraph G, int v) {
        marked[v] = true;
        count++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    /**
     * w和s是连通的吗
     * @param w
     * @return
     */
    public boolean marked(int w) {
        return marked[w];
    }

    /**
     * 与s连通的顶点总数
     * @return
     */
    public int count() {
        return count;
    }

    /**
     * In:
     * 13
     * 13
     * 0 5
     * 4 3
     * 0 1
     * 9 12
     * 6 4
     * 5 4
     * 0 2
     * 11 12
     * 9 10
     * 0 6
     * 7 8
     * 9 11
     * 5 3
     * Out:
     * 0 1 2 3 4 5 6
     * Not Connected
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UndiGraph graph = new UndiGraph(sc);
        int s = 0;

        DepthFirstSearch DFS = new DepthFirstSearch(graph, s);
        for (int v = 0; v < graph.V(); v++) {
            if (DFS.marked(v)) {
                System.out.print(v + " ");
            }
        }
        System.out.println();

        if (DFS.count() != graph.V()) {
            System.out.println("Not Connected");
        }
    }
}
