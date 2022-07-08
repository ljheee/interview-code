package leetcode.array;

import java.util.*;

/**
 * 找出数组中第k大的元素
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 * <p>
 * <p>
 * 前k小、前k大问题，最优解应该是是bfprt算法
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/shu-zu-zhong-de-di-kge-zui-da-yuan-su-by-leetcode/
 * https://segmentfault.com/a/1190000008322873
 * <p>
 * <p>
 * 快速选择算法——BFPRT
 */
public class KthLargestElement {



    public static int findKthLargest0(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public static int findKthLargest(int[] nums, int k) {
        // 前K大，就是：length - k + 1 小
        return quickSort(nums, 0, nums.length - 1, nums.length - k + 1);
    }


    public static int quickSort(int[] data, int left, int right, int k) {
        int base = data[left];// 轴

        int begin = left;
        int end = right;

        while (begin < end) {

            //从后往前找，小于base的
            while (begin < end && data[end] >= base) {// 大于base的跳过
                end--;
            }
            // 说明找到了
            if (begin < end) {
                int temp = data[end];
                data[end] = data[begin];
                data[begin] = temp;
                begin++;
            }

            //从前往后找，大于base的
            while (begin < end && data[begin] <= base) {
                begin++;
            }
            // 说明找到了
            if (begin < end) {
                int temp = data[end];
                data[end] = data[begin];
                data[begin] = temp;
                end--;
            }
        }
        // 循环完一轮，数组被分成    小于base base 大于base 三段
        // 递归左边部分和右边部分

        // 此时begin 就是轴 的下标位置
        if (k == begin + 1) {
            return data[begin];
        } else if (k < begin + 1) {
            return quickSort(data, left, begin - 1, k);
        } else if (k > begin + 1) {
            //如果此时centre的位置比k前,递归地在其右边寻找
            return quickSort(data, begin + 1, right, k);
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(findKthLargest0(new int[]{3, 2, 1, 5, 6, 4}, 2));
        System.out.println(findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 5));
    }


}

class Solution {
    int[] nums;

    public void swap(int a, int b) {
        int tmp = this.nums[a];
        this.nums[a] = this.nums[b];
        this.nums[b] = tmp;
    }


    public int partition(int left, int right, int pivot_index) {
        int pivot = this.nums[pivot_index];
        // 1. move pivot to end
        swap(pivot_index, right);
        int store_index = left;

        // 2. move all smaller elements to the left
        for (int i = left; i <= right; i++) {
            if (this.nums[i] < pivot) {
                swap(store_index, i);
                store_index++;
            }
        }

        // 3. move pivot to its final place
        swap(store_index, right);

        return store_index;
    }

    public int quickselect(int left, int right, int k_smallest) {
    /*
    Returns the k-th smallest element of list within left..right.
    */

        if (left == right) // If the list contains only one element,
            return this.nums[left];  // return that element

        // select a random pivot_index
        Random random_num = new Random();
        int pivot_index = left + random_num.nextInt(right - left);

        pivot_index = partition(left, right, pivot_index);

        // the pivot is on (N - k)th smallest position
        if (k_smallest == pivot_index)
            return this.nums[k_smallest];
            // go left side
        else if (k_smallest < pivot_index)
            return quickselect(left, pivot_index - 1, k_smallest);
        // go right side
        return quickselect(pivot_index + 1, right, k_smallest);
    }

    /**
     * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/shu-zu-zhong-de-di-kge-zui-da-yuan-su-by-leetcode/
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        this.nums = nums;
        int size = nums.length;
        // kth largest is (N - k)th smallest
        return quickselect(0, size - 1, size - k);
    }
}