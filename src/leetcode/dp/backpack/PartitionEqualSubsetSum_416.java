package leetcode.dp.backpack;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/partition-equal-subset-sum/
 * 给了我们一个数组，问这个数组能不能分成两个非空子集合，使得两个子集合的元素之和相同。那么想，原数组所有数字和一定是偶数，不然根本无法拆成两个和相同的子集合，只需要算出原数组的数字之和，然后除以2，就是 target，
 * 转化成，选出一些数，装满背包target。
 * <p>
 * ==>从nums中选出一些物品，在target的容量限制下，最大能装多少？
 */
public class PartitionEqualSubsetSum_416 {

    //AC
    public boolean canPartition(int[] nums) {

        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) return false;
        int target = sum / 2;

        int[][] dp = new int[nums.length + 1][target + 1];// dp[i][j]背包容量为j，只有i个物品时，最大的装载量
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= target; j++) {
                if (nums[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], nums[i - 1] + dp[i - 1][j - nums[i - 1]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[nums.length][target] == target;
    }


    /**
     * dp空间优化
     * 上面的解法，dp[i][j] 只和 dp[i - 1][j] 有关，优化一维空间
     * 定义一个一维的 dp 数组，其中 dp[i] 表示原数组是否可以取出若干个数字，其和为i。那么最后只需要返回 dp[target] 就行了。
     * <p>
     * dp[0] = true
     * dp[i] 能否取出若干个数，凑出和为i。
     * dp[i] = dp[i-1] || dp[i-nums[i]]
     * <p>
     * 思想来源 https://www.cnblogs.com/grandyang/p/5951422.html
     * https://leetcode.com/problems/partition-equal-subset-sum/discuss/90592/01-knapsack-detailed-explanation
     */
    public boolean canPartition1(int[] nums) {

        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) return false;
        int target = sum / 2;

        // dp[i] 使用0~i上的数能否达到 和为i
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {// 时间复杂度没有优化，优化的是空间复杂度
            for (int i = target; i >= num; i--) {
                dp[i] = dp[i] | dp[i - num];
            }
        }
        return dp[target];
    }


}
