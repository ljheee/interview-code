package string;

/**
 * Created by lijianhua04 on 2020/5/2.
 */
public class LongestPalindromicSubstring_5 {


    public String longestPalindrome(String str) {

        if (str == null || "".equals(str) || str.length() == 0) return "";
        String s = process(str);
        int len = s.length();

        int max = -1;
        int idx = -1;

        int R = -1;
        int C = -1;
        int[] p = new int[len];
        for (int i = 0; i < len; i++) {
            p[i] = i < R ? Math.min(R - i, p[2 * C - i]) : 1;

            while (i + p[i] < len && i - p[i] > -1) {
                if (s.charAt(i + p[i]) == s.charAt(i - p[i])) {
                    p[i]++;
                } else {
                    break;
                }
            }

            if (i + p[i] > R) {
                R = i + p[i];
                C = i;
            }

            if (p[i] > max) {
                max = p[i];
                idx = i;
            }
        }
        max = max - 1;
        int from = idx / 2 - max / 2;
        return str.substring(from, from + max);
    }

    private String process(String s) {

        StringBuffer stringBuffer = new StringBuffer("#");
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            stringBuffer.append(aChar);
            stringBuffer.append('#');
        }
        return stringBuffer.toString();
    }


    public static void main(String[] args) {
        System.out.println(new LongestPalindromicSubstring_5().longestPalindrome("bacc"));
        System.out.println(new LongestPalindromicSubstring_5().longestPalindrome("cbbd"));
        System.out.println(new LongestPalindromicSubstring_5().longestPalindrome("cbabd"));
        System.out.println(new LongestPalindromicSubstring_5().longestPalindrome("babad"));
        System.out.println(new LongestPalindromicSubstring_5().longestPalindrome("abba"));
        System.out.println(new LongestPalindromicSubstring_5().longestPalindrome("abMba"));
        System.out.println(new LongestPalindromicSubstring_5().longestPalindrome("acb"));
    }
}
