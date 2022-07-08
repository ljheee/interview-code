package leetcode.sliding;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/minimum-window-substring/submissions/
 */
public class MinimumWindowSubstring_76_5 {

    /**
     * 用map比较window和t
     * https://leetcode-cn.com/problems/minimum-window-substring/solution/hua-dong-chuang-kou-suan-fa-tong-yong-si-xiang-by-/
     * <p>
     * 去掉 .intValue()就报错
     * 两个Integer 用==对象比较时，超出缓存范围比较的是地址
     * <p>
     * 两个map动态比较：
     * match记录相等的kv，当match数与 size相等时，说明map相等
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

        HashMap<Character, Integer> needs = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();


        for (int i = 0; i < t.length(); i++) {
            needs.put(t.charAt(i), needs.getOrDefault(t.charAt(i), 0) + 1);
        }

        int minLen = Integer.MAX_VALUE;
        int start = 0;
        int left = 0;

        int match = 0;
        for (int right = 0; right < s.length(); right++) {

            char c = s.charAt(right);
            if (needs.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).intValue() == needs.get(c).intValue())
                    match++;
            }


            while (match == needs.size()) {
                if (right - left < minLen) {
                    // 更新最小子串的位置和长度
                    start = left;
                    minLen = right - left;
                }
                char c2 = s.charAt(left);
                if (needs.containsKey(c2)) {
                    window.put(c2, window.get(c2) - 1);

                    if (window.get(c2) < needs.get(c2))
                        match--;
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ?
                "" : s.substring(start, start + minLen + 1);
    }

    public static void main(String[] args) {
        System.out.println(new MinimumWindowSubstring_76_5().minWindow("ADOBECODEBANC", "ABC"));
    }
}
