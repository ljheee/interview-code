package leetcode.array;

/**
 * 最佳观光点
 * 相近 两个元素，最大收益
 * A[i] + A[j] + i - j
 * 成本是j-i
 * https://leetcode.com/problems/best-sightseeing-pair/
 * <p>
 * A[j] - j 的值固定，最大的两个值，就是答案
 */
public class BestSightseeingPair_1014 {

    /**
     * 不过的case
     *
     * @param nums
     * @return
     */
    public int maxScoreSightseeingPair0(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int max = 0;
        while (left < right) {
            max = Math.max(max, nums[left] + nums[right] + left - right);
            if (nums[left] <= nums[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }

    /**
     * https://leetcode-cn.com/problems/best-sightseeing-pair/solution/python-jie-fa-by-jiayangwu/
     * pre_max 记录当前元素 A[j] 之前的 A[i] + i 的最大值，
     * 这样对于每个 A[j] 来说，都有 最大得分 = pre_max + A[j] - j，
     *
     * @param nums
     * @return
     */
    public int maxScoreSightseeingPair(int[] nums) {

        int ans = 0;
        int preMax = nums[0] + 0;
        for (int i = 1; i < nums.length; i++) {
            ans = Math.max(preMax + nums[i] - i, ans);
            preMax = Math.max(preMax, nums[i] + i);

        }
        return ans;
    }
}
