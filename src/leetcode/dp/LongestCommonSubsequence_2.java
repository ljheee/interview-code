package leetcode.dp;

import leetcode.StringGenerator;

/**
 * 找出两个字符串的 最长公共子序列
 * https://leetcode.com/problems/longest-common-subsequence/
 * https://leetcode-cn.com/problems/longest-common-subsequence/solution/1143-zui-chang-gong-gong-zi-xu-lie-by-ming-zhi-sha/
 */
public class LongestCommonSubsequence_2 {

    /**
     * 递归
     *
     * @param s1
     * @param s2
     * @return
     */
    private int longestCommonSubsequence0(String s1, String s2) {
        if ("".equals(s1) || "".equals(s2)) {
            return 0;
        }
        if (s1.equals(s2)) {
            return s1.length();
        }

        if (s1.charAt(s1.length() - 1) == s2.charAt(s2.length() - 1)) {
            return 1 + longestCommonSubsequence0(s1.substring(0, s1.length() - 1), s2.substring(0, s2.length() - 1));
        } else {
            return Math.max(longestCommonSubsequence0(s1.substring(0, s1.length() - 1), s2),
                    longestCommonSubsequence0(s1, s2.substring(0, s2.length() - 1)));
        }
    }


    private int longestCommonSubsequence_dp(String s1, String s2) {

        int[][] dp = new int[s1.length()][s2.length()];

        dp[0][0] = s1.charAt(0) == s2.charAt(0) ? 1 : 0;
        for (int i = 1; i < dp[0].length; i++) {
            if (s1.charAt(0) == s2.charAt(i)) {
                dp[0][i] = 1;
            } else {
                dp[0][i] = dp[0][i - 1];
            }
        }

        for (int i = 1; i < dp.length; i++) {
            if (s2.charAt(0) == s1.charAt(i)) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[s1.length() - 1][s2.length() - 1];
    }


    public static int lcs(String s1, String s2) {
        if (s1 == null || s1.length() == 0) return 0;
        if (s2 == null || s2.length() == 0) return 0;


        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1] + 1;
            }
        }

        return dp[s1.length()][s2.length()];
    }


    public static void main(String[] args) {
        LongestCommonSubsequence_2 subsequence1143 = new LongestCommonSubsequence_2();
        System.out.println(subsequence1143.longestCommonSubsequence_dp("abcdef", "ace"));//3
        System.out.println(subsequence1143.longestCommonSubsequence_dp("abcdef", "xce"));//2
        System.out.println(subsequence1143.longestCommonSubsequence_dp("abcdef", "xcex"));//2
        System.out.println(subsequence1143.longestCommonSubsequence0("abc", "def"));//0
        System.out.println(subsequence1143.longestCommonSubsequence0("abcdef", "fe"));//1
        System.out.println(subsequence1143.longestCommonSubsequence0("a", "a"));//1
        System.out.println(subsequence1143.longestCommonSubsequence0("abcdef", "fabc"));//3

        System.out.println(subsequence1143.longestCommonSubsequence_dp("lowercase", "aade"));//2


        System.out.println(subsequence1143.longestCommonSubsequence_dp("bsbininm", "jmjkbkjkv"));//1
        System.out.println(subsequence1143.longestCommonSubsequence_dp("bab", "xbx"));//1
        System.out.println(subsequence1143.longestCommonSubsequence_dp("babab", "xbxx"));//1




        for (int i = 0; i < 10000; i++) {
            String s1 = StringGenerator.generateRandomLowerCase(10);
            String s2 = StringGenerator.generateRandomLowerCase(10);
            if (subsequence1143.longestCommonSubsequence_dp(s1, s2) != lcs(s1, s2)) {
                System.out.println("oops=" + s1 + "=" + s2);
            }
        }


    }



}

