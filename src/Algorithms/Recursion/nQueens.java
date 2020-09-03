package Algorithms.Recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 51.N皇后
 * https://leetcode-cn.com/problems/n-queens/
 * @author yzz
 * @create 2020-09-03 20:18
 */
public class nQueens {

    final int N = 1010;                 // 最大皇后个数
    int[] array = new int[N];     // 索引i表示第i个皇后,array[i]表示第i个皇后所在的列

    static List<List<String>> res = new ArrayList<>(); // 结果集

    /**
     *  放置第index个皇后
     * @param index: 第index个皇后
     * @param n : 总共有n个皇后
     */
    public void check(int index, int n) {
        if (index == n) {        // n个皇后全部已经放好
            record(n);           // 记录摆放的位置
            return ;
        }
        for (int i = 0; i < n; i++) {       // 依次放置皇后，并判断是否冲突
            array[index] = i;               // 放置在第i列
            if (judge(index)) {
                check(index + 1, n);  // 不冲突，接着放置第index+1个皇后
            }
            // 如果有冲突，就继续执行 array[index] = i ，即将第index个皇后在本行后移一个位置
        }
    }

    /**
     * 当放置第n个皇后时，判断是否有冲突
     * 1. array[i] == array[n]：表明第n个皇后和第i(0<= i < n)个皇后在同一列
     * 2. Math.abs(n - i) == Math.abs(array[n] - array[i]): 表明第n个皇后和第i(0<= i < n)个皇后在同一斜线上
     * @param n : 第n个皇后
     * @return
     */
    public boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 记录皇后摆放的位置
     * @param n ： n个皇后
     */
    public void record(int n) {

        List<String> r = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (j == array[i]) sb.append("Q");  // 第i个皇后所在的列，用'Q'表示
                else sb.append(".");
            }
            r.add(sb.toString());
        }
        res.add(r);
    }


    public static void main(String[] args) {
        nQueens nqueens = new nQueens();
        nqueens.check(0, 4);
        System.out.println(res.toString());
        /**
         [[.Q..,
           ...Q,
           Q...,
           ..Q.],
          [..Q.,
           Q...,
           ...Q,
           .Q..]]
         */
    }

}
