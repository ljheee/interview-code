package leetcode.dp;

/**
 * Created by lijianhua04 on 2020/6/14.
 */
public class RegularExpressionMatching_10 {


    /**
     * https://leetcode.com/problems/regular-expression-matching/discuss/5651/Easy-DP-Java-Solution-with-detailed-Explanation
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch_dp(String s, String p) {
        if (s.equals(p)) return true;
        if (p.length() == 0) return false;

        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];

        dp[0][0] = true;
        for (int j = 2; j < p.length() + 1; j += 2) {
            if (p.charAt(j - 1) == '*' && dp[0][j - 2]) {
                dp[0][j] = true;
            }
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (p.charAt(j - 1) == '*') {
                    if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j - 2] || dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public boolean isMatch(String s, String p) {
        if (s.equals(p)) return true;
        if (p.length() == 0) return false;

        boolean first = s.charAt(0) == p.charAt(0) || p.charAt(0) == '.';
        if (p.length() >= 2 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2)) || (first && isMatch(s.substring(1), p));
        } else {
            return first && isMatch(s.substring(1), p.substring(1));
        }
    }


}
