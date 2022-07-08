package leetcode.dp;

import leetcode.ArrayGenerator;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/last-stone-weight-ii/
 * <p>
 * Same problem as:
 * Divide all numbers into two groups,
 * what is the minimum difference between the sum of two groups.
 * Now it's a easy classic knapsack problem.
 * https://leetcode.com/problems/last-stone-weight-ii/discuss/294888/JavaC%2B%2BPython-Easy-Knapsacks-DP
 */
public class LastStoneWeightII_1049 {


    /**
     * AC
     * @param stones
     * @return
     */
    public int lastStoneWeightII(int[] stones) {

        int sum = Arrays.stream(stones).sum();
        //任意使用0~i的石头，能否选出一个集合和为j
        boolean[][] dp = new boolean[stones.length + 1][sum + 1];
        dp[0][0] = true;
        for (int i = 0; i <= stones.length; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= stones.length; i++) {
            for (int j = 0; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - stones[i - 1] >= 0) {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - stones[i - 1]];
                }
            }
        }
        // 如果能选出和为half的集合，另一半集合则是sum-half
        // 最终要求，分成的这俩 集合，差最小
        int half = sum / 2;
        if ((sum & 1) == 0 && dp[stones.length][half]) {
            return 0;
        }

        int otherHalf = half;
        while (!dp[stones.length][half]) {
            half++;
        }
        while (!dp[stones.length][otherHalf]) {
            otherHalf--;
        }

        return Math.min(Math.abs(sum - 2 * half), Math.abs(sum - 2 * otherHalf));
    }
    // 空间还能优化


    public static void main(String[] args) {
        LastStoneWeightII_1049 stoneWeightII1049 = new LastStoneWeightII_1049();

        System.out.println(stoneWeightII1049.lastStoneWeightII(new int[]{7, 8, 4, 7, 8, 2, 1, 1}));
        System.out.println(stoneWeightII1049.lastStoneWeightII(new int[]{4, 7, 8, 2, 1, 1}));
        System.out.println(stoneWeightII1049.lastStoneWeightII(new int[]{8,2,4,4,8}));

//        int[] array = ArrayGenerator.generateRandomArray(23, 1, 12);
//        System.out.println(Arrays.toString(array));
//        System.out.println(stoneWeightII1049.lastStoneWeightII(array));
    }

}
