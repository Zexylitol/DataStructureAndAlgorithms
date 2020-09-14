package DataStructure.NewGraph.UndirGraph;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 使用邻接矩阵表示无向无全图
 * 空间复杂度: O(V^2)
 * 时间复杂度：
 *				建图：O(E)
 *				查看两点是否相邻：O(1)
 *				求一个点的相邻节点：O(V)
 **/
public class AdjMatrix implements Graph {

    private int V;
    private int E;
    private int[][] adj;

    public AdjMatrix(String filename){

        File file = new File(filename);

        try(Scanner scanner = new Scanner(file)){

            V = scanner.nextInt();
            if(V < 0) throw new IllegalArgumentException("V must be non-negative");
            adj = new int[V][V];

            E = scanner.nextInt();
            if(E < 0) throw new IllegalArgumentException("E must be non-negative");

            for(int i = 0; i < E; i ++){
                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);

                if(a == b) throw new IllegalArgumentException("Self Loop is Detected!");
                if(adj[a][b] == 1) throw new IllegalArgumentException("Parallel Edges are Detected!");

                adj[a][b] = 1;
                adj[b][a] = 1;
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    // 判断输入顶点v的合法性[0,V-1]
    public void validateVertex(int v){
        if(v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + "is invalid");
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public boolean hasEdge(int v, int w){
        validateVertex(v);
        validateVertex(w);
        return adj[v][w] == 1;
    }

    public Iterable<Integer> adj(int v){
        validateVertex(v);
        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 0; i < V; i ++)
            if(adj[v][i] == 1)
                res.add(i);
        return res;
    }

    public int degree(int v){
        validateVertex(v);
        int res = 0;
        for(int i = 0; i < V; i ++)
            res += adj[v][i];
        return res;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("V = %d, E = %d\n", V, E));
        for(int i = 0; i < V; i ++){
            for(int j = 0; j < V; j ++)
                sb.append(String.format("%d ", adj[i][j]));
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args){

        String filePath = "E:\\Java\\javaWorkSpace\\DataStructure\\src\\DataStructure\\NewGraph\\g.txt";
        Graph adjMatrix = new AdjMatrix(filePath);
        System.out.print(adjMatrix);
    }
}
