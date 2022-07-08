package leetcode.dp.backpack;

import leetcode.ArrayGenerator;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
 * 能否将 nums 拆分成k个子集，和相等；
 */
public class PartitionToKEqualSumSubsets_698 {

    /**
     * 不过case：
     * [2,2,2,2,3,4,5]
     * 4
     * 主要是num被重复使用了。
     * sum=20,target=5,dp[7][5],dp[7][10],dp[7][15]都是true，重复使用了里面的数
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {

        int sum = Arrays.stream(nums).sum();

        if (sum % k != 0) return false;
        int target = sum / k;

        int n = nums.length;
        boolean[][] dp = new boolean[n + 1][sum + 1];
        dp[0][0] = true;
        for (int i = 1; i < n + 1; i++) {
            dp[i][0] = true;
        }

        for (int j = 1; j < sum + 1; j++) {
            dp[0][j] = false;
        }
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - nums[i - 1] >= 0) {
                    dp[i][j] = dp[i - 1][j] | dp[i][j - nums[i - 1]];
                }
            }
        }

        boolean ans = true;
        for (int i = 0; i < k; i++) {
            ans &= dp[nums.length][(i + 1) * target];
        }

        return ans;
    }


    public static void main(String[] args) {
        PartitionToKEqualSumSubsets_698 partitionToKEqualSumSubsets_698 = new PartitionToKEqualSumSubsets_698();
//        System.out.println(partitionToKEqualSumSubsets_698.canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));
        System.out.println(partitionToKEqualSumSubsets_698.canPartitionKSubsets(new int[]{2,2,2,2,3,4,5}, 4));

        int[] array = ArrayGenerator.generateRandomArray(16, 1, 10);
        System.out.println(Arrays.toString(array));
        System.out.println(partitionToKEqualSumSubsets_698.canPartitionKSubsets(array, 4));
    }
}
