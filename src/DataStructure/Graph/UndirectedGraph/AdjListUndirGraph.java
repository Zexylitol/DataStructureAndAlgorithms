package DataStructure.Graph.UndirectedGraph;

import java.util.Iterator;
import java.util.Scanner;

/**
 * 利用邻接表(Adjacency List)存储无向图
 * @author yzz
 * @create 2020-06-28 20:47
 */
public class AdjListUndirGraph {

    private class LinkedList<E> implements Iterable<E> {

        private class Node{
            E item;
            Node next;
        }
        private Node first;                // 链表的首节点
        private int N;                     // 元素数量

        public void add(E item) {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            N++;
        }

        public boolean isEmpty() {
            return first == null;
        }
        public int size() {
            return N;
        }

        public Iterator<E> iterator() {
            return new ListIterator();
        }
        private class ListIterator implements Iterator<E> {
            private Node current = first;
            public boolean hasNext() {
                return current != null;
            }
            public void remove() {}
            public E next() {
                E item = current.item;
                current = current.next;
                return item;
            }
        }

    }

    private final int V;                // 定点数目
    private int E;                      // 边的数目
    private LinkedList<Integer>[] adj;  // 邻接表

    public AdjListUndirGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (LinkedList<Integer>[]) new LinkedList[V];   // 创建邻接表
        for (int v= 0; v < V; v++) {
            adj[v] = new LinkedList<Integer>();     // 将所有链表初始化为空
        }
    }
    public AdjListUndirGraph(Scanner sc) {

        this(sc.nextInt());         // 读取V并将图初始化
        int e = sc.nextInt();       // 读取E

        for (int i = 0; i < e; i++) {
            // 添加一条边
            int v = sc.nextInt();                  // 读取一个顶点
            int w = sc.nextInt();                  // 读取另一个顶点

            addEdge(v,w);           // 添加一条连接它们的边
        }
        sc.close();
    }

    public int V() {
        return V;
    }
    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);           // 将w添加到v的链表中
        adj[w].add(v);           // 将v添加到w的链表中  每条边都会出现两次
        E++;
    }
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * 图的领接表的字符串表示
     * @return
     */
    public String toString() {
        String s = V + " vertices, " + E + " edges\n";
        for (int v = 0; v < V; v++) {
            s += v + ": ";
            for (int w : this.adj(v)) {
                s += w + " ";
            }
            s += "\n";
        }
        return s;
    }

    /**
     * 计算自环的个数
     * @param G
     * @return
     */
    public static int numOfSelfLoops(AdjListUndirGraph G) {
        int count = 0;
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (v == w) {
                    count++;
                }
            }
        }
        return count / 2;        // 每条边都被记过两次
    }

    /**
     * 计算v的度数
     * @param G
     * @param v
     * @return
     */
    public static int degree(AdjListUndirGraph G, int v) {
        int degree = 0;
        for (int w : G.adj(v)) {
            degree ++;
        }
        return degree;
    }

    /**
     * 计算所有顶点的最大度数
     * @param G
     * @return
     */
    public static int maxDegree(AdjListUndirGraph G) {
        int max = 0;
        for (int v = 0; v < G.V(); v++) {
            if (degree(G, v) > max) {
                max = degree(G, v);
            }
        }
        return max;
    }

    /**
     * 计算所有顶点的平均度数
     * @param G
     * @return
     */
    public static double avgDegree(AdjListUndirGraph G) {
        return 2 * G.V() / G.V();
    }

    public static void main(String[] args) {
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
         * 13 vertices, 13 edges
         * 0: 6 2 1 5
         * 1: 0
         * 2: 0
         * 3: 5 4
         * 4: 5 6 3
         * 5: 3 4 0
         * 6: 0 4
         * 7: 8
         * 8: 7
         * 9: 11 10 12
         * 10: 9
         * 11: 9 12
         * 12: 11 9
         */
        Scanner sc = new Scanner(System.in);
        AdjListUndirGraph graph = new AdjListUndirGraph(sc);
        System.out.println(graph.toString());
    }

}
