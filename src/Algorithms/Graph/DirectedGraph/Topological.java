package Algorithms.Graph.DirectedGraph;

import DataStructure.Graph.DirectedGraph.Digraph;
import DataStructure.Graph.DirectedGraph.SymbolGraph;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 顶点的深度优先次序与拓扑排序
 * 优先级限制下的调度问题等价于计算有向无环图中的所有顶点的拓扑排序
 * @author yzz
 * @create 2020-07-04 19:36
 */
public class Topological {
    private Iterable<Integer> order;           // 顶点的拓扑排序
    public Topological(Digraph G) {
        DirectedCycle cyclefinder = new DirectedCycle(G);
        if (!cyclefinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    // 拓扑排序的所有顶点
    public Iterable<Integer> order() {
        return order;
    }

    // G是有向无环图
    public boolean isDAG() {
        return order != null;
    }

    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<String>();             // 保存文件内容

        String filePath = "E:\\Java\\javaWorkSpace\\DataStructure\\src\\Algorithms\\Graph\\DirectedGraph\\jobs.txt";
        File file = new File(filePath);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
        BufferedReader br = new BufferedReader(reader);
        String line = " ";
        line = br.readLine();
        while(line != null) {
            list.add(line);
            line = br.readLine();
        }

        SymbolGraph sg = new SymbolGraph(list, "/");

        Topological top = new Topological(sg.Graph());

        for (int v : top.order()) {
            System.out.println(sg.name(v));
        }
    }
}
