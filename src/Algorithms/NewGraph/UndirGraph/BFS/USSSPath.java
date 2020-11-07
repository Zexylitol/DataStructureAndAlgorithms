package Algorithms.NewGraph.UndirGraph.BFS;

import DataStructure.NewGraph.UndirGraph.AdjSet;
import DataStructure.NewGraph.UndirGraph.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

// Unweighted Single Source Shortest Path
/**
 * 无权图的单源最短路径
 * 无向图中两个顶点之间的最短路径的长度，可以通过广度优先遍历得到；
 **/
public class USSSPath {

    private Graph G;
    private int s;

    private boolean[] visited;
    private int[] pre;
    private int[] dis;                   // dis[t] 表示s->t的最短路径

    public USSSPath(Graph G, int s){

        this.G = G;
        this.s = s;

        visited = new boolean[G.V()];
        pre = new int[G.V()];
        dis = new int[G.V()];
        for(int i = 0; i < G.V(); i ++) {
            pre[i] = -1;
            dis[i] = -1;
        }
        bfs(s);

        for(int i = 0; i < G.V(); i ++)
            System.out.print(dis[i] + " ");
        System.out.println();
    }

    private void bfs(int s){

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;
        dis[s] = 0;
        while(!queue.isEmpty()){
            int v = queue.remove();

            for(int w: G.adj(v))
                if(!visited[w]){
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                    dis[w] = dis[v] + 1;
                }
        }
    }

    public boolean isConnectedTo(int t){
        G.validateVertex(t);
        return visited[t];
    }

	// 返回s->t的最短路径
    public int dis(int t){
        G.validateVertex(t);
        return dis[t];
    }

    public Iterable<Integer> path(int t){

        ArrayList<Integer> res = new ArrayList<Integer>();
        if(!isConnectedTo(t)) return res;

        int cur = t;
        while(cur != s){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);

        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args){

        String filePath = "E:\\Java\\javaWorkSpace\\DataStructure\\src\\DataStructure\\NewGraph\\g.txt";
        Graph g = new AdjSet(filePath);
        USSSPath ussspath = new USSSPath(g, 0);
        System.out.println("0 -> 6 : " + ussspath.path(6));
        System.out.println("0 -> 6 : " + ussspath.dis(6));
    }
}
