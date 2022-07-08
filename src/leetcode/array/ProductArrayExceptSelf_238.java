package leetcode.array;

import java.util.Arrays;

/**
 * 238. 除自身以外数组的乘积
 */
public class ProductArrayExceptSelf_238 {


    public static void main(String[] args) {
        System.out.println(new ProductArrayExceptSelf_238().productExceptSelf(new int[]{1, 2, 3, 4}));
    }


    /**
     * "Input:  [1,2,3,4]
     * Output: [24,12,8,6]
     * <p>
     * L        1 2 6 24
     * R       24 24 12 4"
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf0(int[] nums) {

        int length = nums.length;

        // The left and right arrays as described in the algorithm
        int[] L = new int[length];
        int[] R = new int[length];

        int[] answer = new int[length];

        L[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            L[i] = L[i - 1] * nums[i];
        }

        R[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            R[i] = R[i + 1] * nums[i];
        }

        for (int i = 0; i < answer.length; i++) {

            int l = 1;
            if (i - 1 < 0) {
                l = 1;
            } else {
                l = L[i - 1];
            }
            int r = 1;
            if (i + 1 > length - 1) {
                r = 1;
            } else {
                r = R[i + 1];
            }
            answer[i] = l * r;
        }

        return answer;
    }

    /**
     * 空间复杂度O(n)的解法
     * https://leetcode-cn.com/problems/product-of-array-except-self/solution/yi-ci-bian-li-qiao-miao-cun-chu-he-ji-suan-zuo-ji-/
     */

    public int[] productExceptSelf(int[] nums) {

        int length = nums.length;
        int[] answer = new int[length];
        Arrays.fill(answer,1);

        int left = 1, right = 1;
        for (int i = 0; i < nums.length; i++) {

            answer[i]*=left;
            left*=nums[i];

            answer[length-1-i]*=right;
            right*=nums[length-1-i];
        }

        return answer;
    }

}
