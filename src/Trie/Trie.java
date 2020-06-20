package Trie;

import java.util.TreeMap;

/**
 * 字典树
 * @author yzze
 * @create 2020-05-12 9:20
 */
public class Trie {
    private class Node {
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    /**
     * 获得Trie中存储的单词数量
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 向Trie中添加一个新的单词word
     * 不会重复
     * @param word
     */
     public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }

        // 判断是否是新单词
        if ( !cur.isWord) {
            cur.isWord = true;
            size++;
        }
     }

    /**
     * 查询单词word是否在Trie中
     * @param word
     * @return
     */
     public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
     }

    /**
     * 查询是否在Trie中有单词以prefix为前缀
     * @param prefix
     * @return
     */
     public boolean isPrefix(String prefix) {
         Node cur = root;
         for (int i = 0; i < prefix.length(); i++) {
             char c = prefix.charAt(i);
             if (cur.next.get(c) == null) {
                 return false;
             }
             cur = cur.next.get(c);
         }
         return true;
     }

     public static void main(String[] args) {

     }

}
