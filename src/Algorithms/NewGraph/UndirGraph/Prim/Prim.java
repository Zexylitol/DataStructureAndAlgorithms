package Algorithms.NewGraph.UndirGraph.Prim;

import Algorithms.NewGraph.UndirGraph.Kruskal.CC;
import DataStructure.NewGraph.UndirGraph.WeightedGraph.WeightedEdge;
import DataStructure.NewGraph.UndirGraph.WeightedGraph.WeightedGraph;

import java.util.ArrayList;

/**
 * 求最小生成树
 * Prim算法：
 *       1. 对于连通赋权图G的任意一个顶点u, 选择与点u关联的且权值最小的边作为最小生成树的第一条边e1;
 *       2. 在接下来的边e2, e3, …, e(n-1) , 在与一条已经选取的边只有一个公共端点的所有边中, 选取权值最小的边.
 *
 * 时间复杂度 O((V-1)*(V+E)) = O(VE)
 */
public class Prim {

    private WeightedGraph G;
    private ArrayList<WeightedEdge> mst;    // 最小生成树的带权边

    public Prim(WeightedGraph G){

        this.G = G;
        mst = new ArrayList<>();

        CC cc = new CC(G);
        if(cc.count() > 1) return;         // 非连通图

		// Prim
        boolean[] visited = new boolean[G.V()];
        visited[0] = true;
        for(int i = 1; i < G.V(); i ++){

            WeightedEdge minEdge = new WeightedEdge(-1, -1, Integer.MAX_VALUE);
            for(int v = 0; v < G.V(); v ++)
                if(visited[v])
                    for(int w: G.adj(v))
                        if(!visited[w] && G.getWeight(v, w) < minEdge.getWeight())
                            minEdge = new WeightedEdge(v, w, G.getWeight(v, w));
            mst.add(minEdge);
            visited[minEdge.getV()] = true;
            visited[minEdge.getW()] = true;
        }
    }

	// Prim算法结果
    public ArrayList<WeightedEdge> result(){
        return mst;
    }

    public static void main(String[] args){
        String filePath = "E:\\Java\\javaWorkSpace\\DataStructure\\src\\Algorithms\\NewGraph\\g4.txt";
        WeightedGraph g = new WeightedGraph(filePath);
        Prim prim = new Prim(g);
        System.out.println(prim.result());
    }
}
