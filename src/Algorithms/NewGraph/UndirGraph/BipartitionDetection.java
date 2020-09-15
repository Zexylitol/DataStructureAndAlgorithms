package Algorithms.NewGraph.UndirGraph;

import DataStructure.NewGraph.UndirGraph.AdjSet;
import DataStructure.NewGraph.UndirGraph.Graph;

/**
 * 二分图检测
 **/
public class BipartitionDetection {

    private Graph G;

    private boolean[] visited;
    private int[] colors;  // 顶点染色：0或1
    private boolean isBipartite = true;

    public BipartitionDetection(Graph G){

        this.G = G;
        visited = new boolean[G.V()];
        colors = new int[G.V()];
        for(int i = 0; i < G.V(); i ++)
            colors[i] = -1;

        for(int v = 0; v < G.V(); v ++)
            if(!visited[v])
                if(!dfs(v, 0)){
                    isBipartite = false;
                    break;
                }
    }

	// 顶点v的颜色为color
    private boolean dfs(int v, int color){

        visited[v] = true;
        colors[v] = color;
        for(int w: G.adj(v))
            if(!visited[w]){
                if(!dfs(w, 1 - color)) return false;  // 1 - color： w与v的颜色不同
            }
            else if(colors[w] == colors[v]) // v的相邻顶点w已经访问过了且染色与v相同，因此不是二分图
                return false;
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
