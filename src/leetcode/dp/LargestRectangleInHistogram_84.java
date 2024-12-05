package leetcode.dp;

import leetcode.ArrayGenerator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * 柱状图中最大的矩形
 * https://leetcode.com/problems/largest-rectangle-in-histogram/submissions/
 * Created by lijianhua04 on 2020/10/2.
 */
public class LargestRectangleInHistogram_84 {


    /**
     * AC 暴力
     * Runtime: 1366 ms, faster than 5.00% of Java online submissions for Largest Rectangle in Histogram.
     * Memory Usage: 45.2 MB, less than 9.60% of Java online submissions for Largest Rectangle in Histogram.
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int[] dp = new int[heights.length + 1];

        int left = 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = heights[i - 1];

            if (heights[i - 1] == 0) {
                dp[i] = 0;
                left = i - 1;
                continue;
            }

            if (dp[i - 1] == 0) {
                dp[i] = heights[i - 1];
                left = i - 1;
                continue;
            }

            int pointer = i - 1;
            int curMin = dp[i];
            int step = 1;
            while (pointer >= left) {
                curMin = Math.min(curMin, heights[pointer]);
                dp[i] = Math.max(dp[i], curMin * step);
                pointer--;
                step++;
            }
        }

        System.out.println(Arrays.toString(dp));
        return Arrays.stream(dp).max().getAsInt();
    }


    /**
     * 找出 i 位置两边，比heights[i]小的第一个数
     * Runtime: 27 ms, faster than 21.80% of Java online submissions for Largest Rectangle in Histogram.
     * Memory Usage: 41.3 MB, less than 12.60% of Java online submissions for Largest Rectangle in Histogram.
     * -------------------------------------------------------------------------------------
     * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/comments/419044
     * 使用 left 和 right 数组记录以 i 柱子为基准，从 i 开始向左右两边延伸的第一根比 i 小的柱子
     * 那么，在 （left[i], right[i]) (注意是小括号，即不包括边界)之间的柱子都是比 i 柱子小的
     * 那么 i 柱子能够围成的面积宽度是 width = right[i] - left[i] - 1
     * 面积则为 heights[i] * width
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea1(int[] heights) {
        int[] leftLess = new int[heights.length];
        int[] rightLess = nextSmallerIndex(heights);
        Arrays.fill(leftLess, -1);
        Stack<Integer> stack = new Stack<>();

        int[] clone = heights.clone();
        List<Integer> collect = Arrays.stream(clone).boxed().collect(Collectors.toList());
        Collections.reverse(collect);
        clone = collect.stream().mapToInt(Integer::intValue).toArray();
        for (int i = 0; i < clone.length; i++) {
            int num = clone[i];
            while (!stack.isEmpty() && clone[stack.peek()] > num) {
                leftLess[stack.pop()] = i;
            }
            stack.push(i);
        }

        List<Integer> collect2 = Arrays.stream(leftLess).boxed().collect(Collectors.toList());
        Collections.reverse(collect2);
        leftLess = collect2.stream().mapToInt(Integer::intValue).toArray();

        System.out.println(Arrays.toString(leftLess));
        System.out.println(Arrays.toString(rightLess));

        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            if (leftLess[i] > 0) leftLess[i] = heights.length - 1 - leftLess[i];
            int width = rightLess[i] - leftLess[i] - 1;
            max = Math.max(max, width * heights[i]);
        }
        return max;
    }

    public int[] nextSmallerIndex(int[] nums) {
        int[] result = new int[nums.length];
        Arrays.fill(result, nums.length);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            while (!stack.isEmpty() && nums[stack.peek()] > num) {
                result[stack.pop()] = i;
            }
            stack.push(i);
        }
        return result;
    }

    /**
     * Runtime: 9 ms, faster than 34.60% of Java online submissions for Largest Rectangle in Histogram.
     * Memory Usage: 40.6 MB, less than 35.07% of Java online submissions for Largest Rectangle in Histogram.
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea2(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;
        }

        if (len == 1) {
            return heights[0];
        }
        int[] dp = new int[heights.length];

        // 存储下标
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int idx = stack.pop();
                if (stack.isEmpty()) {
                    dp[idx] = heights[idx] * i;
                } else {
                    dp[idx] = heights[idx] * (i - stack.peek() - 1);
                }
            }
            stack.add(i);
        }

        while (!stack.isEmpty()) {
            int idx = stack.pop();
            if (stack.isEmpty()) {
                dp[idx] = heights[idx] * heights.length;
            } else {
                //dp[idx] = heights[idx] * (heights.length - idx);
                dp[idx] = heights[idx] * (heights.length - stack.peek() - 1);
            }
        }

        return Arrays.stream(dp).max().getAsInt();
    }


    public int[] kWeakestRows(int[][] mat, int k) {

        List<Long> collect = Arrays.stream(mat).map(row -> Arrays.stream(row).filter(i -> i == 1).count()).collect(Collectors.toList());
        for (int i = 0; i < collect.size(); i++) {

        }
        return null;
    }

    public static void main(String[] args) {
        LargestRectangleInHistogram_84 largestRectangleInHistogram84 = new LargestRectangleInHistogram_84();

        System.out.println(largestRectangleInHistogram84.largestRectangleArea2(new int[]{2, 2, 3, 6, 5, 6}));
//        System.out.println(largestRectangleInHistogram84.largestRectangleArea2(new int[]{1, 6, 0, 9, 5, 1}));
//        System.out.println(largestRectangleInHistogram84.largestRectangleArea2(new int[]{8, 3, 3, 1, 3, 5}));
//        System.out.println(largestRectangleInHistogram84.largestRectangleArea2(new int[]{0, 7, 9, 3, 3, 5}));
//        System.out.println(largestRectangleInHistogram84.largestRectangleArea1(new int[]{2, 1, 5, 6, 2, 3}));
//        System.out.println(largestRectangleInHistogram84.largestRectangleArea1(new int[]{2, 1, 5, 6, 2, 3}));
//        System.out.println(largestRectangleInHistogram84.largestRectangleArea1(new int[]{2, 1, 0, 0, 5, 6, 2, 3}));
//
//
        for (int i = 0; i < 0; i++) {

            int[] array = ArrayGenerator.generateRandomArray(6, 0, 10);

            if (largestRectangleInHistogram84.largestRectangleArea1(array) !=
                    largestRectangleInHistogram84.largestRectangleArea2(array)) {

                System.out.println(Arrays.toString(array));
            }

        }
    }

}
