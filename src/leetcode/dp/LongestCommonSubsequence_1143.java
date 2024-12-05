package leetcode.dp;

import leetcode.StringGenerator;

/**
 * 找出两个字符串的 最长公共子序列
 * https://leetcode.com/problems/longest-common-subsequence/
 * https://leetcode-cn.com/problems/longest-common-subsequence/solution/1143-zui-chang-gong-gong-zi-xu-lie-by-ming-zhi-sha/
 */
public class LongestCommonSubsequence_1143 {


    public int longestCommonSubsequence0(String text1, String text2) {
        if (text1.length() < text2.length()) {
            return longestCommonSubsequence0(text2, text1);
        }
        int ans = 0;

        char[] chars = text2.toCharArray();
        int preIdx = -1;
        int curIdx = -1;
        for (int i = 0; i < chars.length; i++) {
            if ((curIdx = text1.indexOf(chars[i])) > preIdx) {
                preIdx = curIdx;
                ans++;
            }
        }
        return ans;
    }


    // 递归解法
    public int longestCommonSubsequence_re(String text1, String text2) {

        if(text1.length() == 0 || text2.length()==0) return 0;
        if (text1.charAt(text1.length() - 1) == text2.charAt(text2.length() - 1)) {
            return 1 + longestCommonSubsequence_re(text1.substring(0, text1.length() - 1), text2.substring(0, text2.length() - 1));
        } else {
            return Math.max(longestCommonSubsequence_re(text1.substring(0, text1.length() - 1), text2),
                    longestCommonSubsequence_re(text1, text2.substring(0, text2.length() - 1)));
        }
    }

    // 递归:从头开始匹配
    public static int longestCommonSubseq(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) return 0;
        if (s1.equals(s2)) return s1.length();

        if (s1.charAt(0) == s2.charAt(0)) {
            return 1 + longestCommonSubseq(s1.substring(1), s2.substring(1));
        } else {
            return Math.max(longestCommonSubseq(s1, s2.substring(1)),
                    longestCommonSubseq(s1.substring(1), s2));
        }
    }


    public int longestCommonSubsequence_dp(String text1, String text2) {

        int[][] dp = new int[text1.length()][text2.length()];
        dp[0][0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;

        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], text1.charAt(i) == text2.charAt(0) ? 1 : 0);
        }
        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] = Math.max(dp[0][j - 1], text1.charAt(0) == text2.charAt(j) ? 1 : 0);
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;//在以当前结果就等于之前没进来字符串的结果+1.
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[text1.length() - 1][text2.length() - 1];
    }

    // 简单写法
    public static int longestCommonSuq_dp(String s1, String s2) {

        if (s1.length() == 0 || s2.length() == 0) return 0;

        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    public static void main(String[] args) {
        LongestCommonSubsequence_1143 subsequence1143 = new LongestCommonSubsequence_1143();
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


        for (int i = 0; i < 1001; i++) {

            String s1 = StringGenerator.generateRandomLowerCase(10);
            String s2 = StringGenerator.generateRandomLowerCase(10);
            if (subsequence1143.longestCommonSubsequence_dp(s1, s2) != subsequence1143.longestCommonSubsequence_re(s1, s2)) {
                System.out.println("oops=" + s1 + "=" + s2);
            }

        }
    }
}

