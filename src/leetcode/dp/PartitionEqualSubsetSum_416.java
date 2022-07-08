package leetcode.dp;

import java.util.Arrays;

/**
 * 将数组分割成两个和相等的子集
 * https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/jiang-shu-zu-fen-ge-cheng-liang-ge-he-xiang-deng-d/
 * Created by lijianhua04 on 2020/3/28.
 */
public class PartitionEqualSubsetSum_416 {


    public static boolean canPartition(int[] nums) {

        int target = Arrays.stream(nums).sum() / 2;

        int[][] dp = new int[nums.length + 1][target + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (nums[i - 1] <= j) {
                    dp[i][j] = Math.max(nums[i - 1] + dp[i - 1][j - nums[i - 1]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[nums.length][target] == target;
    }


    public static void main(String[] args) {

        System.out.println(canPartition(new int[]{1, 5, 11, 5}));
    }


}
