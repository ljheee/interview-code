package string;

import java.util.Arrays;
import java.util.Stack;

/**
 * 最初公共前缀
 * https://leetcode.com/problems/longest-common-prefix/submissions/
 * <p>
 * <p>
 * todo：可以采用字典树
 */
public class LongestCommonPrefix_14 {


    public String longestCommonPrefix(String[] strs) {

        if (strs == null) return "";
        int length = strs.length;
        if (length == 0) return "";
        if (length == 1) return strs[0];

        Arrays.sort(strs);
        String str0 = strs[0];
        String strN = strs[length - 1];
        if ("".equals(str0) || "".equals(strN)) return "";

        int idx = 0;
        int len = Math.min(str0.length(), strN.length());
        for (int i = 0; i < len; i++) {
            if (strN.charAt(i) == str0.charAt(i)) {
                idx++;
            } else {
                break;
            }
        }
        return str0.substring(0, idx);
    }


    /**
     * 出勤记录中不超过一个'A'(缺勤)并且不超过两个连续的'L'(迟到),那么这个学生会被奖赏。
     * <p>
     * 不超过两个连续的'L'(迟到)，意味着连续两次L不会惩罚，连续3次LLL才会惩罚
     *
     * @param s
     * @return
     */
    public boolean checkRecord(String s) {

        int absent = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'A') {
                absent++;
            }
            if (i <= s.length() - 3 && s.charAt(i) == 'L' && s.charAt(i + 1) == 'L' && s.charAt(i + 2) == 'L') {
                return false;
            }
        }

        return absent <= 1;
    }




    public static void main(String[] args) {
        System.out.println(new LongestCommonPrefix_14().longestCommonPrefix(new String[]{}));
        System.out.println(new LongestCommonPrefix_14().longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(new LongestCommonPrefix_14().longestCommonPrefix(new String[]{"aaa", "aa", "a"}));


        System.out.println(new LongestCommonPrefix_14().checkRecord("PPALLP"));
        System.out.println(new LongestCommonPrefix_14().checkRecord("PPALLL"));
        System.out.println(new LongestCommonPrefix_14().checkRecord("LLPPPLL"));
    }
}
