package leetcode.dp;

import leetcode.ArrayGenerator;

import java.util.*;

/**
 * 最长递增子序列
 * https://leetcode.com/problems/longest-increasing-subsequence/
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 */
public class LongestIncreasingSubsequence_300 {


    // 最长递增的子数组
    public int lengthOfIncreasingSubarray(int[] nums) {

        int[][] dp = new int[nums.length][nums.length];

        for (int i = dp.length - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < dp[0].length; j++) {
                if (nums[j - 1] < nums[j]) {
                    dp[i][j] = dp[i][j - 1] + 1;
                } else {
                    dp[i][j] = 1;
                }

            }
        }
        return dp[0][nums.length - 1];
    }


    public int lengthOfLIS(int[] nums) {

        int max = 0;
        int[][] dp = new int[nums.length][nums.length];
        int[][] dpMax = new int[nums.length][nums.length];

        for (int i = dp.length - 1; i >= 0; i--) {
            dp[i][i] = 1;
            dpMax[i][i] = nums[i];
            for (int j = i + 1; j < dp[0].length; j++) {
                if (dpMax[i][j - 1] < nums[j]) {
                    dp[i][j] = dp[i][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                    dpMax[i][j] = nums[j];
                } else {
                    dp[i][j] = dp[i][j - 1];
                    dpMax[i][j] = dpMax[i][j - 1];
                }

            }
        }
        return max;
    }


    class Node {
        int val;
        int idx;

        public Node(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    public int lengthOfLIS2(int[] nums) {

        List<Node> list = new ArrayList<Node>();
        for (int i = 0; i < nums.length; i++) {
            list.add(new Node(nums[i], i));
        }
        Collections.sort(list, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.val - o2.val;
            }
        });
        int count = 0;
        int pre = list.get(0).idx;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).idx > pre) {
                count++;
                pre = list.get(i).idx;
            }
        }
        return count;
    }



    /**
     * 思路路由： http://www.jeepxie.net/article/826087.html
     * dp[i] 记录0~i的最长子序列；
     * 以i结尾的子序列，前面小于nums[i]的均可作为 子序列前缀，具体采用哪个呢？
     * 找出 前缀最长的。
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < dp.length; i++) {
            int curMax = 0;
            // 从i往前找，找出最长的前缀
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    curMax = Math.max(curMax, dp[j]);
                }
            }
            dp[i] = curMax + 1;
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence_300 increasingSubsequence300 = new LongestIncreasingSubsequence_300();

//        System.out.println(increasingSubsequence300.lengthOfIncreasingSubarray(new int[]{2, 3, 5, 6}));
//        System.out.println(increasingSubsequence300.lengthOfLIS(new int[]{2, 3, 1, 5, 6}));
//        System.out.println(increasingSubsequence300.lengthOfLIS(new int[]{2, 3, 0, 5, 6}));

//        System.out.println(increasingSubsequence300.lengthOfLIS(new int[]{2, 3, 0, 1, 2, 3}));
//        System.out.println(increasingSubsequence300.lengthOfLIS(new int[]{2, 3, 0, 1, 5, 6}));
//        System.out.println(increasingSubsequence300.lengthOfLIS(new int[]{1, 2, 3, 0, 1, 5, 6}));
//        System.out.println(increasingSubsequence300.lengthOfLIS(new int[]{0, 2, 1, 3, 5, 6}));
//        System.out.println(increasingSubsequence300.lengthOfLIS(new int[]{5, 1, 2, 3, 5, 6}));
        System.out.println(increasingSubsequence300.lengthOfLIS(new int[]{5, 1, 1, 1, 1, 6}));


        // 全递减
        // 降低、升高、降低
        System.out.println(increasingSubsequence300.lengthOfLIS(new int[]{11, 11, 7, 6, 4}));//0
        System.out.println(increasingSubsequence300.lengthOfLIS(new int[]{5, 4, 14, 5, 9}));//3
        System.out.println(increasingSubsequence300.lengthOfLIS(new int[]{13, 2, 14, 4, 8}));//3
        System.out.println(increasingSubsequence300.lengthOfLIS(new int[]{7, 10, 9, 10, 13}));//4


        for (int i = 0; i < 100; i++) {
            int[] array = ArrayGenerator.generateRandomArray(5, 1, 15);
            if (increasingSubsequence300.lengthOfLIS2(array) !=
                    increasingSubsequence300.lengthOfLIS1(array)) {
                System.out.println("oops=" + Arrays.toString(array));
            }
        }
        System.out.println();
    }
}

