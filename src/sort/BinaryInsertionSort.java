package sort;

import java.util.Arrays;

/**
 * 二分插入排序
 * Created by lijianhua04 on 2020/3/2.
 */
public class BinaryInsertionSort {


    /**
     * 前半部分是排序完的，进行二分查找。
     */
    public static void sort(int[] nums) {

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];

            int k = findByBinary(nums, i);

            // 元素后移
            for (int j = i - 1; j >= k; j--) {
                nums[j + 1] = nums[j];
            }
            nums[k] = num;
        }

        System.out.println(Arrays.toString(nums));
    }

    private static int findByBinary(int[] nums, int index) {

        int high = index - 1;
        int low = 0;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (nums[mid] == nums[index]) {
                return mid;
            }
            if (nums[mid] < nums[index]) {
                low = mid + 1;
                continue;
            }
            if (nums[mid] > nums[index]) {
                high = mid - 1;
                continue;
            }
        }
        return low;
    }


    public static void main(String[] args) {
        int a[] = new int[]{1, 4, 6, 2, 0, 9, 7};
        System.out.println(findByBinary(a, 4));
        sort(a);
    }
}
