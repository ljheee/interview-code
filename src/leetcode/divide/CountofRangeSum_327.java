package leetcode.divide;

/**
 * https://leetcode.com/problems/count-of-range-sum/submissions/
 */
public class CountofRangeSum_327 {

    /**
     * 暴力双重for
     *
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    public static int countRangeSum(int[] nums, int lower, int upper) {

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            long perSum = 0;// 关键：使用int会超界
            for (int j = i; j < nums.length; j++) {
                perSum += nums[j];
                if (perSum >= lower && perSum <= upper) {
                    count++;
                }
            }
        }
        return count;
    }


    // TODO: 2019/12/15  分治解法

    public static void main(String[] args) {
        countRangeSum(new int[]{-2147483647, 0, -2147483647, 2147483647}, -564, 3864);
    }


}
