package leetcode.dp;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximal-square/
 * <p>
 * <p>
 * dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1;
 * https://leetcode.com/problems/maximal-square/discuss/61802/Extremely-Simple-Java-Solution-%3A)
 */
public class MaximalSquare_221 {


    public int maximalSquare0(char[][] matrix) {

        if (matrix == null || matrix.length == 0) return 0;

        // dp[i][j] 以i,j为y右下角的点，能够形成的z最大矩阵
        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];

        int ans = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {

                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = 1;
                    if (dp[i - 1][j] == dp[i][j - 1] && dp[i - 1][j - 1] > 0) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        for (int i = 1; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return ans * ans;
    }


    public int maximalSquare1(char[][] matrix) {

        if (matrix == null || matrix.length == 0) return 0;

        // dp[i][j] 以i,j为y右下角的点，能够形成的z最大矩阵
        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];

        int ans = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {

                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = 1;
                    if (dp[i - 1][j] == dp[i][j - 1] && dp[i - 1][j - 1] > 0) {

                        dp[i][j] = dp[i - 1][j - 1] + 1;

                    } else if (dp[i - 1][j] > 0 && dp[i][j - 1] > 0) {
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                    }
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        for (int i = 1; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return ans * ans;
    }


    /**
     * AC
     * <p>
     * 输入
     * [["1","1","1","1","0"],
     * ["1","1","1","1","0"],
     * ["1","1","1","1","1"],
     * ["1","1","1","1","1"],
     * ["0","0","1","1","1"]]
     * <p>
     * 输出
     * [0, 1, 1, 1, 1, 0]
     * [0, 1, 2, 2, 2, 0]
     * [0, 1, 2, 3, 3, 1]
     * [0, 1, 2, 3, 4, 2]
     * [0, 0, 0, 1, 2, 3]
     *
     * @param matrix
     * @return
     */
    public int maximalSquare2(char[][] matrix) {

        if (matrix == null || matrix.length == 0) return 0;

        // dp[i][j] 以i,j为右下角的点，能够形成的最大矩阵
        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];

        int ans = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {

                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = 1;
                    if (dp[i - 1][j] == dp[i][j - 1]) {

                        if (dp[i - 1][j] == dp[i - 1][j - 1]) dp[i][j] = dp[i - 1][j - 1] + 1;

                        if (dp[i - 1][j] > dp[i - 1][j - 1]) dp[i][j] = dp[i - 1][j];

                        if (dp[i - 1][j] < dp[i - 1][j - 1]) dp[i][j] = dp[i - 1][j] + 1;
                    } else if (dp[i - 1][j] > 0 && dp[i][j - 1] > 0 && dp[i - 1][j - 1] > 0) {
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                    }
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        for (int i = 1; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return ans * ans;
    }

}
