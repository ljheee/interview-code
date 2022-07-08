package leetcode.sliding;

/**
 * 滑动窗口，夹住一个window，里面包含t
 * https://leetcode.com/problems/minimum-window-substring/submissions/
 */
public class MinimumWindowSubstring_76 {

    public String minWindow(String s, String t) {

        if (s.equals(t)) {
            return s;
        }
        if (s.length() < t.length()) {
            return "";
        }
//        int[] arr = new int[26];
//        for (int i = 0; i < t.length(); i++) {
//            arr[t.charAt(i) - 'A'] += 1;
//        }
        int left = 0;

        int minLen = s.length();
        String minSubStr = "";
        for (int right = t.length() - 1; right < s.length(); right++) {
            while (containAll(s, left, right, t)) {
                int thisLen = right - left + 1;
                if (thisLen <= minLen) {
                    minLen = thisLen;
                    minSubStr = s.substring(left, right + 1);
                }
                left++;
            }
        }

        return minSubStr;
    }

    //    private boolean containAll(String s, int left, int right, int[] arr) {
//        for (int i = left; i <= right; i++) {
//            arr[s.charAt(i) - 'A'] -= 1;
//        }
//        for (int i = 0; i < arr.length; i++) {
//            if (arr[i] > 0) {
//                return false;
//            }
//        }
//        return true;
//    }
    private boolean containAll(String s, int left, int right, String t) {
        for (int i = 0; i < t.length(); i++) {
            int i1 = s.indexOf(t.charAt(i), left);
            if (i1 > right || i1 == -1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        new MinimumWindowSubstring_76().minWindow("ADOBECODEBANC", "ABC");
        new MinimumWindowSubstring_76().minWindow("abc", "ac");
    }
}
