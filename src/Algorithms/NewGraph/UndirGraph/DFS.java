package Algorithms.NewGraph.UndirGraph;

import DataStructure.NewGraph.UndirGraph.AdjList;
import DataStructure.NewGraph.UndirGraph.AdjMatrix;
import DataStructure.NewGraph.UndirGraph.AdjSet;
import DataStructure.NewGraph.UndirGraph.Graph;

import java.util.ArrayList;

/**
 *  图的深度优先遍历
 *	复杂度：O(V + E)
 *  应用：
 *		1. 求图的连通分量
 *		2. 求两点间是否可达
 *		3. 求两点间的一条路径
 *		4. 检测图中是否有环
 *		5. 二分图检测
 *		6. 寻找图中的桥
 *		7. 寻找图中的割点
 *		8. 哈密尔顿路径
 *		9. 拓扑排序
 **/
public class DFS {

    private Graph G;
    private boolean[] visited;

    private ArrayList<Integer> pre = new ArrayList<>();
    private ArrayList<Integer> post = new ArrayList<>();

    public DFS(Graph G){

        this.G = G;
        visited = new boolean[G.V()];
        for(int v = 0; v < G.V(); v ++)
            if(!visited[v])
                dfs(v);
    }

    private void dfs(int v){

        visited[v] = true;
        pre.add(v);
        for(int w: G.adj(v))
            if(!visited[w])
                dfs(w);
        post.add(v);
    }
    // 返回DFS遍历结果 : 先序遍历
    public Iterable<Integer> pre(){
        return pre;
    }

    // 返回DFS遍历结果 : 后序遍历
    public Iterable<Integer> post(){
        return post;
    }

    public static void main(String[] args){
        String filePath = "E:\\Java\\javaWorkSpace\\DataStructure\\src\\DataStructure\\NewGraph\\g.txt";
        Graph g1 = new AdjSet(filePath);
        DFS DFS1 = new DFS(g1);
        System.out.println("DFS preOrder : " + DFS1.pre());
        System.out.println("DFS postOrder : " + DFS1.post());
        System.out.println();

        Graph g2 = new AdjList(filePath);
        DFS DFS2 = new DFS(g2);
        System.out.println("DFS preOrder : " + DFS2.pre());
        System.out.println("DFS postOrder : " + DFS2.post());
        System.out.println();

        Graph g3 = new AdjMatrix(filePath);
        DFS DFS3 = new DFS(g3);
        System.out.println("DFS preOrder : " + DFS3.pre());
        System.out.println("DFS postOrder : " + DFS3.post());
        System.out.println();
    }
}
