package leetcode.greedy;

import java.util.Arrays;

/**
 * Created by lijianhua04 on 2019/12/17.
 */
public class CreateMaximumNumber_321_2 {



    /**
     * 首先分别求出nums1中长度为i的最大子序列，和nums2中长度为k-i的最大子序列，然后求它们归并起来的最大子序列。
     *
     * https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-321-create-maximum-number/
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {

        if (k == nums1.length + nums2.length) {
            return merge(nums1, nums2);
        }
        int[] res = new int[k];

        int max = 0;
        int[] maxRes = null;
        for (int i = Math.max(0, k - nums2.length);
             i <= Math.min(k, nums1.length); ++i){
            int[] temp1 = selectKdigits(nums1, i);
            int[] temp2 = selectKdigits(nums2, k - i);
            int[] tempMaxRes = merge(temp1, temp2);
            int s = 0;
            for (int i1 = 0; i1 < tempMaxRes.length; i1++) {
                s += tempMaxRes[i1] * Math.pow(10, tempMaxRes.length - i1 - 1);
            }
            if (s > max) {
                max = s;
                maxRes = tempMaxRes;
            }
        }
        return maxRes;
    }

    private static int[] selectKdigits(int[] nums, int k) {
        if (k >= nums.length) {
            return nums;
        }
        if (k == 0) {
            return new int[]{};
        }

        int[] ans = new int[k];
        int p = 0;
        int maxIdx = -1;
        while (k > 0) {
            int max = 0;

            for (int i = maxIdx + 1; i < nums.length - k + 1; i++) {
                if (nums[i] > max) {
                    max = nums[i];
                    maxIdx = i;
                }
            }
            ans[p++] = max;
            k--;
        }
        return ans;
    }

    private static int[] merge(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length + nums2.length];
        int s1 = 0;
        int s2 = 0;
        int index = 0;
        while (s1 != nums1.length || s2 != nums2.length)
            ans[index++] = max(nums1, s1, nums2, s2) == nums1 ?
                    nums1[s1++] : nums2[s2++];
        return ans;
    }

    private static int[] max(int[] nums1, int s1, int[] nums2, int s2) {
        for (int i = s1; i < nums1.length; ++i) {
            int j = s2 + i - s1;
            if (j >= nums2.length) return nums1;
            if (nums1[i] < nums2[j]) return nums2;
            if (nums1[i] > nums2[j]) return nums1;
        }
        return nums2;
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(maxNumber(new int[]{3, 4, 6, 5}, new int[]{9, 1, 2, 5, 8, 3}, 5)));//[9, 8, 6, 5, 3]
//        System.out.println(Arrays.toString(maxNumber(new int[]{3, 9}, new int[]{8, 9}, 3)));//[9, 8, 9]
//        System.out.println(Arrays.toString(maxNumber(new int[]{6, 7}, new int[]{6, 0, 4}, 5)));//[6, 7, 6, 0, 4]
//        System.out.println(Arrays.toString(maxNumber(new int[]{7, 6, 1, 9, 3, 2, 3, 1, 1}, new int[]{4, 0, 9, 9, 0, 5, 5, 4, 7}, 9)));//[6, 7, 6, 0, 4]
//        System.out.println(Arrays.toString(maxNumber(new int[]{2, 5, 6, 4, 4, 0}, new int[]{7, 3, 8, 0, 6, 5, 7, 6, 2}, 15)));//[6, 7, 6, 0, 4]


        System.out.println(Arrays.toString(selectKdigits(new int[]{6, 0, 4}, 0)));
        System.out.println(Arrays.toString(selectKdigits(new int[]{6, 0, 4}, 2)));
        System.out.println(Arrays.toString(selectKdigits(new int[]{3, 9}, 1)));
        System.out.println(Arrays.toString(selectKdigits(new int[]{2, 88}, 1)));
        System.out.println(Arrays.toString(selectKdigits(new int[]{9, 1, 2, 5, 8, 3}, 4)));
    }


}
