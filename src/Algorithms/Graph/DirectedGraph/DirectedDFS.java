package Algorithms.Graph.DirectedGraph;

import DataStructure.Graph.DirectedGraph.Digraph;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 有向图中的可达性：是否存在一条从s到达给定顶点v的有向路径
 * 多点可达性：是否存在一条从集合中的任意顶点到达给定顶点v的有向路径
 * 多点可达性的一个重要的实际应用是在典型的内存管理系统中，包括许多Java的实现。
 * 在一幅有向图中，一个顶点表示一个对象，一条边则表示一个对象对另 一个对象的引用。
 * 这个模型很好地表现了运行中的Java程序的内存使用状况。在程序执行的任何时候都有某些对象是可以被直接访问的，
 * 而不能通过这些对象访问到的所有对象都应该被回收以便释放内存。标记-清除的垃圾回收策略会为每个对象保留一个位做垃圾收集之用。
 * 它会周期性地运行一个类似于DirectedDFS的有向图可达性算法来标记所有可以被访问到的对象，然后清理所有对象，回收没有被标记的对象，以腾出内存供新的对象使用。
 * @author yzz
 * @create 2020-07-03 20:31
 */
public class DirectedDFS {
    private boolean[] marked;

    // 从G中找到从s可达的所有顶点
    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    // 在G中找到从sources中的所有顶点可达的所有顶点
    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        for (int s : sources) {
            if (!marked[s]) {
                dfs(G, s);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    // v是否可达
    public boolean marked(int v) {
        return marked[v];
    }

    /*
     * In:
13
22
4 2
2 3
3 2
6 0
0 1
2 0
11 12
12 9
9 10
9 11
8 9
10 12
11 4
4 3
3 5
7 8
8 7
5 4
0 5
6 4
6 9
7 6
       Out:
        0 1 2 3 4 5
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Digraph G= new Digraph(sc);

        List<Integer> sources = new LinkedList<>();
        for (int i = 1; i < 4;i++) {
            sources.add(i);
        }
        // 能够判断从给定的一个或者一组顶点能到达哪些其他顶点
        DirectedDFS reachable = new DirectedDFS(G, sources);
        for (int v = 0; v < G.V(); v++) {
            if (reachable.marked(v)) {
                System.out.print(v + " ");
            }
        }
        System.out.println();
    }
}
