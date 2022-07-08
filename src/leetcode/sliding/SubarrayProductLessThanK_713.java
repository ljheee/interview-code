package leetcode.sliding;

/**
 * https://leetcode.com/problems/subarray-product-less-than-k/
 */
public class SubarrayProductLessThanK_713 {


    /**
     * 双重循环
     *
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK0(int[] nums, int k) {

        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < k) {
                ans++;
            } else {
                continue;
            }

            int p = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                p *= nums[j];
                if (p < k) {
                    ans++;
                }
            }
        }
        return ans;
    }

    /**
     * 滑动窗口
     * 扩展右指针，同时还需收缩左指针
     *
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {

        int product = 1;
        int left = 0;

        int ans = 0;
        boolean hascontinue = false;
        for (int right = 0; right < nums.length; ) {

            hascontinue = false;
            product *= nums[right];
            if (product < k) {
                ans++;
                right++;
                hascontinue = true;
            }

            while (product >= k && left <= right) {
                product /= nums[left++];
                if (product < k) ans++;
            }
            if (!hascontinue) {
                product /= nums[right];
            }
        }

        // 最后收缩完left
        while (product >= 1 && left < nums.length) {
            product /= nums[left++];
            if (product < k) ans++;
        }
        return ans;
    }

    /**
     * ans += right - left + 1;
     * nums[left] * nums[left + 1] * ... * nums[right] < k
     * 所有收缩left时，小于k的等式依然成立；也就是left能收缩right-left+1个。
     *
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK2(int[] nums, int k) {

        int product = 1;
        int left = 0;

        int ans = 0;
        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];
            while (product >= k) {
                product /= nums[left++];
            }
            ans += (right - left + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new SubarrayProductLessThanK_713().numSubarrayProductLessThanK2(new int[]{10, 5, 2, 6}, 100));
        System.out.println(new SubarrayProductLessThanK_713().numSubarrayProductLessThanK2(new int[]{10, 9, 1, 1}, 91));


    }

}
