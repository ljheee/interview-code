package leetcode.dp;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/edit-distance/
 */
public class EditDistance_72 {


    public static int longestCommonSeq(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n][m];
        int[][] idx = new int[n][m];
        dp[0][0] = word1.charAt(0) == word2.charAt(0) ? 1 : 0;
        idx[0][0] = dp[0][0] == 1 ? 0 : -1;

        for (int i = 1; i < dp.length; i++) {
            if (word1.charAt(i) == word2.charAt(0)) {
                dp[i][0] = 1;
                idx[i][0] = i;
            } else {
                dp[i][0] = dp[i - 1][0];
                idx[i][0] = idx[i - 1][0];
            }
        }
        for (int i = 1; i < dp[0].length; i++) {
            if (word1.charAt(0) == word2.charAt(i)) {
                dp[0][i] = 1;
                idx[0][i] = 0;
            } else {
                dp[0][i] = dp[0][i - 1];
                idx[0][i] = idx[0][i - 1];
            }
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    idx[i][j] = idx[i - 1][j - 1];
                } else {
                    if (dp[i - 1][j] >= dp[i][j - 1]) {
                        dp[i][j] = dp[i - 1][j];
                        idx[i][j] = idx[i - 1][j];
                    } else {
                        dp[i][j] = dp[i][j - 1];
                        idx[i][j] = idx[i][j - 1];
                    }
//                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return idx[n - 1][m - 1];

    }

    /**
     * 解析https://leetcode.com/problems/edit-distance/discuss/25846/C++-O(n)-space-DP/24826
     *
     * 最小编辑成本 http://www.jeepxie.net/article/826087.html
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
                            Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));// insert or detete
                }
            }
        }
        return dp[n - 1][m - 1];
    }


    public static void main(String[] args) {
//        System.out.println(longestCommonSeq("abcdef", "ace"));//3
        System.out.println(longestCommonSeq("abcdef", "xce"));//2
        System.out.println(longestCommonSeq("abcdef", "xcex"));//2
        System.out.println(longestCommonSeq("abc", "def"));//0
        System.out.println(longestCommonSeq("abcdef", "fe"));//1
        System.out.println(longestCommonSeq("a", "a"));//1
        System.out.println(longestCommonSeq("abcdef", "fabc"));//3

        System.out.println(longestCommonSeq("lowercase", "aade"));//2


        System.out.println(longestCommonSeq("bsbininm", "jmjkbkjkv"));//1
        System.out.println(longestCommonSeq("bab", "xbx"));//1
        System.out.println(longestCommonSeq("babab", "xbxx"));//1

    }
}
