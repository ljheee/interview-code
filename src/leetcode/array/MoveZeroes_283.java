package leetcode.array;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/move-zeroes/
 * 把0 移到数组末尾，非0 数字相对顺序不变
 * Created by lijianhua04 on 2020/4/3.
 */
public class MoveZeroes_283 {

    /**
     * 非0 数字相对顺序不变
     *
     * @param nums
     */
    public void moveZeroes0(int[] nums) {

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {

            if (nums[left] == 0) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right--;
            } else {
                left++;
            }
        }
    }


    /**
     * 采用 三步翻转，将0元素移至最后
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {

        if (nums.length == 1) {
            return;
        }
        int left = 0;
        int right = nums.length - 1;
        int zeroCount = 0;
        while (left <= right) {

            if (nums[left] == 0) {
                zeroCount++;
                reverse(nums, left, left);
                reverse(nums, left + 1, right);
                reverse(nums, left, right);
                // left++;
                right--;
            } else {
                left++;
            }
        }
        // [0,0,0]全0时，退出
        if (zeroCount == nums.length - 1 - right) {
            return;
        }
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int num = nums[left];
            nums[left] = nums[right];
            nums[right] = num;
            left++;
            right--;
        }
    }


    /**
     * 将非0元素 按顺序排在数组开头，后面全填充0
     *
     * @param nums
     */
    public void moveZeroes1(int[] nums) {

        int lastNonZeroIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[lastNonZeroIndex++] = nums[i];
            }
        }

        for (int i = lastNonZeroIndex; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public boolean validMountainArray(int[] A) {

        if (A.length < 3) {
            return false;
        }
        int mountain = -1;
        int idx = -1;
        for (int i = 1; i < A.length - 1; i++) {
            if (A[i] <= A[i - 1]) {
                return false;
            }
            if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
                mountain = A[i];
                idx = i;
                break;
            }

        }
        for (int i = idx + 1; i < A.length; i++) {
            if (A[i] >= mountain) {
                return false;
            }
        }
        return mountain > 0;
    }



    public static void main(String[] args) {
//        int[] a = new int[]{0,1,3,0,12};
//        int[] a = new int[]{0,1,0};
//        int[] a = new int[]{0,0};
//        int[] a = new int[]{0,0,1};
        int[] a = new int[]{1, 0, 0, 1};
        new MoveZeroes_283().moveZeroes(a);
        System.out.println(Arrays.toString(a));

        new MoveZeroes_283().validMountainArray(new int[]{3, 7, 6, 4, 3, 0, 1, 0});
    }

}
