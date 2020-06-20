package DataStructure.UnionFind;

/**
 * 并查集支持的操作
 * @author yzze
 * @create 2020-05-13 17:00
 */
public interface UF {
    boolean isConnected(int p, int q);
    void unionElements(int p, int q);
    int getSize();
}
