package Algorithms.NewGraph.UndirGraph;

import DataStructure.NewGraph.UndirGraph.AdjSet;
import DataStructure.NewGraph.UndirGraph.Graph;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 单源路径: 固定某一顶点s到其他顶点的路径
 **/
public class SingleSourcePath {

    private Graph G;
    private int s;     // 顶点s到所有点的路径(不一定是最短路径)

    private boolean[] visited;
    private int[] pre;  // 存储每一个顶点的前一个顶点

    public SingleSourcePath(Graph G, int s){

        G.validateVertex(s);

        this.G = G;
        this.s = s;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
		for (int i = 0; i < pre.length; i++) {
			pre[i] = -1;
		}
        dfs(s, s);
    }

    private void dfs(int v, int parent){

        visited[v] = true;
        pre[v] = parent;
        for(int w: G.adj(v))
            if(!visited[w])
                dfs(w, v);
    }

	// 顶点s和顶点t是否可达
    public boolean isConnectedTo(int t){
        G.validateVertex(t);
        return visited[t];
    }

	// 顶点s到顶点t的路径
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
        System.out.println("0 -> 5 : " + sspath.path(5));
    }
}
