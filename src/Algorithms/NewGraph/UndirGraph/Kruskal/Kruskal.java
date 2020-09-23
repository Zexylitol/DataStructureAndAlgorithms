package Algorithms.NewGraph.UndirGraph.Kruskal;

import DataStructure.NewGraph.UndirGraph.WeightedGraph.WeightedEdge;
import DataStructure.NewGraph.UndirGraph.WeightedGraph.WeightedGraph;
import DataStructure.UnionFind.UF;
import DataStructure.UnionFind.UnionFind1;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 求最小生成树
 * 生成子图：如果图G的一个子图包含G的所有顶点。
 * 图G的一个生成子图T如果是树，称为G的一颗生成树。
 * 连通赋权图中权值最小的生成树(生成树中所有边权值的和)称为最小生成树(minimum spanning tree)。
 *
 * 克鲁斯克 (Kruskal)算法：
 *  （1）选择边e1, 使得其权值最小;
 *  （2）若已经选定边e1,  e2, …,  ek,  则从E –{e1,  e2, …,  ek }中选择边e(k+1), 使得：
 *      a)  G[e1,  e2, …,  e(k+1)]为无圈图;
 *      b)  e(k+1)的权值w(e(k+1))尽可能小
 *  （3）当(2)不能进行时, 停止
 *
 *  时间复杂度：O(ElogE)
 */
public class Kruskal {

    private WeightedGraph G;
    private ArrayList<WeightedEdge> mst;        // 最小生成树结果 带权边

    public Kruskal(WeightedGraph G){

        this.G = G;
        mst = new ArrayList<>();

        CC cc = new CC(G);
        if(cc.count() > 1) return;             // 非连通图

        ArrayList<WeightedEdge> edges = new ArrayList<>();
        for(int v = 0; v < G.V(); v ++)
            for(int w: G.adj(v))
                if(v < w)                    // 避免重复添加
                    edges.add(new WeightedEdge(v, w, G.getWeight(v, w)));

        Collections.sort(edges);             // 边按权值排序

        // 按照从小到大的顺序，依次从edges中取出边，检查取出的边是否和已经取出的边形成环，若无环，则这条边就属于最小生成树
        UF uf = new UnionFind1(G.V());
        for(WeightedEdge edge: edges){
            int v = edge.getV();
            int w = edge.getW();
            if(!uf.isConnected(v, w)){
                mst.add(edge);
                uf.unionElements(v, w);
            }
        }
    }

    public ArrayList<WeightedEdge> result(){
        return mst;
    }

    public static void main(String[] args){

        String filePath = "E:\\Java\\javaWorkSpace\\DataStructure\\src\\Algorithms\\NewGraph\\g4.txt";
        WeightedGraph g = new WeightedGraph(filePath);
        Kruskal kruskal = new Kruskal(g);
        System.out.println(kruskal.result());
    }
}
