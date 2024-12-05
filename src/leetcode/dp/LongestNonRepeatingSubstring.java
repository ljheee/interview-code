package leetcode.dp;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 * 给定一个只有小写字母的字符串，返回最长无重复子串的长度（meituan）
 */
public class LongestNonRepeatingSubstring {


    public static int longestNonRepeatingSubstring0(String s) {

        if ("".equals(s) || s.length() == 0) return 0;

        char[] chars = s.toCharArray();

        // （每个字符）上一次出现的位置
        int[] lastIndex = new int[26];
        Arrays.fill(lastIndex, -1);

        // 记录以i结尾的最长子串
        int[] dp = new int[s.length()];
        dp[0] = 1;
        lastIndex[chars[0] - 'a'] = 0;

        for (int i = 1; i < chars.length; i++) {

            dp[i] = dp[i - 1] + 1;
            int last = lastIndex[chars[i] - 'a'];
            if (last >= 0) {
                dp[i] = Math.min(dp[i - 1] + 1, i - last);
            }

            // 更新上一次出现的位置
            lastIndex[chars[i] - 'a'] = i;
        }

        int max = dp[0];
        for (int i : dp) {
            max = Math.max(max, i);
        }
        return max;
    }

    /**
     * 用一个变量滚动记录dp
     * @param s
     * @return
     */
    public static int longestNonRepeatingSubstring(String s) {

        if ("".equals(s) || s.length() == 0) return 0;

        char[] chars = s.toCharArray();

        // （每个字符）上一次出现的位置
        int[] lastIndex = new int[26];
        Arrays.fill(lastIndex, -1);

        // 记录以i结尾的最长子串
        //int[] dp = new int[s.length()];
        int preDP = 1;
        lastIndex[chars[0] - 'a'] = 0;

        int max = preDP;
        for (int i = 1; i < chars.length; i++) {

            int last = lastIndex[chars[i] - 'a'];
            preDP = Math.min(preDP + 1, i - last);
            max = Math.max(max, preDP);
            // 更新上一次出现的位置
            lastIndex[chars[i] - 'a'] = i;
        }
        return max;
    }


    public static void main(String[] args) {
        System.out.println(longestNonRepeatingSubstring0("abcabcbb"));

    }

}
