package Algorithms.KMP;

/**
 * Knuth-Morris-Pratt字符串查找算法，简称"KMP算法"，常用于在一个文本串S内查找一个模式串P的出现位置
 *
 * https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/dong-tai-gui-hua-zhi-kmp-zi-fu-pi-pei-suan-fa#si-dai-ma-shi-xian
 *
 * @create 2020-10-12 10:48
 */
public class KMP {
    private int[][] dp;
    private String pat;

    public KMP(String pat) {
        this.pat = pat;
        int M = pat.length();
        // dp[状态][字符] = 下个状态
        dp = new int[M][256];
        // base case
        dp[0][pat.charAt(0)] = 1;
        // 影子状态 X 初始为 0
        int X = 0;
        // 构建状态转移图（稍改的更紧凑了）
        for (int j = 1; j < M; j++) {
            for (int c = 0; c < 256; c++)
                dp[j][c] = dp[X][c];
            dp[j][pat.charAt(j)] = j + 1;
            // 更新影子状态
            X = dp[X][pat.charAt(j)];
        }
    }

    public int search(String txt) {
        int M = pat.length();
        int N = txt.length();
        // pat 的初始态为 0
        int j = 0;
        for (int i = 0; i < N; i++) {
            // 计算 pat 的下一个状态
            j = dp[j][txt.charAt(i)];
            // 到达终止态，返回结果
            if (j == M) return i - M + 1;
        }
        // 没到达终止态，匹配失败
        return -1;
    }

    public static void main(String[] args) {
        // dp数组只和pat有关，当需要用同一pat去匹配不同txt时，就不需要浪费时间构造dp数组了
        KMP kmp = new KMP("aaab");
        int pos1 = kmp.search("aaacaaab");    // 4
        int pos2 = kmp.search("aaaaaaab");    // 4
        System.out.println("pos1 = " + pos1);
        System.out.println("pos2 = " + pos2);
    }
}
