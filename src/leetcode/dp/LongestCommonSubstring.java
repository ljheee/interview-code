package leetcode.dp;

import leetcode.StringGenerator;

/**
 * 最长公共子串
 * 例如 bbac,ba 返回2
 *
 */
public class LongestCommonSubstring {


    public static int longestCommonSubstring_recursive(String s1, String s2) {
        return lcs(s1, s2, 0);
    }

    private static int lcs(String s1, String s2, int c) {
        if (s1.length() == 0 || s2.length() == 0) return c;

        if (s1.charAt(0) == s2.charAt(0)) {
            c = lcs(s1.substring(1), s2.substring(1), c + 1);
        } else {
            c = Math.max(c, Math.max(lcs(s1, s2.substring(1), 0),
                    lcs(s1.substring(1), s2, 0)));
        }
        return c;
    }


    public static int longestCommonSubstring_dp(String s1, String s2) {

        if (s1.length() == 0 || s2.length() == 0) return 0;

        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        int max = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    max = Math.max(max, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return max;
    }

    /**
     * https://www.geeksforgeeks.org/longest-common-substring-dp-29/
     *
     * @param X
     * @param Y
     * @param m
     * @param n
     * @return
     */
    static int LCSubStr(char X[], char Y[], int m, int n) {
        int LCStuff[][] = new int[m + 1][n + 1];
        int result = 0;  // To store length of the longest common substring

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    LCStuff[i][j] = 0;
                else if (X[i - 1] == Y[j - 1]) {
                    LCStuff[i][j] = LCStuff[i - 1][j - 1] + 1;
                    result = Integer.max(result, LCStuff[i][j]);
                } else
                    LCStuff[i][j] = 0;
            }
        }
        return result;
    }


    private static int longestCommonSubstring(String s1, String s2) {
        if ("".equals(s1) || "".equals(s2)) return 0;
        if (s1.equals(s2)) return s1.length();


        int[][] dp = new int[s1.length()][s2.length()];
        dp[0][0] = s1.charAt(0) == s2.charAt(0) ? 1 : 0;

        // 初始化时，也需考虑max结果
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = s1.charAt(i) == s2.charAt(0) ? 1 : 0;
        }

        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] = s1.charAt(0) == s2.charAt(j) ? 1 : 0;
        }

        int max = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    max = Math.max(max, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {


        for (int i = 0; i < 210; i++) {
            String s1 = StringGenerator.generateRandomLowerCase(7);
            String s2 = StringGenerator.generateRandomLowerCase(9);
            if (longestCommonSubstring(s1, s2) != LCSubStr(s1.toCharArray(), s2.toCharArray(), s1.length(), s2.length())) {

                int res = LCSubStr(s1.toCharArray(), s2.toCharArray(), s1.length(), s2.length());
                System.out.println(String.format("%s,%s,%d,%d", s1, s2, longestCommonSubstring(s1, s2), res));
            }
        }
    }


}
