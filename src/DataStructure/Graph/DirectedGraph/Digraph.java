package DataStructure.Graph.DirectedGraph;

import java.util.Iterator;
import java.util.Scanner;

/**
 * 利用邻接表(Adjacency List)存储有向图
 * @author yzz
 * @create 2020-07-03 20:09
 */
public class Digraph {

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

    private final int V;
    private int E;
    private LinkedList<Integer>[] adj;

    /**
     * 创建一幅含有V个顶点但没有边的有向图
     * @param V
     */
    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (LinkedList<Integer>[]) new LinkedList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new LinkedList<>();
        }
    }

    // 读取一幅有向图
    public Digraph(Scanner sc) {

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

    // 顶点总数
    public int V() {
        return V;
    }

    // 边的总数
    public int E() {
        return E;
    }

    // 向有向图中添加一条边 v->w
    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    // 由 v 指出的边所连接的所有顶点
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    // 该图的反向图
    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                R.addEdge(w, v);
            }
        }
        return R;
    }

    // 图的领接表的字符串表示
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



}
