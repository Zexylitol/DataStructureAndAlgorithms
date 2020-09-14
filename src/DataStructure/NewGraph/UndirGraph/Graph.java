package DataStructure.NewGraph.UndirGraph;

/**
 * @author yzz
 * @create 2020-09-13 15:47
 */
public interface Graph {
    // 判断输入顶点v的合法性[0,V-1]
    public void validateVertex(int v);
    // 顶点数
    public int V();
    // 边数
    public int E();
    // 顶点v和w之间是否存在边
    public boolean hasEdge(int v, int w);
    // 返回和v相邻的顶点
    public Iterable<Integer> adj(int v);
    // 返回顶点v的度
    public int degree(int v);
}
