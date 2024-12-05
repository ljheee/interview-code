package some;

import java.util.HashSet;

/**
 * 给定一个非负数组arr，和一个正数m。
 * 返回arr的所有子序列中累加和%m之后的最大值。
 */
public class MaxNumberSubsequenceModM {


    /**
     * 暴力法
     * 先生成所有子序列，在分别对m取模，得到最大值
     *
     * @param arr
     * @param m
     * @return
     */
    public static int subsequenceModM_force(int[] arr, int m) {
        HashSet<Integer> set = new HashSet<>();
        generateSubArray(arr, 0, 0, set);

        int max = 0;
        for (Integer i : set) {
            max = Math.max(max, i % m);
        }
        return max;
    }

    private static void generateSubArray(int[] arr, int from, int sum, HashSet<Integer> set) {
        if (from == arr.length) {
            set.add(sum);
        } else {
            generateSubArray(arr, from + 1, sum, set);
            generateSubArray(arr, from + 1, sum + arr[from], set);
        }
    }

    /**
     * dp解法
     * 背包的思路，arr中的每个数，要么选要么不选，嫩否得到
     *
     * @param arr
     * @param m
     * @return
     */
    public static int subsequenceModM(int[] arr, int m) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        /**
         * dp[i][j]
         * 任意使用0~i能否得到累加和j
         */
        boolean[][] dp = new boolean[arr.length][sum + 1];

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }
        dp[0][arr[0]] = true;

        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < sum; j++) {
                dp[i][j] = dp[i - 1][j];// 不使用arr[i]，让0~i-1完成任务
                if (j - arr[i] >= 0) {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - arr[i]];//使用arr[i]，让0~i-1完成j-arr[i]的任务
                }
            }
        }

        int max = 0;
        for (int j = 0; j < dp[0].length; j++) {
            if (dp[arr.length - 1][j]) {
                max = Math.max(max, j % m);
            }
        }
        return max;
    }


    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 3, 7, 3};
        int m = 4;
        System.out.println(subsequenceModM_force(arr, m));
        System.out.println(subsequenceModM(arr, m));
    }

}
