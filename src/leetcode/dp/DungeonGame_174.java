package leetcode.dp;

/**
 * 地下城游戏
 * https://leetcode.com/problems/dungeon-game/
 */
public class DungeonGame_174 {


    /**
     * 从左上 到右下
     * dp[i][j] 到达i,j时，最大的剩余血量
     * —— 可能中间一格补充血量很多，结果就是这条线路
     *
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP(int[][] dungeon) {


        // dp[i][j] 到达i,j时，最大的剩余血量
        int[][] dp = new int[dungeon.length][dungeon[0].length];

        dp[0][0] = dungeon[0][0];
        for (int i = 1; i < dungeon.length; i++) {
            dp[i][0] = dp[i - 1][0] + dungeon[i][0];
        }
        for (int i = 1; i < dungeon[0].length; i++) {
            dp[0][i] = dp[0][i - 1] + dungeon[0][i];
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + dungeon[i][j];
            }
        }
        int c = dp[dungeon.length - 1][dungeon[0].length - 1];
        return c <= 0 ? 1 - c : 0;
    }


    /**
     * 隐含条件：每到一步，必须有血量
     * 从 右下 到左上，反推到达i,j时，需要的血量；
     * AC
     *
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP2(int[][] dungeon) {

        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) return 0;

        int n = dungeon.length - 1;
        int m = dungeon[0].length - 1;

        // dp[i][j] 到达i,j时，起始位置 需要的血量
        // 到达dp[i][j] 时，起始位置只能是上方或左方
        int[][] dp = new int[dungeon.length][dungeon[0].length];
        dp[dungeon.length - 1][dungeon[0].length - 1] = dungeon[dungeon.length - 1][dungeon[0].length - 1] >= 0 ? 1 : 1 - dungeon[dungeon.length - 1][dungeon[0].length - 1];

        // 填充最后一列
        for (int i = dungeon.length - 2; i >= 0; i--) {
            dp[i][m] = dungeon[i][m] >= dp[i + 1][m] ? 1 : dp[i + 1][m] - dungeon[i][m];
        }
        // 填充最后一行
        for (int j = dungeon[0].length - 2; j >= 0; j--) {
            dp[n][j] = dungeon[n][j] >= dp[n][j + 1] ? 1 : dp[n][j + 1] - dungeon[n][j];
        }

        for (int i = dungeon.length - 2; i >= 0; i--) {
            for (int j = dungeon[0].length - 2; j >= 0; j--) {
                int need = Math.min(dp[i][j + 1], dp[i + 1][j]);
                dp[i][j] = dungeon[i][j] >= need ? 1 : need - dungeon[i][j];
            }
        }

        return dp[0][0];
    }


}
