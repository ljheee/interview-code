package string;

/**
 * 实现indexOf()
 * https://leetcode-cn.com/problems/implement-strstr/
 */
public class ImplementStrStr_28 {


    /**
     * 子串逐一比较
     *
     * @param s
     * @param str
     * @return
     */
    public int strStr(String s, String str) {

        if (!"".equals(s) && "".equals(str)) {
            return -1;
        }
        if (s.equals(str)) {
            return 0;
        }
        char c = str.charAt(0);

        for (int i = 0; i < s.length(); i++) {
            if (c == s.charAt(i) && equalStr(s, i, str)) {
                return i;
            }
        }
        return -1;
    }

    private boolean equalStr(String s, int from, String str) {
        if (from + str.length() > s.length()) {
            return false;
        }
        int j = 0;
        for (int i = from; i < from + str.length(); i++) {
            if (s.charAt(i) == str.charAt(j)) {
                j++;
            } else {
                return false;
            }
        }
        return true;
    }


    /**
     * 滚动hash解法
     * https://leetcode-cn.com/problems/implement-strstr/solution/shi-xian-strstr-by-leetcode/
     * 没有处理 hash溢出
     *
     * @param s
     * @param str
     * @return
     */
    public int strStr1(String s, String str) {

        if (!"".equals(s) && "".equals(str)) {
            return -1;
        }

        int n = s.length();
        int m = str.length();
        if (m > n) return -1;

        int base = 26;

        /**
         * subHash： 搜索子串的hash值
         * hash： 原串在(m长度)内的hash值
         * aL： 存储最高位的base幂次方；
         */
        long subHash = 0, hash = 0, aL = 1;
        for (int i = 0; i < m; i++) {
            subHash = subHash * base + (str.charAt(i) - 'a');
            hash = hash * base + (s.charAt(i) - 'a');
            aL = aL * base;
        }
        aL /= base;

        if (subHash == hash) {
            return 0;
        }

        for (int i = 1; i < n - m + 1; i++) {
            // 进一个，出一个
            hash = (hash - (s.charAt(i - 1) - 'a') * aL) * base + (s.charAt(m + i - 1) - 'a');
            if (subHash == hash) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
//        System.out.println(new ImplementStrStr_28().strStr("helllo", "ll"));
        System.out.println(new ImplementStrStr_28().strStr1("helllo", "ll"));//2
        System.out.println(new ImplementStrStr_28().strStr1("abbcff", "cf"));//3
        System.out.println(new ImplementStrStr_28().strStr1("plnmmc", "mc"));//4
    }
}
