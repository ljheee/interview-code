package leetcode.dp;

import leetcode.StringGenerator;

import java.util.HashSet;

/**
 * 最长回文子序列
 * 从s中选出其中的一些字符，组成的子序列是最长回文；
 * 每个字符，或选或不选
 * https://leetcode.com/problems/longest-palindromic-subsequence/submissions/
 */
public class LongestPalindromicSubsequence_516 {


    /**
     * 暴力法
     * 先生成所有子序列，再判断回文
     * <p>
     * 递归生成子序列：2^n
     * 判断回文：2^n * N = n * 2^n
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq_bf(String s) {

        HashSet<String> set = new HashSet<>();
        generateSubseq(set, 0, "", s);

        int ans = 1;
        for (String str : set) {
            if (str.length() == 0 || str.length() == 1) continue;
            if (isPalindromic(str)) {
                ans = Math.max(ans, str.length());
            }
        }
        return ans;
    }

    /**
     * 递归
     * 判断首位字符：
     * 首位相等：则去除首位的中间部分+2；
     * 首位不相等：则从from~to-1, from+1~to选最大的
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq_recursive(String s) {
        return lps(s, 0, s.length() - 1);
    }

    private int lps(String s, int from, int to) {
        if (from == to) {
            return 1;
        }
        if (from > to) {
            return 0;
        }
        if (s.charAt(from) == s.charAt(to)) {
            return lps(s, from + 1, to - 1) + 2;
        } else {
            return Math.max(lps(s, from, to - 1), lps(s, from + 1, to));
        }
    }

    private boolean isPalindromic(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left <= right && str.charAt(left) == str.charAt(right)) {
            left++;
            right--;
        }
        return left >= right;
    }

    private void generateSubseq(HashSet<String> set, int from, String subseq, String s) {
        if (from == s.length()) {
            set.add(subseq);
        } else {
            generateSubseq(set, from + 1, subseq + s.charAt(from), s);
            generateSubseq(set, from + 1, subseq, s);
        }
    }

    /**
     * s长度是4，下标范围0~4
     * 0~0、0~1、0~2、0~3
     * 1~1、1~2、1~3
     * 2~2、2~3
     * 3~3
     * 相似代码 https://leetcode-cn.com/problems/longest-palindromic-subsequence/comments/71463
     * <p>
     * AC
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {

        char[] chars = s.toCharArray();

        // dp[i][j] 任意使用i~j，能得到最长的回文子序列
        int length = s.length();
        int[][] dp = new int[length][length];


        // 对角线，只有一个字符，全为1；只填对角线右上角
//        for (int i = 0; i < length; i++) {
//            dp[i][i] = 1;
//        }
//        dp[0][1] = chars[0] == chars[1] ? 2 : 1;
//        dp[length - 2][length - 1] = chars[length - 2] == chars[length - 1] ? 2 : 1;

        for (int i = dp.length - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < length; j++) {
                if (Math.abs(i - j) == 1) dp[i][j] = chars[i] == chars[j] ? 2 : 1;

                if (chars[i] == chars[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }

        return dp[0][length - 1];
    }


    public static void main(String[] args) {
        LongestPalindromicSubsequence_516 palindromicSubsequence516 = new LongestPalindromicSubsequence_516();

        System.out.println(palindromicSubsequence516.longestPalindromeSubseq_bf("aba"));
        System.out.println(palindromicSubsequence516.longestPalindromeSubseq_bf("aa"));
        System.out.println(palindromicSubsequence516.longestPalindromeSubseq_bf("a"));
        System.out.println(palindromicSubsequence516.longestPalindromeSubseq_bf("axbxa"));
        System.out.println(palindromicSubsequence516.longestPalindromeSubseq_bf("bbbab"));
        System.out.println(palindromicSubsequence516.longestPalindromeSubseq_bf("abxbb"));
        System.out.println(palindromicSubsequence516.longestPalindromeSubseq_bf("mskbuxvyymvcorknl"));


        for (int i = 0; i < 421; i++) {
            String s = StringGenerator.generateRandomLowerCase(17);
            if (palindromicSubsequence516.longestPalindromeSubseq_recursive(s) !=
                    palindromicSubsequence516.longestPalindromeSubseq(s)) {
                System.out.println("oops=" + s);
            }
        }
    }
}
