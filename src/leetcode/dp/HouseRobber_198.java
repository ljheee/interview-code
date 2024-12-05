package leetcode.dp;

import java.util.Arrays;

/**
 * 打劫邻舍
 * 相邻房屋，不能连续偷盗
 * https://leetcode.com/problems/house-robber/
 */
public class HouseRobber_198 {


    /**
     * 要么偷奇数，要么偷偶数，不对；
     * 特殊case：2,1,1,2
     *
     * @param nums
     * @return
     */
    public int rob0(int[] nums) {
        int even = 0;
        int odd = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                odd += nums[i];
            } else {
                even += nums[i];
            }
        }
        return Math.max(odd, even);
    }


    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        // dp[i]要偷窃i，能够得到的最大值
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[1];
        int max = nums[0];

        for (int i = 2; i < nums.length; i++) {
            dp[i] = max + nums[i];
            max = Math.max(max, dp[i - 1]);
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    /**
     * Since House[1] and House[n] are adjacent, they cannot be robbed together. Therefore, the problem becomes to rob either House[1]-House[n-1] or House[2]-House[n], depending on which choice offers more money. Now the problem has degenerated to the House Robber, which is already been solved.
     * https://leetcode.com/problems/house-robber-ii/
     *
     * @param nums
     * @return
     */
    public int robII(int[] nums) {

        return Math.max(rob(Arrays.copyOfRange(nums, 0, nums.length - 1)), rob(Arrays.copyOfRange(nums, 1, nums.length)));
    }


}
