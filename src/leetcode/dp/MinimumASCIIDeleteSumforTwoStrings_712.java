package leetcode.dp;

/**
 * 给定两个字符串s1, s2，找到使两个字符串相等所需删除字符的ASCII值的最小和。
 * https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/
 * <p>
 * 输入: s1 = "delete", s2 = "leet"
 * 输出: 403
 * 解释: 在 "delete" 中删除 "dee" 字符串变成 "let"，
 * 将 100[d]+101[e]+101[e] 加入总和。在 "leet" 中删除 "e" 将 101[e] 加入总和。
 * 结束时，两个字符串都等于 "let"，结果即为 100+101+101+101 = 403 。
 * 如果改为将两个字符串转换为 "lee" 或 "eet"，我们会得到 433 或 417 的结果，比答案更大。
 * -------------------------------------------------------------------------
 * 相当于找出两个字符串的 “最长相等子序列”，且码值最大的；
 * <p>
 * 例如:"xaxaxaa"
 * "xxxaaa"
 * 应该删除三个a;
 * <p>
 * 找出两个字符串中，码值最大的相等子序列，其余的删除。
 */
public class MinimumASCIIDeleteSumforTwoStrings_712 {



    // AC
    public static int minimumDeleteSum(String s1, String s2) {

        int total = 0;
        for (char c : s1.toCharArray()) {
            total += c;
        }
        for (char c : s2.toCharArray()) {
            total += c;
        }

        // 记录相等子序列 字符累加和
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + s1.charAt(i - 1);
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return total - 2 * dp[s1.length()][s2.length()];
    }


    public static void main(String[] args) {
        System.out.println(minimumDeleteSum("djoqzmzxe", "onydroiizrlggfh"));
    }

}
