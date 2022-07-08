package leetcode.dp;

import java.util.Arrays;

/**
 * 乘积最大的子数组
 * https://leetcode-cn.com/problems/maximum-product-subarray/
 */
public class MaximumProductSubarray_152 {


    /**
     *
     *
     * @param nums
     * @return
     */
    public int maxProduct0(int[] nums) {

        /**
         * dp[i] 记录以i位置结尾，能达到的最大乘积
         */
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= 0) {
                dp[i] = Math.max(nums[i], dp[i - 1] * nums[i]);
                continue;
            }
            if (dp[i - 1] <= 0) {
                dp[i] = nums[i];
                continue;
            }
            dp[i] = dp[i - 1] * nums[i];
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    /**
     * https://leetcode-cn.com/problems/maximum-product-subarray/solution/dpfang-fa-xiang-jie-by-yang-cong-12/
     * 用两个dp数组
     * dpMin记录乘积最小值
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {

        /**
         * dp[i] 记录以i位置结尾，能达到的最大乘积
         */
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        // 以i位置结尾，乘积的最小值
        int[] dpMin = new int[nums.length];
        dpMin[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                dp[i] = 0;
                dpMin[i] = 0;
            } else if (nums[i] < 0) {
                dp[i] = Math.max(nums[i], dpMin[i - 1] * nums[i]);
                dpMin[i] = Math.min(nums[i], dp[i - 1] * nums[i]);
            } else {
                dp[i] = Math.max(nums[i], dp[i - 1] * nums[i]);
                dpMin[i] = Math.min(nums[i], dpMin[i - 1] * nums[i]);
            }
        }

        return Arrays.stream(dp).max().getAsInt();
    }


    public static void main(String[] args) {

        System.out.println(new MaximumProductSubarray_152().maxProduct(new int[]{2, -3, -4, 0, 9}));//24
        System.out.println(new MaximumProductSubarray_152().maxProduct(new int[]{-2, -3, -4, 0, 9}));// 12
        System.out.println(new MaximumProductSubarray_152().maxProduct(new int[]{2, 3, 4, 0, 9}));// 24
        System.out.println(new MaximumProductSubarray_152().maxProduct(new int[]{2, 3, 4, 0, 9, 9}));//91
        System.out.println(new MaximumProductSubarray_152().maxProduct(new int[]{-2, -1, 0}));//2
        System.out.println(new MaximumProductSubarray_152().maxProduct(new int[]{0, -1, 0}));//0
    }


    /**
     * 能变大就扩展right
     * 否则收缩left  ——不好收缩
     *
     * @param nums
     * @return
     */
    public int maxProduct_slidingWindow(int[] nums) {
        int left = 0;
        int prod = 1;

        int max = nums[0];
        for (int right = 0; right < nums.length; ) {
            int after = prod * nums[right];

            if (after > max) max = prod;
            if (after > prod) {
                prod = after;
                right++;
            } else {

            }
        }

        return 0;
    }
}
