package leetcode.dp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lijianhua04 on 2020/8/29.
 */
public class FindTwoNonoverlappingSubarraysEachWithTargetSum_1477 {


    public int minSumOfLengths0(int[] arr, int target) {

        int[] preSum = new int[arr.length + 1];

        // dp[i] 以i位置开始的子数组，形成target和的子数组长度
        int[] dp = new int[arr.length + 1];
        Arrays.fill(dp, -1);

        for (int i = 0; i < arr.length; i++) {
            preSum[i + 1] = preSum[i] + arr[i];
            if (arr[i] == target) dp[i + 1] = 1;
        }

        // min & second
        int[] ans = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        for (int i = 1; i < dp.length; i++) {
            if (dp[i] == 1) {
                setValue(dp[i], ans);
                continue;
            }
            if (preSum[i] - target < 0) continue;

            int j = Arrays.binarySearch(preSum, 0, i, preSum[i] - target);

            if (j >= 0) {
                dp[i] = i - j;
                setValue(dp[i], ans);
            }
        }
        return ans[1] == Integer.MAX_VALUE ? -1 : ans[0] + ans[1];
    }

    /**
     * 不过的case
     * 1, 1, 1, 2, 2, 2, 4, 4 ; target=6
     *
     * @param arr
     * @param target
     * @return
     */
    public static int minSumOfLengths1(int[] arr, int target) {

        int[] preSum = new int[arr.length + 1];

        // dp[i] 以i位置结束的子数组，形成target和的子数组长度
        int[] dp = new int[arr.length + 1];
        Arrays.fill(dp, -1);

        for (int i = 0; i < arr.length; i++) {
            preSum[i + 1] = preSum[i] + arr[i];
            if (arr[i] == target) dp[i + 1] = 1;
        }

        // min & second
        int[] ans = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        for (int i = 1; i < dp.length; i++) {
            if (dp[i] == 1) continue;
            if (preSum[i] - target < 0) continue;
            int j = Arrays.binarySearch(preSum, 0, i, preSum[i] - target);

            // overlapping
            if (j >= 0 && j < i - 1 && dp[i - 1] > 0) {
                if (dp[i - 1] > i - j) {
                    dp[i - 1] = -1;
                    dp[i] = i - j;
                } else {
                    dp[i] = -1;
                }

                continue;
            }
            if (j >= 0) dp[i] = i - j;
        }
        for (int i = 1; i < dp.length; i++) {
            if (dp[i] > 0) setValue(dp[i], ans);
        }
        return ans[1] == Integer.MAX_VALUE ? -1 : ans[0] + ans[1];
    }

    private static void setValue(int val, int[] ans) {
        int min = ans[0];
        int second = ans[1];
        second = Math.min(val, second);
        if (min > second) {
            int temp = second;
            second = min;
            min = temp;
        }
        ans[0] = min;
        ans[1] = second;
    }

    /**
     * 按tips实现;
     * <p>
     * O(N * logN)
     * 我们用二分搜索查找 和等于target的子数组起始位置，但是考虑到前缀和是单调的，通过滑动窗口的方法可以在O(n)的复杂度内得到所有和为target的子数组。
     *
     * @param arr
     * @param target
     * @return
     */
    public static int minSumOfLengths(int[] arr, int target) {


        // dp[i] 以i位置结束的子数组，形成sum=target和的子数组最小长度
        int[] left = build(arr, target);
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        Collections.reverse(list);
        int[] right = build(list.stream().mapToInt(Integer::intValue).toArray(), target);
        List<Integer> list2 = Arrays.stream(right).boxed().collect(Collectors.toList());
        Collections.reverse(list2);
        right = list2.stream().mapToInt(Integer::intValue).toArray();

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < left.length - 1; i++) {
            if (left[i] == Integer.MAX_VALUE || right[i] == Integer.MAX_VALUE) continue;
            ans = Math.min(ans, left[i] + right[i]);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int[] build(int[] arr, int target) {
        // dp[i] 以i位置结束的子数组，形成sum=target和的子数组最小长度
        int[] dp = new int[arr.length + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        int[] preSum = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            preSum[i + 1] = preSum[i] + arr[i];
        }
        for (int i = 1; i < dp.length; i++) {
            if (arr[i - 1] == target) {
                dp[i] = 1;
                continue;
            }

            if (preSum[i] - target < 0) continue;
            int j = Arrays.binarySearch(preSum, 0, i, preSum[i] - target);
            if (j < 0) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = Math.min(dp[i - 1], i - j);
            }
        }
        return dp;
    }


    private static int[] build2(int[] arr, int target) {
        // dp[i] 以i位置结束的子数组，形成sum=target和的子数组最小长度
        int[] dp = new int[arr.length + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        int[] preSum = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            preSum[i + 1] = preSum[i] + arr[i];
        }
        int left = 0;
        int right = 1;
        for (; right < dp.length; right++) {
            dp[right] = dp[right - 1];
            if (preSum[right] - preSum[left] < target) {
                continue;
            }
            if (preSum[right] - preSum[left] == target) dp[right] = Math.min(dp[right], right - left);
            while (preSum[right] - preSum[left] > target) {
                left++;
                if (preSum[right] - preSum[left] == target) dp[right] = Math.min(dp[right], right - left);
            }
        }
        return dp;
    }


    public static void main(String[] args) {
        System.out.println(minSumOfLengths(new int[]{1, 1, 1, 2, 2, 2, 4, 4}, 6));
        System.out.println(minSumOfLengths(new int[]{1, 6, 1, 6}, 7));

        System.out.println(minSumOfLengths(new int[]{1, 6, 1}, 7));
        System.out.println(minSumOfLengths(new int[]{6, 1, 6}, 7));

        System.out.println(Arrays.toString(build(new int[]{3, 2, 2, 4, 3}, 3)));
        System.out.println(Arrays.toString(build2(new int[]{3, 2, 2, 4, 3}, 3)));

        System.out.println(Arrays.toString(build(new int[]{4, 3, 2, 6, 2, 3, 4}, 3)));
        System.out.println(Arrays.toString(build2(new int[]{4, 3, 2, 6, 2, 3, 4}, 3)));
    }

}
