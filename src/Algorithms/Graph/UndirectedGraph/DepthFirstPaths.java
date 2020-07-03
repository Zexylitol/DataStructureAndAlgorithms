package Algorithms.Graph.UndirectedGraph;

import DataStructure.Graph.UndirectedGraph.UndiGraph;

import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * 基于深度优先搜索实现路径寻找:在G中找出所有起点为s的路径
 * @author yzz
 * @create 2020-06-29 20:36
 */
public class DepthFirstPaths {
    private boolean[] marked;    // 判断顶点是否调用过dfs()
    private int[] edgeTo;        // 从起点到一个顶点的已知路径上的最后一个顶点
    private final int s;         // 起点

    public DepthFirstPaths(UndiGraph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(UndiGraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;       // edgeTo[w] = v表示v-w是第一次访问w时经过的边
                dfs(G, w);
            }
        }
    }

    /**
     * 是否存在从s到v的路径
     * @param v
     * @return
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * s到v的路径，如果不存在则返回null
     * @param v
     * @return
     */
    public List<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

    /**
     * In:
        6
        8
        0 5
        2 4
        2 3
        1 2
        0 1
        3 4
        3 5
        0 2
     *
     * Out:
        0 to 0: 0
        0 to 1: 0-2-1
        0 to 2: 0-2
        0 to 3: 0-2-3
        0 to 4: 0-2-3-4
        0 to 5: 0-2-3-5
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UndiGraph graph = new UndiGraph(sc);
        int s = 0;

        DepthFirstPaths dfp = new DepthFirstPaths(graph, s);
        for (int v = 0; v < graph.V(); v++) {
            System.out.print(s + " to " + v + ": ");
            if (dfp.hasPathTo(v)) {
                for (int x = dfp.pathTo(v).size()-1; x >= 0; x--) {
                    if (dfp.pathTo(v).get(x) == s) {
                        System.out.print(s);
                    } else {
                        System.out.print("-" + dfp.pathTo(v).get(x));
                    }
                }
//                for (int x : dfp.pathTo(v)) {
//                    if (x == s) {
//                        System.out.print(x);
//                    } else {
//                        System.out.print("-" + x);
//                    }
//                }
            }
            System.out.println();
        }
    }
}
