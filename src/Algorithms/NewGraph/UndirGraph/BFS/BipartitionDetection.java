package Algorithms.NewGraph.UndirGraph.BFS;

import DataStructure.NewGraph.UndirGraph.AdjSet;
import DataStructure.NewGraph.UndirGraph.Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 利用BFS实现二分图检测
 */
public class BipartitionDetection {

    private Graph G;

    private boolean[] visited;
    private int[] colors;
    private boolean isBipartite = true;

    public BipartitionDetection(Graph G){

        this.G = G;
        visited = new boolean[G.V()];
        colors = new int[G.V()];
        for(int i = 0; i < G.V(); i ++)
            colors[i] = -1;

        for(int v = 0; v < G.V(); v ++)
            if(!visited[v])
                if(!bfs(v)){
                    isBipartite = false;
                    break;
                }
    }

    private boolean bfs(int s){

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        colors[s] = 0;

        while(!queue.isEmpty()){
            int v = queue.remove();

            for(int w: G.adj(v))
                if(!visited[w]){
                    queue.add(w);
                    visited[w] = true;
                    colors[w] = 1 - colors[v];
                }
                else if(colors[v] == colors[w])
                    return false;
        }
        return true;
    }

    public boolean isBipartite(){
        return isBipartite;
    }

    public static void main(String[] args){

        String filePath = "E:\\Java\\javaWorkSpace\\DataStructure\\src\\DataStructure\\NewGraph\\g.txt";
        Graph g = new AdjSet(filePath);
        BipartitionDetection bipartitionDetection = new BipartitionDetection(g);
        System.out.println(bipartitionDetection.isBipartite);


        String filePath2 = "E:\\Java\\javaWorkSpace\\DataStructure\\src\\Algorithms\\NewGraph\\g2.txt";
        Graph g2 = new AdjSet(filePath2);
        BipartitionDetection bipartitionDetection2 = new BipartitionDetection(g2);
        System.out.println(bipartitionDetection2.isBipartite);


        String filePath3 = "E:\\Java\\javaWorkSpace\\DataStructure\\src\\Algorithms\\NewGraph\\g3.txt";
        Graph g3 = new AdjSet(filePath3);
        BipartitionDetection bipartitionDetection3 = new BipartitionDetection(g3);
        System.out.println(bipartitionDetection3.isBipartite);

    }
}