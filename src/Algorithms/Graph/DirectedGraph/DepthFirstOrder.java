package Algorithms.Graph.DirectedGraph;

import DataStructure.Graph.DirectedGraph.Digraph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 有向图中基于深度优先搜索的顶点排序
 * 用各种顺序遍历深度优先搜索经过的所有顶点
 * @author yzz
 * @create 2020-07-04 19:40
 */
public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre;         // 所有顶点的前序排列
    private Queue<Integer> post;        // 所有顶点的后序排列
    private Stack<Integer> reversePost; // 所有顶点的逆后序排列

    public DepthFirstOrder(Digraph G) {
        pre = new LinkedList<>();
        post = new LinkedList<>();
        reversePost = new Stack<>();
        marked = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        pre.offer(v);                           // 前序：在递归调用前将顶点加入队列
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        post.offer(v);                          // 后序：在递归调用之后将顶点加入队列
        reversePost.push(v);                    // 逆后序：在递归调用之后将顶点压入栈
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}
