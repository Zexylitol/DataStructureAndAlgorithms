package DataStructure.NewGraph.UndirGraph;

/**
 * @author yzz
 * @create 2020-09-13 15:47
 */
public interface Graph {
    public int V();
    public int E();
    public boolean hasEdge(int v, int w);
    public Iterable<Integer> adj(int v);
    public int degree(int v);
}
