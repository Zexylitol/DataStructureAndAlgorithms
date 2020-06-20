package LeetCode;

import java.util.Arrays;

/**
 * @author yzze
 * @create 2020-05-31 11:21
 * https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/solution/dong-tai-gui-hua-jie-zui-chang-zi-xu-lie-zi-chua-4/
 */
public class LeetCode673 {
    // 动态规划
    public int findNumberOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int maxDp = 0;
        int[] dp = new int[nums.length];          // dp[i]表示以nums[i]结尾的，最长连续递增子序列的长度
        int[] cnt = new int[nums.length];         // count[i]表示以nums[i]结尾的最长递增子序列的组合个数
        Arrays.fill(dp, 1);
        Arrays.fill(cnt, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        cnt[i] += cnt[j];
                    }
                }
                maxDp = Math.max(dp[i], maxDp);
            }
        }

        int ret = 0;
        for (int i = 0; i < nums.length; i++) {
            if (maxDp == dp[i]) {
                ret += cnt[i];
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,2,4,3,5,4,7,2};          // 1 2 4 5 7;
                                                           // 1 2 3 5 7;
                                                           // 1 2 3 4 7
        LeetCode673 leetCode673 = new LeetCode673();
        System.out.println(leetCode673.findNumberOfLIS(nums));
    }

}
