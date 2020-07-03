package Algorithms.Graph.UndirectedGraph;

import DataStructure.Graph.UndirectedGraph.UndiGraph;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 使用深度优先搜索找出图中的所有连通分量(Connected Component)
 * @author yzz
 * @create 2020-07-02 19:48
 */
public class ConnectedComponent {
    private boolean[] marked;
    private int[] id;                 // 如果v属于第i个连通分量，则id[v]的值为i
    private int count;

    public ConnectedComponent(UndiGraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {           // 找出一个未被标记的顶点并调用递归函数dfs()来标记并区分出所有和它连通的顶点
                dfs(G, s);
                count++;
            }
        }
    }
    private void dfs(UndiGraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for(int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    /**
     * 判断v和w是否连通
     * @param v
     * @param w
     * @return
     */
    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    /**
     * v所在的连通分量的标示符( 0~count()-1 )
     * @param v
     * @return
     */
    public int id(int v) {
        return id[v];
    }

    /**
     * 连通分量数
     * @return
     */
    public int count() {
        return count;
    }

    /**
     * 测试连通分量
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
     *
     * Out:
     * 3 components
     * 0 1 2 3 4 5 6
     * 7 8
     * 9 10 11 12
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UndiGraph G = new UndiGraph(sc);

        ConnectedComponent cc = new ConnectedComponent(G);

        int M = cc.count();
        System.out.println(M + " components");

        List<Integer>[] components = new LinkedList[M];

        for(int i = 0; i < M; i++) {
            components[i] = new LinkedList<>();
        }

        for (int v = 0; v < G.V(); v++) {
            components[cc.id(v)].add(v);
        }

        for (int i = 0; i < M; i++) {
            for (int v : components[i]) {
                System.out.print(v + " ");
            }
            System.out.println();
        }

    }
}
