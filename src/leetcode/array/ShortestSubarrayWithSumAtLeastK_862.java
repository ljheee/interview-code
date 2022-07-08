package leetcode.array;


import leetcode.ArrayGenerator;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
 *
 * @author lijianhua.
 */
public class ShortestSubarrayWithSumAtLeastK_862 {


    public int shortestSubarray(int[] nums, int k) {

        if (nums == null || nums.length < 1) {
            return -1;
        }

        int size = 0;
        int[][] stack = new int[nums.length + 1][2];
        stack[0] = new int[]{0,0};

        int ans = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            int find = sum - k;
            int mostRight = mostRight(stack, size, find);
            if (mostRight >= 0) {
                ans = Math.min(ans, i - mostRight);
            }
            while (size > 0 && sum <= stack[size][0]) size--;
            stack[++size] = new int[]{sum, i};
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // 找出<=target的最右位置
    private int mostRight(int[][] stack, int size, int target) {
        int ans = -2;
        int left = 0;
        int right = size;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (stack[mid][0] <= target) {
                ans = stack[mid][1];
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }


    public int shortestSubarrayII(int[] nums, int k) {
        if (nums == null || nums.length < 1) {
            return -1;
        }

        int n = nums.length + 1;
        long[] preSum = new long[n];
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        int left = 0;
        int right = 0;
        // 双端队列，dequeQueue[i]保存的是累加和为preSum下标：累加和为preSum[i]
        int[] dequeQueue = new int[n];

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            while (left != right && preSum[i] <= preSum[dequeQueue[right-1]]) right--;
            while (left != right && preSum[i] - preSum[dequeQueue[left]] >= k) {
                ans = Math.min(ans, i - dequeQueue[left++]);
            }
            dequeQueue[right++] = i;
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }


    public static void main(String[] args) {
        ShortestSubarrayWithSumAtLeastK_862 sumAtLeast = new ShortestSubarrayWithSumAtLeastK_862();


        for (int i = 0; i < 1111110; i++) {

            int[] array = ArrayGenerator.generateRandomArray(ArrayGenerator.randomPositive(10), -10, 10);
            int k = ArrayGenerator.randomPositive(10);

            int i1 = sumAtLeast.shortestSubarray(array, k);
            int i2 = sumAtLeast.shortestSubarrayII(array, k);
            if (i1 != i2) {
                System.out.println(Arrays.toString(array) + ",k="+k+ ", " + i1 + ", " + i2);
            }


        }

        System.out.println(sumAtLeast.shortestSubarray(new int[]{-4, -4, 8}, 2));
        System.out.println(sumAtLeast.shortestSubarrayII(new int[]{8}, 1));
        System.out.println(sumAtLeast.shortestSubarrayII(new int[]{5}, 5));
    }


}
