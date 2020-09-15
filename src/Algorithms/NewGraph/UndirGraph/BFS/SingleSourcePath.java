package Algorithms.NewGraph.UndirGraph.BFS;

import DataStructure.NewGraph.UndirGraph.AdjSet;
import DataStructure.NewGraph.UndirGraph.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 利用BFS实现单源路径: 固定某一顶点s到其他顶点的路径
 */
public class SingleSourcePath {

    private Graph G;
    private int s;                      // 顶点s到所有点的路径

    private boolean[] visited;
    private int[] pre;                  // 存储每一个顶点的前一个顶点

    public SingleSourcePath(Graph G, int s){

        this.G = G;
        this.s = s;

        visited = new boolean[G.V()];
        pre = new int[G.V()];
        for(int i = 0; i < pre.length; i ++)
            pre[i] = -1;

        bfs(s);
    }

    private void bfs(int s){

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
        }
    }

	// 如果visited[t]为true说明顶点t被遍历到，存在s->t的路径
    public boolean isConnectedTo(int t){
        G.validateVertex(t);
        return visited[t];
    }

	// 返回s->t的路径
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
        SingleSourcePath sspath = new SingleSourcePath(g, 0);
        System.out.println("0 -> 6 : " + sspath.path(6));
    }
}
