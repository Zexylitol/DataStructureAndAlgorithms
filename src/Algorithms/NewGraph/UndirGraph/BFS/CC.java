package Algorithms.NewGraph.UndirGraph.BFS;

import DataStructure.NewGraph.UndirGraph.AdjSet;
import DataStructure.NewGraph.UndirGraph.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


/**
 * 利用BFS求无向图的连通分量(Connected Component)个数
 **/
public class CC {

    private Graph G;
    private int[] visited;
    private int cccount = 0;

    public CC(Graph G){

        this.G = G;
        visited = new int[G.V()];
        for(int i = 0; i < visited.length; i ++)
            visited[i] = -1;

        for(int v = 0; v < G.V(); v ++)
            if(visited[v] == -1){
                bfs(v, cccount);
                cccount ++;
            }
    }

    private void bfs(int s, int ccid){

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = ccid;
        while(!queue.isEmpty()){
            int v = queue.remove();

            for(int w: G.adj(v))
                if(visited[w] == -1){
                    queue.add(w);
                    visited[w] = ccid;
                }
        }
    }

    public int count(){
        return cccount;
    }

    public boolean isConnected(int v, int w){
        G.validateVertex(v);
        G.validateVertex(w);
        return visited[v] == visited[w];
    }

    public ArrayList<Integer>[] components(){

        ArrayList<Integer>[] res = new ArrayList[cccount];
        for(int i = 0; i < cccount; i ++)
            res[i] = new ArrayList<Integer>();

        for(int v = 0; v < G.V(); v ++)
            res[visited[v]].add(v);
        return res;
    }

    public static void main(String[] args){

        String filePath = "E:\\Java\\javaWorkSpace\\DataStructure\\src\\DataStructure\\NewGraph\\g.txt";
        Graph g = new AdjSet(filePath);
        CC cc = new CC(g);
        System.out.println(cc.count());

        System.out.println(cc.isConnected(0, 6));
        System.out.println(cc.isConnected(5, 6));

        ArrayList<Integer>[] comp = cc.components();
        for(int ccid = 0; ccid < comp.length; ccid ++){
            System.out.print(ccid + " : ");
            for(int w: comp[ccid])
                System.out.print(w + " ");
            System.out.println();
        }
    }
}
