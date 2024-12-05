package some;

/**
 * minWindow(String s, String t)
 * 从s中找到的子串，可以不按续包含t；
 * 如s=abcc,t=acb,返回abc(全部包含t，但不一定有序)。
 * <p>
 * 子串必须全部包含t;
 * 如s=abc,t=abbc,返回空，因为s不全包含t。
 * <p>
 * https://leetcode.com/submissions/detail/319449600/
 * O(n*m) 可能超时
 */
public class MinWindowSubstring {


    public static String minWindow(String s, String t) {
        if (s == null || s.length() == 0) return s;

        int minLen = Integer.MAX_VALUE;
        String ans = null;

        int[] set = new int[54];

        for (char c : t.toCharArray()) {
            set[c - 'A'] += 1;
        }
        char[] chars = s.toCharArray();

        int[] curSet = new int[54];
        int left = 0;
        for (int right = 0; right < chars.length; right++) {
            curSet[chars[right] - 'A'] += 1;

            while (right - left + 1 >= t.length() && checkContains(set, curSet)) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    ans = s.substring(left, right + 1);
                }
                curSet[chars[left++] - 'A'] -= 1;
            }

        }
        return ans;
    }

    private static boolean checkContains(int[] set, int[] curSet) {
        for (int i = 0; i < curSet.length; i++) {
            if (curSet[i] - set[i] < 0) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(minWindow("abcc", "abc"));
        System.out.println(minWindow("abcc", "ac"));
        System.out.println(minWindow("bbaa", "aba"));
    }
}
