package num;

/**
 * https://leetcode.com/problems/string-to-integer-atoi/
 */
public class StringToInteger {

    /**
     * 入参输入s 为正常数字字符串
     *
     * @param s
     * @return
     */
    public int parseInt(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        s = s.trim();
        char[] chars = s.toCharArray();
        boolean negative = false;
        if (chars[0] == '-') {
            negative = true;
        }
        int ans = 0;
        for (int i = negative ? 1 : 0; i < chars.length; i++) {
            int temp = chars[i] - '0';
            ans = ans * 10 + temp;
        }
        return negative ? -ans : ans;
    }


    /**
     * https://leetcode.com/problems/string-to-integer-atoi/submissions/
     *
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        if (s.startsWith("+-") || s.startsWith("-+")) {
            return 0;
        }

        s = s.trim().replaceFirst("//+", "");
        if (s == null || "".equals(s)) {
            return 0;
        }
        char[] chars = s.toCharArray();
        // 字母开头，直接返回0
        if (chars[0] > '9') {
            return 0;
        }

        boolean negative = false;
        if (chars[0] == '-') {
            negative = true;
        }

        double ans = 0;
        for (int i = negative || chars[0] == '+' ? 1 : 0; i < chars.length; i++) {
            if (chars[i] < '0' || chars[i] > '9') {
                break;
            }
            int temp = chars[i] - '0';
            ans = ans * 10 + temp;
        }

        ans = negative ? -ans : ans;
        if (ans > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (ans < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) ans;
    }
}
