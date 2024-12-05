package leetcode.array;

import java.util.HashMap;

/**
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * 无重复元素
 * <p>
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 */
public class FindMinimumRotatedSortedArray_153 {


    /**
     * 最简单的办法，找出会降点
     *
     * @param nums
     * @return
     */
    public int findMin0(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                return nums[i];
            }
        }
        return nums[0];
    }


    /**
     * Input: [3,4,5,1,2]
     * Output: 1
     * <p>
     * 二分查找，找到nums[i] 小于左边的数
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {

        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;

            //退出条件
            if (mid - 1 >= 0 && nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return nums[0];
    }

    /**
     * 在旋转数组中，搜索指定值
     * Input: nums = [4,5,6,7,0,1,2], target = 0
     * Output: 4
     * 元素不重复
     * https://leetcode.com/problems/search-in-rotated-sorted-array/
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {

        boolean atFront = nums[nums.length - 1] < target;
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;

            //退出条件
            if (nums[mid] == target) {
                return mid;
            }
            // 4,5,6,7,0,1,2 查找0
            if (nums[mid] > nums[right] && target <= nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right] && nums[mid] < target && target <= nums[right]) {
                // 1,2,3 查找2
                left = mid + 1;
            } else if (nums[mid] > nums[right] && target > nums[mid]) {
                // 4,5,7,8,1 查找8
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
//        new FindMinimumRotatedSortedArray_153().findMin(new int[]{4,5,6,7,0,1,2});
//        new FindMinimumRotatedSortedArray_153().findMin(new int[]{2, 1});
//        new FindMinimumRotatedSortedArray_153().search(new int[]{1, 3},3);
//        new FindMinimumRotatedSortedArray_153().search(new int[]{4,5,6,7,0,1,2},0);
        new FindMinimumRotatedSortedArray_153().search(new int[]{4, 5, 6, 7, 8, 1, 2, 3}, 8);
    }

    /**
     * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/submissions/
     * 包含重复元素
     */

}
