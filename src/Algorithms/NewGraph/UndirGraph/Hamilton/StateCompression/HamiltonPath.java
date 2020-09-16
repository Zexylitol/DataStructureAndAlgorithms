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
public class HamiltonPath {

    private Graph G;
    private int[] pre;
    private int end;
    private int s;

    public HamiltonPath(Graph G, int s){

        this.G = G;
        this.s = s;
        pre = new int[G.V()];
        end = -1;

        int visited = 0;
        dfs(visited, s, s, G.V());
    }

    private boolean dfs(int visited, int v, int parent, int left){

        visited += (1 << v);
        pre[v] = parent;
        left --;
        if(left == 0){
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
        while(cur != s){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);

        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args){

        String filePath = "E:\\Java\\javaWorkSpace\\DataStructure\\src\\Algorithms\\NewGraph\\UndirGraph\\Hamilton\\g4.txt";
        Graph g = new AdjSet(filePath);
        HamiltonPath hp = new HamiltonPath(g, 0);
        System.out.println(hp.result());

        HamiltonPath hp2 = new HamiltonPath(g, 1);
        System.out.println(hp2.result());
    }
}
