package leetcode.dp;

/**
 * 找出 ASCII码值 累加和最大的相等子序列
 * xxxaaa 和 aaaxxx应该返回xxx;
 * "aaaaaazzzzz","zzzzzaaaaaa" 应该返回zzzzz，虽然a的子序列更长但累加和小于z的。
 * <p>
 * "xaxaxaa"
 * "xxxaaa" 最长相等子序列有xxx和aaa，返回xxx;
 * <p>
 *
 */
public class LongestSubsequenceWithCondition {

    /**
     * 只记录长度增大的字符：
     * 子序列增长时，记录相等的字符码值
     *
     * 不过的case
     * "doz", "odroiiz"
     * 第一个字符串的o，和第二个字符串的第二o，匹配上，却用了之前的sum
     *
     * @param s1
     * @param s2
     * @return
     */
    public int longestSubsequence(String s1, String s2) {
        int sum = 0;
        int maxSum = 0;
        int preLen = 0;
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;

                    if (dp[i - 1][j - 1] == 0) {
                        maxSum = Math.max(maxSum, sum);
                        sum = s1.charAt(i - 1);
                        preLen = dp[i][j];
                    } else if (dp[i - 1][j - 1] == preLen) {
                        sum += s1.charAt(i - 1);
                        preLen = dp[i][j];
                    }
                    System.out.println("sum=" + sum);
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        maxSum = Math.max(maxSum, sum);
        System.out.println("dp=" + dp[s1.length()][s2.length()]);
        return maxSum;
    }

    /**
     * 码值最大的子序列
     * 返回最大码值
     *
     * AC 在该题验证
     * https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/submissions/
     * @param s1
     * @param s2
     * @return
     */
    public int longestSubsequence0(String s1, String s2) {

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
        System.out.println("dp=" + dp[s1.length()][s2.length()]);
        return dp[s1.length()][s2.length()];
    }


    public static void main(String[] args) {
        LongestSubsequenceWithCondition subsequenceWithCondition = new LongestSubsequenceWithCondition();

//        System.out.println(subsequenceWithCondition.longestSubsequence("xaxaxa", "xxxaaa"));
//        System.out.println(subsequenceWithCondition.longestSubsequence("aaaxxx", "xxxaaa"));
//        System.out.println(subsequenceWithCondition.longestSubsequence("sea", "eat"));
//        System.out.println(subsequenceWithCondition.longestSubsequence("delete", "leet"));

        System.out.println(subsequenceWithCondition.longestSubsequence0("doz", "odroiiz"));
//        System.out.println(subsequenceWithCondition.longestSubsequence("doz", "onydroiizrlggfh"));
//        System.out.println(subsequenceWithCondition.longestSubsequence("djoqzmzxe", "onydroiizrlggfh"));
        System.out.println((int) ('d' + 'o' + 'z'));


    }

}
