package leetcode.dp;

/**
 * 完全平方数
 * https://leetcode.com/problems/perfect-squares/submissions/
 */
public class PerfectSquares_279 {

    public int numSquares(int n) {
        if (n <= 3) return n;

        int m = (int) Math.sqrt(n);

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i < dp.length; i++) {
            dp[i][1] = i;
        }
        for (int j = 1; j < dp[0].length; j++) {
            dp[1][j] = 1;
        }


        for (int i = 2; i < dp.length; i++) {
            for (int j = 2; j < dp[0].length; j++) {
                int jj = j * j;
                if (jj > i) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    dp[i][j] = Math.min(i / jj + dp[i % jj][j], dp[i][j - 1]);
                }
            }
        }


        return dp[n][m];
    }

    public static void main(String[] args) {
        System.out.println(9 / 2 * 2);
    }
}
