package leetcode.dp;

import leetcode.ArrayGenerator;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 有面值为 coins 的硬币，数量不限
 * 凑出amount的金额，使用最少的硬币数量，如果不存在返回-1;
 *
 * 面值为coins[i]的硬币，可能不会使用；如果使用，使用的数量也不一定。
 *
 */
public class CoinChange_322 {


    /**
     * 不过的case:
     * [186,419,83,408]
     * 6249
     * <p>
     * 可能某个面值的硬币，需要跳过（不使用），结果才能拼凑出amount；
     * 即：coins[i] 可能需要用到，可能不需要用到。
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {

        if (amount == 0) return 0;
        if (coins.length == 0) return -1;

        int ans = 0;
        Arrays.sort(coins);// O(N*longN)
        for (int i = coins.length - 1; i >= 0; i--) {
            ans += amount / coins[i];
            amount = amount % coins[i];
        }

        return amount != 0 ? -1 : ans;
    }


    /**
     * 并非每次，都需要直接使用j/coin[i]个
     * coin[i]币种，具体需要使用几个，要就行尝试
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange_dp0(int[] coins, int amount) {

        if (amount == 0) return 0;
        if (coins.length == 0) return -1;

        // dp[i][j]代表 使用前i个币种，凑出amount金额，需要的最少数量
        int[][] dp = new int[coins.length + 1][amount + 1];
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        dp[0][0] = 0;

        for (int j = 1; j < dp[0].length; j++) {
            if (j % coins[0] == 0) {
                dp[1][j] = j / coins[0];
            } else {
                dp[1][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 2; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                int newCoin = coins[i - 1];
                if (dp[i - 1][j % newCoin] == Integer.MAX_VALUE || newCoin > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j % newCoin] + j / newCoin);
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < dp.length; i++) {
            ans = Math.min(dp[i][amount], ans);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }


    //AC
    public static int coinChange_dp(int[] coins, int amount) {

        if (amount == 0) return 0;
        if (coins.length == 0) return -1;

        // dp[i][j]代表 使用前i个币种，凑出amount金额，需要的最少数量
        int[][] dp = new int[coins.length + 1][amount + 1];
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        dp[0][0] = 0;

        for (int j = 1; j < dp[0].length; j++) {
            if (j % coins[0] == 0) {
                dp[1][j] = j / coins[0];
            } else {
                dp[1][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 2; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                int newCoin = coins[i - 1];

                if (j < newCoin) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    int maxCount = j / newCoin;
                    int min = dp[i - 1][j - maxCount * newCoin] != Integer.MAX_VALUE ?
                            dp[i - 1][j - maxCount * newCoin] + maxCount : Integer.MAX_VALUE;
                    while (--maxCount > 0) {
                        min = Math.min(min, dp[i - 1][j - maxCount * newCoin] != Integer.MAX_VALUE ?
                                dp[i - 1][j - maxCount * newCoin] + maxCount : Integer.MAX_VALUE);
                    }
                    dp[i][j] = Math.min(dp[i - 1][j], min);
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < dp.length; i++) {
            ans = Math.min(dp[i][amount], ans);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }


    /**
     * f(n) = min{f(n-c1),f(n-c2),f(n-c3)……}+1
     * 用一位数组记录f(n)即可，n的范围1~amount
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange_dp1(int[] coins, int amount) {

        // dp[i]金额为i时，最小硬币数
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i < dp.length; i++) {
            int count = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j] && dp[i - coins[j]] != Integer.MAX_VALUE) {
                    count = Math.min(count, dp[i - coins[j]] + 1);
                }
            }
            dp[i] = count;
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(coinChange_dp1(new int[]{186, 419, 83, 408}, 6249));
        System.out.println(coinChange_dp1(new int[]{1, 2, 5}, 11));

        for (int i = 0; i < 400; i++) {
            int[] array = ArrayGenerator.generateRandomArray(10, 1, 100);

            int amount  = ThreadLocalRandom.current().nextInt(100,1000);
            if(coinChange_dp1(array,amount)!= coinChange_dp(array,amount)){
                System.out.println("xxxxx");
            }
        }


    }


}
