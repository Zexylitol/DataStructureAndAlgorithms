package Algorithms.Graph;

import DataStructure.Graph.UndirectedGraph.AdjListUndirGraph;

import java.util.*;

/**
 * 使用广度优先搜索查找图中的路径
 * 单点最短路径：找到从s到顶点v的最短路径
 * @author yzz
 * @create 2020-07-01 21:38
 */
public class BreadthFirstPaths {
    private boolean[] marked;       // 到达该顶点的最短路径已知吗？
    private int[] edgeTo;           // 到达该顶点的已知路径上的最后一个顶点
    private final int s;            // 起点

    public BreadthFirstPaths(AdjListUndirGraph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    private void bfs(AdjListUndirGraph G, int s) {
        Queue<Integer> queue = new LinkedList<>();
        marked[s] = true;             // 标记起点
        queue.offer(s);               // 加入队列
        while(!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : G.adj(v)) {
                if (!marked[w]) {    // 对于每个未被标记的相邻顶点
                    edgeTo[w] = v;   // 保存最短路径的最后一条边
                    marked[w] = true;  // 标记此顶点，因为最短路径已知
                    queue.offer(w);    // 并将其添加到队列中
                }
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
     * s到v的最短路径，如果不存在则返回null
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
     * 6
     * 8
     * 0 5
     * 2 4
     * 2 3
     * 1 2
     * 0 1
     * 3 4
     * 3 5
     * 0 2
     *
     * Out:
     * 0 to 0: 0
     * 0 to 1: 0-1
     * 0 to 2: 0-2
     * 0 to 3: 0-2-3
     * 0 to 4: 0-2-4
     * 0 to 5: 0-5
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AdjListUndirGraph graph = new AdjListUndirGraph(sc);
        int s = 0;

        BreadthFirstPaths bfp = new BreadthFirstPaths(graph,s);
        for (int v = 0; v < graph.V(); v++) {
            System.out.print(s + " to " + v + ": ");
            if (bfp.hasPathTo(v)) {
                for (int x = bfp.pathTo(v).size()-1; x >= 0; x--) {
                    if (bfp.pathTo(v).get(x) == s) {
                        System.out.print(s);
                    } else {
                        System.out.print("-" + bfp.pathTo(v).get(x));
                    }
                }
            }
            System.out.println();
        }
    }

}
