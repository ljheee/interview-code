package leetcode.sliding;

/**
 * https://leetcode.com/problems/minimum-window-substring/submissions/
 * 最后一个case 超时
 */
public class MinimumWindowSubstring_76_2 {

    /**
     * case包含大小写字母
     * 用arr 数组，判断子串都包含
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {

        if (s.equals(t)) {
            return s;
        }
        if (s.length() < t.length()) {
            return "";
        }
        int[] arr = new int[58];
        for (int i = 0; i < t.length(); i++) {
            arr[t.charAt(i) - 'A'] += 1;
        }
        int left = 0;

        int minLen = s.length();
        String minSubStr = "";
        for (int right = t.length() - 1; right < s.length(); right++) {
            while (containAll(s, left, right, arr.clone())) {
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

    private boolean containAll(String s, int left, int right, int[] arr) {
        for (int i = left; i <= right; i++) {
            arr[s.charAt(i) - 'A'] -= 1;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                return false;
            }
        }
        return true;
    }

}
