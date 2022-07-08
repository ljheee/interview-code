package leetcode.dp;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/cherry-pickup/
 * 来回 各一次，摘最多的桃子;
 * <p>
 * 难点：正向走的那一遍，需要把摘了桃子的地方擦除
 * 找出两条 桃子最多的路
 *
 *
 * 这不是华为机测题嘛
 *
 */
public class CherryPickup_741 {

    /**
     * 不能分别解去程和回程再求和，必须综合考虑
     *
     * @param grid
     * @return
     */
    public int cherryPickup(int[][] grid) {
        int ans = maxPick(grid);
        // 翻转
        int n = grid.length - 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                int axisI = n - j;
                int axisJ = n - i;
                int temp = grid[i][j];
                grid[i][j] = grid[axisI][axisJ];
                grid[axisI][axisJ] = temp;
            }
        }
        ans += maxPick(grid);
        return Math.max(ans, 0);
    }

    /**
     * 注释的代码是，擦除摘过的蟠桃
     * @param grid
     * @return
     */
    private int maxPick(int[][] grid) {
        int n = grid.length;

        int[][] dp = new int[n][n];
        dp[0][0] = grid[0][0];
//        grid[0][0]= 0;

        for (int i = 1; i < dp.length; i++) {
            if (grid[i][0] < 0) break;
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for (int j = 1; j < dp[0].length; j++) {
            if (grid[0][j] < 0) break;
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
//                if (i == 1 && j == 1) {
//                    if (grid[0][1] >= grid[1][0]) {
//                        grid[0][1] = 0;
//                    } else {
//                        grid[1][0] = 0;
//                    }
//                }

                if (grid[i][j] < 0) {
                    dp[i][j] = grid[i][j];
                    continue;
                }
                int pre = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (pre < 0) return 0;
                dp[i][j] = pre + grid[i][j];
//                grid[i][j]=0;
            }
        }
        return dp[n - 1][n - 1];
    }


    /**
     * https://leetcode-cn.com/problems/cherry-pickup/solution/zhai-ying-tao-by-leetcode/
     * 正方形矩阵，两个人同时从0,0开始，移到相同的步数，一定在对角线上；
     * x1+y1=x2+y2
     * 简化成3维
     *
     */
    class Solution {
        int[][][] memo;
        int[][] grid;
        int N;
        public int cherryPickup(int[][] grid) {
            this.grid = grid;
            N = grid.length;
            memo = new int[N][N][N];
            for (int[][] layer: memo)
                for (int[] row: layer)
                    Arrays.fill(row, Integer.MIN_VALUE);
            return Math.max(0, dp(0, 0, 0));
        }
        public int dp(int r1, int c1, int c2) {
            int r2 = r1 + c1 - c2;
            if (N == r1 || N == r2 || N == c1 || N == c2 ||
                    grid[r1][c1] == -1 || grid[r2][c2] == -1) {
                return -999999;
            } else if (r1 == N-1 && c1 == N-1) {
                return grid[r1][c1];
            } else if (memo[r1][c1][c2] != Integer.MIN_VALUE) {
                return memo[r1][c1][c2];
            } else {
                int ans = grid[r1][c1];
                if (c1 != c2) ans += grid[r2][c2];
                ans += Math.max(Math.max(dp(r1, c1+1, c2+1), dp(r1+1, c1, c2+1)),
                        Math.max(dp(r1, c1+1, c2), dp(r1+1, c1, c2)));
                memo[r1][c1][c2] = ans;
                return ans;
            }
        }
    }

}
