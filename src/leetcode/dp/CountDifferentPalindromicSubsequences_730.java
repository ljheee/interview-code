package leetcode.dp;

/**
 * https://leetcode-cn.com/problems/count-different-palindromic-subsequences/
 * 给定一个字符串 S，找出 S 中不同的非空回文子序列个数，并返回该数字与 10^9 + 7 的模。
 */
public class CountDifferentPalindromicSubsequences_730 {


    public static int countPalindromicSubsequences(String s) {

        int[][] dp = new int[s.length()][s.length()];

        for (int i = dp.length - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < dp[0].length; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }

        System.out.println(dp[0][s.length()-1]);
        return 0;
    }


    public static void main(String[] args) {
        countPalindromicSubsequences("abbcbba");
        countPalindromicSubsequences("bccb");
        countPalindromicSubsequences("axbxa");
    }
}
