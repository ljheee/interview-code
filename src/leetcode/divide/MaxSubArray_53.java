package leetcode.divide;

import leetcode.ArrayGenerator;

import java.util.Arrays;

/**
 * 最大子序列和
 * https://leetcode.com/problems/maximum-subarray/submissions/
 */
public class MaxSubArray_53 {


    public int maxSubArray0(int[] nums) {

        int currentMax = Integer.MIN_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            currentMax = 0;
            for (int i1 = i; i1 < nums.length; i1++) {
                currentMax += nums[i1];
                if (currentMax > max) {
                    max = currentMax;
                }
            }

        }
        return max;
    }


    /**
     * https://www.tinymind.net.cn/articles/78b1ced9116784
     * 在线处理法，遍历数列的时候，顺便累加，每次累加的和若是小于0，那么我们可以认为最大子列和为负数时，一定不会让后面的部分增大了，所以就可以把它丢弃，重新置当前的sum = 0。
     * 只适用于 返回结果非负 的case。nums全为负数时，不行；如[-2,-1]
     */
    public int maxSubArrayOnline(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }
        int currentMax = 0, max = 0;
        int i = 0;
        for (i = 0; i < nums.length; i++) {
            currentMax += nums[i];
            if (currentMax > max) {
                max = currentMax;
            } else if (currentMax < 0) {
                currentMax = 0;
            }
        }
        return max;
    }

    /**
     * 在线处理费增强
     * 数组任意元素>0，用positive标记为true，使用在线处理的结果；
     * nums全为负数时，取数组最大value。
     *
     * @param nums
     * @return
     */
    public int maxSubArrayOnline2(int[] nums) {
        if (nums.length == 0) return 0;

        int ans = 0;
        int maxSum = 0;
        int maxValue = nums[0];
        boolean positive = false;
        for (int i : nums) {
            maxValue = Math.max(maxValue, i);
            maxSum += i;
            ans = Math.max(ans, maxSum);
            if (maxSum < 0) {
                maxSum = 0;
            }
            if (i > 0) {
                positive = true;
            }
        }
        return positive ? ans : maxValue;

    }

    /**
     * 贪心O(n)解法
     * https://leetcode-cn.com/problems/maximum-subarray/solution/zui-da-zi-xu-he-by-leetcode/
     *
     * @param nums
     * @return
     */
    public int maxSubArrayI(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }
        int cur = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            cur += nums[i];
            if (cur < nums[i]) {
                cur = nums[i];
            }
            if (cur > max) {
                max = cur;
            }
        }
        return max;
    }

    public int maxSubArray_dp(int[] nums) {

        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    public int maxSubArray_dp2(int[] nums) {

        int max = 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i - 1] > 0 ? dp[i - 1] + nums[i] : nums[i];
            max = Math.max(max, dp[i]);
        }
        return max;
    }


    public static void main(String[] args) {
//        System.out.println(new MaxSubArray_53().maxSubArrayI(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
//        System.out.println(new MaxSubArray_53().maxSubArray_dp(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));


        int[] arr = ArrayGenerator.generateRandomArray(10, -10, 10);
        System.out.println(Arrays.toString(arr));
        System.out.println(new MaxSubArray_53().maxSubArrayI(arr));
        System.out.println(new MaxSubArray_53().maxSubArray_dp(arr));
    }

}
