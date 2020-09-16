package Algorithms.NewGraph.UndirGraph.Hamilton.StateCompression;

import DataStructure.NewGraph.UndirGraph.AdjSet;
import DataStructure.NewGraph.UndirGraph.Graph;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 将visited数组进行状态压缩
 * 第i位是否为0：visited & (1 << i) == 0?
 * 如果第i位为0，设为1：visited + (1 << i)
 * 如果第i位为1:，设为0：visited - (1 << i)
 */
public class HamiltonLoop {

    private Graph G;
    private int[] pre;
    private int end;

    public HamiltonLoop(Graph G){

        this.G = G;
        pre = new int[G.V()];
        end = -1;

        int visited = 0;
        dfs(visited, 0, 0, G.V());
    }

	// 在visited状态下，从顶点v出发还需要遍历left个顶点才能找到一个哈密尔顿回路
    private boolean dfs(int visited, int v, int parent, int left){

        visited += (1 << v);
        pre[v] = parent;
        left --;
        if(left == 0 && G.hasEdge(v, 0)){
            end = v;
            return true;
        }

        for(int w: G.adj(v))
            if((visited & (1 << w)) == 0){
                if(dfs(visited, w, v, left)) return true;
            }

        visited -= (1 << v);
        return false;
    }

    public ArrayList<Integer> result(){

        ArrayList<Integer> res = new ArrayList<>();
        if(end == -1) return res;

        int cur = end;
        while(cur != 0){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(0);

        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args){

        String filePath = "E:\\Java\\javaWorkSpace\\DataStructure\\src\\Algorithms\\NewGraph\\UndirGraph\\Hamilton\\Dodecahedron.txt";
        Graph g = new AdjSet(filePath);
        HamiltonLoop h = new HamiltonLoop(g);
        System.out.println(h.result());
    }
}
