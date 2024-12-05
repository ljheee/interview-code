package leetcode.sliding;

/**
 * 找出 sum>=s的子数组，长度最短
 * 两个指针，夹住一个窗口，
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 */
public class MinimumSizeSubarraySum_209 {


    public int minSubArrayLen(int s, int[] nums) {

        int left = 0;
        int totalSum = 0;
        int sum = 0;
        int ans = nums.length;
        for (int right = 0; right < nums.length; right++) {
            totalSum += nums[right];
            if (nums[right] >= s) return 1;

            sum += nums[right];
            while (sum >= s) {
                ans = Math.min(ans, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }

        if (totalSum < s) {
            return 0;
        }
        return ans;
    }

}
