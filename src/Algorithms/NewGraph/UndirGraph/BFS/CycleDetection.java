package Algorithms.NewGraph.UndirGraph.BFS;

import DataStructure.NewGraph.UndirGraph.AdjSet;
import DataStructure.NewGraph.UndirGraph.Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 利用BFS检测无向图中的环
 **/
public class CycleDetection {

    private Graph G;
    private boolean[] visited;
    private int[] pre;
    private boolean hasCycle = false;

    public CycleDetection(Graph G){

        this.G = G;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        for(int i = 0; i < G.V(); i ++)
            pre[i] = -1;

        for(int v = 0; v < G.V(); v ++)
            if(!visited[v])
                if(bfs(v)){
                    hasCycle = true;
                    break;
                }
    }

    // 从顶点 v 开始，判断图中是否有环
    private boolean bfs(int s){

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;
        while(!queue.isEmpty()){
            int v = queue.remove();

            for(int w: G.adj(v))
                if(!visited[w]){
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                }
                else if(pre[v] != w)
                    return true;
        }
        return false;
    }

    public boolean hasCycle(){
        return hasCycle;
    }

    public static void main(String[] args){

        String filePath = "E:\\Java\\javaWorkSpace\\DataStructure\\src\\DataStructure\\NewGraph\\g.txt";
        Graph g = new AdjSet(filePath);
        CycleDetection cycleDetection = new CycleDetection(g);
        System.out.println(cycleDetection.hasCycle());

    }
}