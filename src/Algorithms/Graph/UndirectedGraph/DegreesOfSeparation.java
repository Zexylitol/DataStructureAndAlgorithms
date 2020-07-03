package Algorithms.Graph.UndirectedGraph;

import DataStructure.Graph.UndirectedGraph.SymbolGraph;
import DataStructure.Graph.UndirectedGraph.UndiGraph;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 找到一个社交网络中两个人间隔的度数
 * @author yzz
 * @create 2020-07-03 19:29
 */
public class DegreesOfSeparation {
    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<String>();             // 保存文件内容

        String filePath = "E:\\Java\\javaWorkSpace\\DataStructure\\src\\DataStructure\\Graph\\UndirectedGraph\\SymbolGraph.txt";
        File file = new File(filePath);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
        BufferedReader br = new BufferedReader(reader);
        String line = " ";
        line = br.readLine();
        while(line != null) {
            list.add(line);
            line = br.readLine();
        }
        SymbolGraph sg = new SymbolGraph(list, " ");

        UndiGraph G = sg.Graph();

        // 查找顶点"JFK"和"DEN"之间的最短路径
        String source = "JFK";
        if (!sg.contains(source)) {
            System.out.println(source + "not in database");
            return ;
        }
        int s = sg.index(source);
        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);

        String sink = "DEN";
        if (sg.contains(sink)) {
            int t = sg.index(sink);
            if (bfs.hasPathTo(t)) {
                for (int v : bfs.pathTo(t)) {
                    System.out.println("  " + sg.name(v));
                }
            } else {
                System.out.println("Not connected.");
            }
        } else {
            System.out.println("Not in database.");
        }
    }
}
