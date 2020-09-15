package Algorithms.NewGraph.UndirGraph.BFS;

import DataStructure.NewGraph.UndirGraph.AdjSet;
import DataStructure.NewGraph.UndirGraph.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 图的广度优先遍历
 * 时间复杂度： O(V + E)
 **/
public class BFS {

    private Graph G;
    private boolean[] visited;

    private ArrayList<Integer> order = new ArrayList<>();

    public BFS(Graph G){

        this.G = G;
        visited = new boolean[G.V()];
		// 遍历所有的连通分量
        for(int v = 0; v < G.V(); v ++)
            if(!visited[v])
                bfs(v);
    }

    private void bfs(int s){

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        while(!queue.isEmpty()){
            int v = queue.remove();
            order.add(v);

            for(int w: G.adj(v))
                if(!visited[w]){
                    queue.add(w);
                    visited[w] = true;
                }
        }
    }

    public Iterable<Integer> order(){
        return order;
    }

    public static void main(String[] args){

        String filePath = "E:\\Java\\javaWorkSpace\\DataStructure\\src\\DataStructure\\NewGraph\\g.txt";
        Graph g = new AdjSet(filePath);
        BFS graphBFS = new BFS(g);
        System.out.println("BFS Order : " + graphBFS.order());
    }
}
