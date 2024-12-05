package leetcode.dp;

import java.util.Arrays;

/**
 * Created by lijianhua04 on 2020/9/25.
 */
public class LargestPlusSign_764 {


    public int orderOfLargestPlusSign(int N, int[][] mines) {

        int[][] matrix = new int[N][N];
        for (int[] row : matrix) {
            Arrays.fill(row, 1);
        }
        for (int[] mine : mines) {
            matrix[mine[0]][mine[1]] = 0;
        }
        // dp[i][j] 以i,j为y右下角的点，能够形成的z最大矩阵
        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];

        int ans = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                ans = Math.max(ans, dp[i][j]);

                if (matrix[i - 1][j - 1] == 0) {
                    dp[i][j] = 0;
                }
            }
        }

        return (ans - 1) / 2 + 1;
    }


    public int orderOfLargestPlusSign1(int N, int[][] mines) {

        int[][] matrix = new int[N][N];
        for (int[] row : matrix) {
            Arrays.fill(row, 1);
        }
        for (int[] mine : mines) {
            matrix[mine[0]][mine[1]] = 0;
        }

        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        // dp[i][j] 以i,j为y右下角的点，能够形成的z最大矩阵
        int[][] left = new int[matrix.length + 1][matrix[0].length + 1];
        int[][] up = new int[matrix.length + 1][matrix[0].length + 1];

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (matrix[i - 1][j - 1] == 1) {
                    left[i][j] = left[i][j - 1] + 1;
                    up[i][j] = up[i - 1][j] + 1;
                    ans = 1;
                }
            }
        }
        for (int[] row : left) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
        for (int[] row : up) {
            System.out.println(Arrays.toString(row));
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {

                int leftLen = left[i][j];
                int mirrorJ = (j - leftLen + 1 + j) / 2;
                if (i + j - mirrorJ <= N && up[i + j - mirrorJ][mirrorJ] >= leftLen) {
                    ans = Math.max(ans, leftLen);
                }
                int upLen = up[i][j];
                int mirrorI = (i - upLen + 1 + i) / 2;
                if (i + j - mirrorI <= N && left[mirrorI][i + j - mirrorI] >= upLen) {
                    ans = Math.max(ans, upLen);
                }
                ans = Math.max(ans, Math.min(Math.min(left[i][j], up[j][i]), Math.min(up[i][j], left[j][i])));


            }
        }

        System.out.println(ans);
        return ans > 0 ? (ans - 1) / 2 + 1 : ans;
    }
}
