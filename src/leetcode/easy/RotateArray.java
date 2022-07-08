package leetcode.easy;

import java.util.Arrays;

/**
 * 数组后k项 移到前面
 * Input: [1,2,3,4,5,6,7] and k = 3
 * Output: [5,6,7,1,2,3,4]
 * <p>
 * https://leetcode.com/problems/rotate-array/
 * 特殊case： [1,2] k=3
 * 旋转3次，需要对k特殊处理，k有可能比n大，k取k%len是等价的
 */
public class RotateArray {

    /**
     * 解法一
     * 替换后再覆盖
     * https://www.cnblogs.com/bakari/archive/2012/09/09/2677155.html
     *
     * @param nums
     * @param k
     */
    public static void rotate0(int[] nums, int k) {
        if (nums.length == 1) {
            return;
        }
        if (k == 0) {
            return;
        }

        k = k % nums.length;// 对k特殊处理
        int[] res = new int[nums.length];
        int from = nums.length - k;
        int idx = 0;
        for (int i = from; i <= nums.length - 1; i++) {
            res[idx++] = nums[i];
        }
        for (int i = 0; i < from; i++) {
            res[idx++] = nums[i];
        }

        // 重新赋值给nums
        idx = 0;
        for (int i = 0; i < res.length; i++) {
            nums[idx++] = res[i];
        }
    }

    /**
     * 移位：每次把尾元素 移到头部
     *
     * @param nums
     * @param k
     */
    public static void rotate(int[] nums, int k) {
        if (nums.length == 1) {
            return;
        }
        if (k == 0) {
            return;
        }
        for (int i = 1; i <= k; i++) {
            rotate(nums);
        }
    }

    // 把数组 末元素当第一个，后续的后移一位
    private static void rotate(int[] nums) {
        int last = nums[nums.length - 1];
        for (int i = nums.length - 1; i > 0; i--) {
            nums[i] = nums[i - 1];
        }
        nums[0] = last;
    }


    /**
     * 解法三
     * 三步翻转法
     *
     * @param nums
     * @param k
     */
    public static void rotate3(int[] nums, int k) {
        k = k % nums.length;
        k = nums.length - k;
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
    }

    private static void reverse(int[] nums, int from, int to) {
        while (from < to) {
            int temp = nums[from];
            nums[from++] = nums[to];
            nums[to--] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        rotate3(arr, 3);
        System.out.println(Arrays.toString(arr));

        int[] arr1 = new int[]{1, 2};
        rotate0(arr1, 3);
        System.out.println(Arrays.toString(arr1));//[2, 1]
    }
}


