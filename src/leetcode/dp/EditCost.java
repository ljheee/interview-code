package leetcode.dp;

/**
 * https://leetcode.com/problems/edit-distance/
 */
public class EditCost {


    /**
     * 解析https://leetcode.com/problems/edit-distance/discuss/25846/C++-O(n)-space-DP/24826
     * <p>
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n][m];
        dp[0][0] = word1.charAt(0) == word2.charAt(0) ? 0 : 1;// 相等只需0步，不等执行replace

        for (int i = 1; i < dp.length; i++) {
            if (word1.charAt(i) == word2.charAt(0)) {
                dp[i][0] = i;
            } else {
                dp[i][0] = dp[i - 1][0] + 1;
            }
        }

        for (int i = 1; i < dp[0].length; i++) {
            if (word1.charAt(0) == word2.charAt(i)) {
                dp[0][i] = i;
            } else {
                dp[0][i] = dp[0][i - 1] + 1;
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 1,// replace
                            Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));// detete or insert
                }
            }
        }
        return dp[n - 1][m - 1];
    }


    /**
     * 最小编辑成本 http://www.jeepxie.net/article/826087.html
     *
     * @param word1
     * @param word2
     * @param ic    insert操作的成本
     * @param dc    delete操作的成本
     * @param rc    remove操作的成本
     * @return
     */
    public int minCost(String word1, String word2, int ic, int dc, int rc) {

        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        dp[0][0] = 0;// 两个都是""

        for (int i = 0; i < dp.length; i++) {
            // word1.charAt(i) == word2.charAt(0)
            dp[i][0] = dc * i;// word2是""，word1删除所有字符
        }

        for (int i = 0; i < dp[0].length; i++) {
            //word1.charAt(0) == word2.charAt(i)
            dp[0][i] = ic * i; // word1是""，word2所有字符要新增
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {

                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + rc,// replace
                            Math.min(dp[i - 1][j] + dc, dp[i][j - 1] + ic));// detete or insert
                }
            }
        }

        return dp[n][m];
    }


    public static void main(String[] args) {

    }
}
