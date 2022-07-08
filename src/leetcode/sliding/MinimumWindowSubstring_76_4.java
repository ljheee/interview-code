package leetcode.sliding;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/minimum-window-substring/submissions/
 * 最后一个case 还超时
 */
public class MinimumWindowSubstring_76_4 {

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

        HashMap<Integer, Integer> map = new HashMap<>();
        int[] arr = new int[s.length() < 48 ? 48 : s.length()];
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'A';
            map.put(idx, i);
            arr[idx] += 1;
        }

        int left = arr.length;
        int right = -1;
        for (int i = 0; i < t.length(); i++) {
            int idx = t.charAt(i) - 'A';
            if (idx < left) {
                left = idx;
            }
            if (idx > right) {
                right = idx;
            }
            arr[idx] -= 1;
        }

        for (int i = left; i <= right; i++) {
            if (arr[i] < 0) {
                return "";
            }
        }

        Integer ll = map.get(left);
        Integer rr = map.get(right);


        return s.substring(ll, rr + 1);
    }


    public static void main(String[] args) {
        System.out.println(new MinimumWindowSubstring_76_4().minWindow("ADOBECODEBANC", "ABC"));
    }
}
