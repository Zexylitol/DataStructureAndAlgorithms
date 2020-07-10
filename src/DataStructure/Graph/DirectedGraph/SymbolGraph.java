package DataStructure.Graph.DirectedGraph;

import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

/**
 * 符号图：使用字符串而非整数来表示和指代顶点
 * @author yzz
 * @create 2020-07-03 16:01
 */
public class SymbolGraph {
    private TreeMap<String, Integer> map;     // 符号名/顶点名 -> 索引
    private String[] keys;                    // 索引 -> 符号名
    private Digraph G;              // 使用索引表示顶点的图

    /**
     * 根据指定的文件构造图
     * @param list 从文件中读取的图信息
     * @param sp   顶点分隔符
     * @throws IOException
     */
    public SymbolGraph(List<String> list, String sp) throws IOException {

        map = new TreeMap<>();
        for (String line : list) {                // 第一遍 构造索引
            String[] a = line.split(sp);
            for (int i = 0; i < a.length; i++) {  // 为每个不同的字符串关联一个索引
                if(!map.containsKey(a[i])) {
                    map.put(a[i], map.size());
                }
            }
        }

        keys = new String[map.size()];           // 用来获得顶点名的反向索引是一个数组
        for (String name : map.keySet()) {
            keys[map.get(name)] = name;
        }

        G = new Digraph(map.size());
        for (String line : list) {              // 第二遍 构造图
            String[] a = line.split(sp);        // 将每一行的顶点和该行的其他顶点相连

            int v = map.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                G.addEdge(v, map.get(a[i]));
            }
        }
    }

    /**
     * s是一个顶点吗
     * @param s
     * @return
     */
    public boolean contains(String s) {
        return map.containsKey(s);
    }

    /**
     * s的索引
     * @param s
     * @return
     */
    public int index(String s) {
        return map.get(s);
    }

    /**
     * 索引v的顶点名
     * @param v
     * @return
     */
    public String name(int v) {
        return keys[v];
    }

    /**
     * 隐藏的AdjListUndirGraph对象
     * @return
     */
    public Digraph Graph() {
        return G;
    }

}