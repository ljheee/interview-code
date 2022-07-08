package leetcode.dp;

import leetcode.ArrayGenerator;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 有限制的 最大子序列和
 * https://leetcode.com/problems/constrained-subsequence-sum/
 * <p>
 * 用滑动窗口的方法，夹住长度为k的区间，找出区间最大值
 */
public class ConstrainedSubsequenceSum_1425 {

    /**
     * 最后一个case 超时
     * 因为O(n*k)
     * 需要更快速找出 长度为k的划窗，最大值
     *
     * @param nums
     * @param k
     * @return
     */
    public static int constrainedSubsetSum0(int[] nums, int k) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int ans = dp[0];
        for (int j = 1; j < dp.length; j++) {
            dp[j] = nums[j];
            for (int i = j - 1; i >= 0 && j - i <= k; i--) {
                if (dp[i] + nums[j] > nums[j]) dp[j] = Math.max(dp[j], dp[i] + nums[j]);
                ans = Math.max(ans, dp[j]);
            }
        }
        return ans;
    }

    public static int constrainedSubsetSum1(int[] nums, int k) {

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        priorityQueue.add(dp[0]);


        int ans = dp[0];
        for (int j = 1; j < dp.length; j++) {
            dp[j] = nums[j];
            dp[j] = priorityQueue.peek() + nums[j];

            if (priorityQueue.size() == k) {
                // TODO: 2020/9/6 划窗已满，如何知道该从 priorityQueue 移除哪个？priorityQueue.remove需要遍历；
            }

            for (int i = j - 1; i >= 0 && j - i <= k; i--) {
                if (dp[i] + nums[j] > nums[j]) dp[j] = Math.max(dp[j], dp[i] + nums[j]);
                ans = Math.max(ans, dp[j]);
            }
        }
        return ans;
    }


    /**
     * 参考 @{SlidingWindowMaximum_239}
     * <p>
     * AC
     *
     * @param nums
     * @param k
     * @return
     */
    public static int constrainedSubsetSum(int[] nums, int k) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(dp[0]);

        for (int i = 1; i < dp.length; i++) {
            dp[i] = Math.max(linkedList.peek() + nums[i], nums[i]);
            while (!linkedList.isEmpty() && dp[i] > linkedList.peekLast()) linkedList.pollLast();
            linkedList.addLast(dp[i]);
            if (!linkedList.isEmpty() && i - k >= 0 && dp[i - k] == linkedList.peek()) linkedList.removeFirst();
        }

        return Arrays.stream(dp).max().getAsInt();
    }

    public static void main(String[] args) {
        System.out.println(constrainedSubsetSum(new int[]{10, 2, -10, 5, 20}, 2));//37
        System.out.println(constrainedSubsetSum(new int[]{10, -2, -10, -5, 20}, 2));//23
        System.out.println(constrainedSubsetSum(new int[]{-1, -2, -3}, 1));//-1
        System.out.println(constrainedSubsetSum(new int[]{-5266, 4019, 7336, -3681, -5767}, 2));//11355


        // 比对通过
        for (int i = 0; i < 1010; i++) {
            int[] array = ArrayGenerator.generateRandomArray(8, -10, 9);
            int k = ThreadLocalRandom.current().nextInt(2, 5);
            if (constrainedSubsetSum0(array, k) != constrainedSubsetSum(array, k)) {
                System.out.println(String.format("%d,%d,%s,%d",
                        constrainedSubsetSum0(array, k),
                        constrainedSubsetSum(array, k),
                        Arrays.toString(array),
                        k));
            }
        }
    }
}