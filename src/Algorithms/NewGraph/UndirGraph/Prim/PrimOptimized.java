package Algorithms.NewGraph.UndirGraph.Prim;

import Algorithms.NewGraph.UndirGraph.Kruskal.CC;
import DataStructure.NewGraph.UndirGraph.WeightedGraph.WeightedEdge;
import DataStructure.NewGraph.UndirGraph.WeightedGraph.WeightedGraph;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Prim算法的优化：利用优先队列(最小堆)
 * 时间复杂度 O(ElogE)
 */
public class PrimOptimized {

    private WeightedGraph G;
    private ArrayList<WeightedEdge> mst;

    public PrimOptimized(WeightedGraph G){

        this.G = G;
        mst = new ArrayList<>();

        CC cc = new CC(G);
        if(cc.count() > 1) return;

        boolean visited[] = new boolean[G.V()];
        visited[0] = true;
		
        Queue pq = new PriorityQueue<WeightedEdge>();
        for(int w: G.adj(0))
            pq.add(new WeightedEdge(0, w, G.getWeight(0, w)));

        while(!pq.isEmpty()){

            WeightedEdge minEdge = (WeightedEdge) pq.remove();
            if(visited[minEdge.getV()] && visited[minEdge.getW()])
                continue;

            mst.add(minEdge);

            int newv = visited[minEdge.getV()] ? minEdge.getW() : minEdge.getV();
            visited[newv] = true;
            for(int w: G.adj(newv))
                if(!visited[w])
                    pq.add(new WeightedEdge(newv, w, G.getWeight(newv, w)));
        }
    }

    public ArrayList<WeightedEdge> result(){
        return mst;
    }

    public static void main(String[] args){
        String filePath = "E:\\Java\\javaWorkSpace\\DataStructure\\src\\Algorithms\\NewGraph\\g4.txt";
        WeightedGraph g = new WeightedGraph(filePath);
        PrimOptimized prim = new PrimOptimized(g);
        System.out.println(prim.result());
    }
}
