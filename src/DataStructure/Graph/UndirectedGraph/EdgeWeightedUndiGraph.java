package DataStructure.Graph.UndirectedGraph;

import StdLib.StdRandom;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * 加权无向图的数据类型
 * An edge-weighted undirected graph, implemented using adjacency lists.
 * Parallel edges and self-loops are permitted.
 * @author yzz
 * @create 2020-07-10 16:39
 */
public class EdgeWeightedUndiGraph {

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

    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;                  // 顶点总数
    private int E;                        // 边的总数
    private LinkedList<Edge>[] adj;       // 领接表

    /**
     * Initializes an empty edge-weighted graph with {@code V} vertices and 0 edges.
     *
     * @param  V the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public EdgeWeightedUndiGraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = (LinkedList<Edge>[]) new LinkedList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new LinkedList<Edge>();
        }
    }

    /**
     * Initializes a random edge-weighted graph with {@code V} vertices and <em>E</em> edges.
     *
     * @param  V the number of vertices
     * @param  E the number of edges
     * @throws IllegalArgumentException if {@code V < 0}
     * @throws IllegalArgumentException if {@code E < 0}
     */
    public EdgeWeightedUndiGraph(int V, int E) {
        this(V);
        if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
        for (int i = 0; i < E; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = Math.round(100 * StdRandom.uniform()) / 100.0;
            Edge e = new Edge(v, w, weight);
            addEdge(e);
        }
    }

    /**
     * Initializes an edge-weighted graph from an input stream.
     *
     * @throws IllegalArgumentException if the endpoints of any edge are not in prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is negative
     */
    public EdgeWeightedUndiGraph(List<String> list, String sp) {
        if (list == null) {
            throw new IllegalArgumentException("list is null");
        }
        this.V = Integer.parseInt(list.get(0));
        this.E = Integer.parseInt(list.get(1));
        adj = (LinkedList<Edge>[]) new LinkedList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new LinkedList<>();
        }
        for (int i = 2; i < list.size(); i++) {              // 第二遍 构造图
            String[] a = list.get(i).split(sp);        // 将每一行的顶点和该行的其他顶点相连
            int v = Integer.parseInt(a[0]);
            int w = Integer.parseInt(a[1]);
            validateVertex(v);
            validateVertex(w);
            double weight = Double.parseDouble(a[2]);
            Edge e = new Edge(v, w, weight);
            addEdge(e);
        }
    }


    /**
     * Initializes a new edge-weighted graph that is a deep copy of {@code G}.
     *
     * @param  G the edge-weighted graph to copy
     */
    public EdgeWeightedUndiGraph(EdgeWeightedUndiGraph G) {
        this(G.V());
        this.E = G.E();
        for (int v = 0; v < G.V(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Edge> reverse = new Stack<Edge>();
            for (Edge e : G.adj[v]) {
                reverse.push(e);
            }
            for (Edge e : reverse) {
                adj[v].add(e);
            }
        }
    }


    /**
     * Returns the number of vertices in this edge-weighted graph.
     *
     * @return the number of vertices in this edge-weighted graph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this edge-weighted graph.
     *
     * @return the number of edges in this edge-weighted graph
     */
    public int E() {
        return E;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Adds the undirected edge {@code e} to this edge-weighted graph.
     *
     * @param  e the edge
     * @throws IllegalArgumentException unless both endpoints are between {@code 0} and {@code V-1}
     */
    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    /**
     * Returns the edges incident on vertex {@code v}.
     *
     * @param  v the vertex
     * @return the edges incident on vertex {@code v} as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Edge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the degree of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the degree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * Returns all edges in this edge-weighted graph.
     * To iterate over the edges in this edge-weighted graph, use foreach notation:
     * {@code for (Edge e : G.edges())}.
     *
     * @return all edges in this edge-weighted graph, as an iterable
     */
    public Iterable<Edge> edges() {
        LinkedList<Edge> list = new LinkedList<Edge>();
        for (int v = 0; v < V; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                }
                // add only one copy of each self loop (self loops will be consecutive)
                else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }

    /**
     * Returns a string representation of the edge-weighted graph.
     * This method takes time proportional to <em>E</em> + <em>V</em>.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists of edges
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (Edge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    /**
     * Unit tests the {@code EdgeWeightedUndiGraph} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<String>();             // 保存文件内容

        String filePath = "E:\\Java\\javaWorkSpace\\DataStructure\\src\\DataStructure\\Graph\\UndirectedGraph\\tinyEWG.txt";
        File file = new File(filePath);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
        BufferedReader br = new BufferedReader(reader);
        String line = " ";
        line = br.readLine();
        while(line != null) {
            list.add(line);
            line = br.readLine();
        }

        EdgeWeightedUndiGraph G = new EdgeWeightedUndiGraph(list," ");
        System.out.println(G);
    }

}
