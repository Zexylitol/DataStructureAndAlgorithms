package Algorithms.NewGraph.UndirGraph.Hamilton;

import DataStructure.NewGraph.UndirGraph.AdjSet;
import DataStructure.NewGraph.UndirGraph.Graph;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 哈密尔顿回路
 */
public class HamiltonLoop {

    private Graph G;
    private boolean[] visited;
    private int[] pre;                // 记录搜索过程中每一个节点的前一个节点
    private int end;                  // 记录哈密尔顿回路最后一个顶点

    public HamiltonLoop(Graph G){

        this.G = G;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        end = -1;
        dfs(0, 0, G.V());
    }

	// parent : v的上一个顶点
	// left : 未被遍历的顶点数量
    private boolean dfs(int v, int parent, int left){

        visited[v] = true;
        pre[v] = parent;
        left --;
        if(left == 0 && G.hasEdge(v, 0)){           // 所有顶点都被访问过了且最后一个顶点与起始点存在路径
            end = v;                                // 哈密尔顿回路最后一个顶点
            return true;
        }

        for(int w: G.adj(v))
            if(!visited[w]){
                if(dfs(w, v, left)) return true;
            }
//            else if(w == 0 && left == 0){
//                end = v;
//                return true;
//            }

        visited[v] = false;                       // 回溯
        return false;
    }

	// 返回哈密尔顿回路
    public ArrayList<Integer> result(){

        ArrayList<Integer> res = new ArrayList<>();
        if(end == -1) return res;                // 不存在哈密尔顿回路

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

		// 正十二面体
        String filePath = "E:\\Java\\javaWorkSpace\\DataStructure\\src\\Algorithms\\NewGraph\\UndirGraph\\Hamilton\\Dodecahedron.txt";
        Graph g = new AdjSet(filePath);
        HamiltonLoop h = new HamiltonLoop(g);
        System.out.println(h.result());
    }
}
