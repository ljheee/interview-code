package some;

import leetcode.ArrayGenerator;
import leetcode.array.TrappingRainWater_42;

import java.util.Arrays;

/**
 * 乱序数组，接雨水；
 * https://leetcode.com/problems/trapping-rain-water/
 */
public class CollectRain {


    /**
     * i位置的雨水= min{左侧最高，右侧最高}-arr[i]
     * <p>
     * 收集雨水
     * https://leetcode.com/problems/trapping-rain-water/
     *
     * @param arr
     * @return
     */
    public static int rain(int[] arr) {

        int n = arr.length - 1;
        int[] rightHighest = new int[arr.length];
        rightHighest[n] = arr[n];
        for (int i = arr.length - 2; i >= 0; i--) {
            rightHighest[i] = Math.max(arr[i], rightHighest[i+1]);
        }

        int ans = 0;
        int leftMax = arr[0];
        for (int i = 1; i < arr.length - 1; i++) {
            ans += Math.max(0, Math.min(leftMax, rightHighest[i + 1]) - arr[i]);
            if (arr[i] > leftMax) leftMax = arr[i];
        }

        return ans;
    }


    /**
     * 双指针
     * leftMax,rightMax
     * num[i]的瓶颈，是其中小的那个
     *
     * @param arr
     * @return
     */
    public static int rain2(int[] arr) {

        if (arr.length <= 2) return 0;
        int left = 1;
        int right = arr.length - 2;

        int ans = 0;
        int leftMax = arr[0];
        int rightMax = arr[arr.length - 1];

        while (left <= right) {
            if (leftMax <= rightMax) {
                ans += Math.max(0, leftMax - arr[left]);
                leftMax = Math.max(leftMax, arr[left]);
                left++;
            } else {
                ans += Math.max(0, rightMax - arr[right]);
                rightMax = Math.max(rightMax, arr[right]);
                right--;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] array = ArrayGenerator.generateRandomArray(100, 0, 10);
//        int[] array = {5, 0, 9, 0, 0, 6, 8, 7};
        System.out.println(Arrays.toString(array));
        TrappingRainWater_42 rainWater42 = new TrappingRainWater_42();
        System.out.println(rainWater42.trap(array));
        System.out.println(rain(array));
        System.out.println(rain2(array));
    }

}
