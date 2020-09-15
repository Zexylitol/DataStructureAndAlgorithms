package Algorithms.NewGraph.UndirGraph.BFS;

import DataStructure.NewGraph.UndirGraph.Graph;

/**
 * 任意顶点的单源路径
 */
public class AllPairsPath {

    private Graph G;
    private SingleSourcePath[] paths;

    public AllPairsPath(Graph G){

        this.G = G;

        paths = new SingleSourcePath[G.V()];
        for(int v = 0; v < G.V(); v ++)
            paths[v] = new SingleSourcePath(G, v);
    }

    public boolean isConnectedTo(int s, int t){
        G.validateVertex(s);
        return paths[s].isConnectedTo(t);
    }

    public Iterable<Integer> path(int s, int t){
        G.validateVertex(s);
        return paths[s].path(t);
    }
}
