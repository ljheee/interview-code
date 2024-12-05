package string;

/**
 * 找出所有 回文子串
 * https://leetcode.com/problems/palindromic-substrings/submissions/
 */
public class PalindromicSubstrings_647 {


    /**
     * 判断每个 i~j 是否为回文
     * O(n^2)
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        if(s == null|| "".equals(s)) return 0;

        int ans = s.length();

        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (isPalindromic(s, i, j)) ans++;
            }
        }
        return ans;
    }

    private boolean isPalindromic(String s, int from, int to) {
        while (from <= to) {
            if (s.charAt(from++) != s.charAt(to--)) return false;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(new PalindromicSubstrings_647().countSubstrings("aaa"));
    }
}
