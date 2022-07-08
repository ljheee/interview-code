package leetcode.dp;

import java.util.Arrays;

/**
 * 找出最长递增子序列的数量
 * https://leetcode.com/problems/number-of-longest-increasing-subsequence/
 */
public class NumberOfLongestIncreasingSubsequence_673 {


    /**
     * 找出最长递增子序列长度后；
     * 从dp数组，输出maxLen的数量，以及maxLen-1的数量；
     * 多个最长子序列，是由于 多个maxLen-1 ，再加最后一个长度得到的 ？？？
     * Input: [1,3,5,4,7]
     * <p>
     * 不过的case [1,3,5,4,7,3,5,4,7,3,5,4,7,3,5,4,5,4,7,3,5,4,7,3,5,4,7,3,5,4]
     *
     * @param nums
     * @return
     */
    public int findNumberOfLIS0(int[] nums) {
        if (nums.length <= 1) return nums.length;

        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxLen = 1;
        for (int i = 1; i < dp.length; i++) {
            int curMax = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    curMax = Math.max(curMax, dp[j]);
                }
            }
            dp[i] = curMax + 1;
            maxLen = Math.max(maxLen, dp[i]);
        }

        int ans = 0;
        int lastIdx = -1;
        for (int i = dp.length - 1; i >= 0; i--) {
            if (dp[i] == maxLen && lastIdx < 0) {
                ans++;
                lastIdx = i;
                continue;
            }
            if ((dp[i] == maxLen || dp[i] == maxLen - 1) && lastIdx > 0) {
                ans++;
            }
        }

        return ans;
    }

    /**
     * 用 count 数组，记录各种长度下，递增子序列的数量
     *
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        if (nums.length <= 1) return nums.length;

        // count[i]长度为i的 子序列个数
        int[] count = new int[nums.length + 1];
        count[1] = nums.length;

        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxLen = 1;
        for (int i = 1; i < dp.length; i++) {
            int curMax = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    curMax = Math.max(curMax, dp[j]);
                    count[dp[j] + 1]++;
                }
            }
            dp[i] = curMax + 1;
            maxLen = Math.max(maxLen, dp[i]);
        }
        return count[maxLen];
    }

    /**
     * 分开两次 计算dp,和count数组不行
     *
     * @param nums
     * @return
     */
    public int findNumberOfLIS1(int[] nums) {
        if (nums.length <= 1) return nums.length;

        // count[i]以i结尾时，最长子序列个数：有两个限定条件，一是以nums[i]nums[i]结尾，二是最长递增子序列
        int[] count = new int[nums.length];
        Arrays.fill(count, 1);

        count[1] = nums.length;

        // 先计算dp
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxLen = 1;
        for (int i = 1; i < dp.length; i++) {
            int curMax = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    curMax = Math.max(curMax, dp[j]);
                }
            }
            dp[i] = curMax + 1;
            maxLen = Math.max(maxLen, dp[i]);
        }
        // 再计算count
        for (int i = 1; i < dp.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        count[i] = count[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        count[i] += count[j];
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] == maxLen) {
                ans += count[i];
            }
        }

        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/solution/dong-tai-gui-hua-jie-zui-chang-zi-xu-lie-zi-chua-4/
     * @param nums
     * @return
     */
    public int findNumberOfLIS2(int[] nums) {
        if (nums.length <= 1) return nums.length;

        // count[i]以i结尾时，最长子序列个数：有两个限定条件，一是以nums[i]nums[i]结尾，二是最长递增子序列
        int[] count = new int[nums.length];
        Arrays.fill(count, 1);
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int maxLen = 1;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {// 从小到大
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                        count[i] = count[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        count[i] += count[j];
                    }
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        int ans = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] == maxLen) {
                ans += count[i];
            }
        }

        return ans;
    }


    public static void main(String[] args) {
        NumberOfLongestIncreasingSubsequence_673 increasingSubsequence673 = new NumberOfLongestIncreasingSubsequence_673();

        System.out.println(increasingSubsequence673.findNumberOfLIS(new int[]{2, 2, 2, 2, 2, 2}));

        System.out.println(increasingSubsequence673.findNumberOfLIS2(new int[]{1, 3, 5, 4, 7, 3, 5, 4, 7, 3, 5, 4, 7, 3, 5, 4}));
    }

}
