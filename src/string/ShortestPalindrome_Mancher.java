package string;

/**
 * 在s 前面添加字符，使其形成的回文最短
 * https://leetcode.com/problems/shortest-palindrome/submissions/
 * <p>
 * 马拉车算法
 * 找出最长的回文子串前缀，截取最后部分，翻转添加到开头；
 * <p>
 * 回文半径数据，记录的是每个位置最长回文半径，p中的最大值是最长的回文子串长度；
 * 如何找出最长回文前缀？
 * case：“aabba” 回文前缀是2，p回文半径记录的最长回文子串长度是5
 * s的前缀是个回文，且对称轴在左半侧；p数组中0~len/2的最大值，就是最长回文前缀
 * <p>
 * <p>
 * 对比
 * 滚动hash 19% 7%
 * 马拉车 87% &%
 */
public class ShortestPalindrome_Mancher {


    public String shortestPalindrome(String s) {

        if (s == null || "".equals(s) || s.length() == 0) return "";

        StringBuffer sb = new StringBuffer("#");
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            sb.append(aChar);
            sb.append('#');
        }
        int len = sb.length();

        int maxLen = -1;
        int R = -1;
        int C = -1;
        int[] p = new int[len];

        for (int i = 0; i < p.length; i++) {
            p[i] = i < R ? Math.min(R - i, p[2 * C - i]) : 1;

            while (i + p[i] < len && i - p[i] > -1) {
                if (sb.charAt(i + p[i]) == sb.charAt(i - p[i])) {
                    p[i]++;
                } else {
                    break;
                }
            }

            if (i + p[i] > R) {
                R = i + p[i];
                C = i;
            }
            /**
             * 必须包含s[0]的回文前缀
             * i <= len / 2    ==> s的最大回文前缀，轴一定在p的左侧
             * 2 * C - R    ==> [L,C,R] 形成的回文范围，必须包含s[0]
             */
            if (i <= len / 2 && p[i] > maxLen && 2 * C - R <= 0) {
                maxLen = p[i];
            }
        }
        return new StringBuffer(s.substring(maxLen - 1)).reverse().append(s).toString();
    }


    public static void main(String[] args) {
        System.out.println(new ShortestPalindrome_Mancher().shortestPalindrome("aacecaaa"));
        System.out.println(new ShortestPalindrome_Mancher().shortestPalindrome("abab"));
        System.out.println(new ShortestPalindrome_Mancher().shortestPalindrome("adcba"));
        System.out.println(new ShortestPalindrome_Mancher().shortestPalindrome("abc"));
        System.out.println(new ShortestPalindrome_Mancher().shortestPalindrome("aabba"));
        System.out.println(new ShortestPalindrome_Mancher().shortestPalindrome("abcd"));
        System.out.println(new ShortestPalindrome_Mancher().shortestPalindrome("ababbbabbaba"));
    }
}
