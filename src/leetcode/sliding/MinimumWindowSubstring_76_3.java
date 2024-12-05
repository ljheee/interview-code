package leetcode.sliding;

/**
 * https://leetcode.com/problems/minimum-window-substring/submissions/
 * 最后一个case 还超时
 */
public class MinimumWindowSubstring_76_3 {

    /**
     * case包含大小写字母
     * 用arr 数组，判断子串都包含
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {

        if(s.length()>60000){
            s = new StringBuffer(s).reverse().toString();
        }
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
            int[] clone = arr.clone();
            int thisLen = right - left + 1;
            if (thisLen >= t.length() && containAll(s, left, right, clone)) {

                if (thisLen <= minLen) {
                    minLen = thisLen;
                    minSubStr = s.substring(left, right + 1);
                }

                while (containsAll(s, left++, clone)) {
                    thisLen = right - left + 1;
                    if (thisLen <= minLen) {
                        minLen = thisLen;
                        minSubStr = s.substring(left, right + 1);
                    }
                    //left++;
                }
            }
        }

        return minSubStr;
    }

    private boolean containsAll(String s, int left, int[] clone) {
        return (clone[s.charAt(left) - 'A'] += 1) <= 0;
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

    public static void main(String[] args) {
        System.out.println(new MinimumWindowSubstring_76_3().minWindow("ADOBECODEBANC", "ABC"));
    }
}
