package leetcode.dp;

import java.util.Stack;

/**
 * s只是小括号，输出最长的有效括号数
 * https://leetcode.com/problems/longest-valid-parentheses/
 * <p>
 *
 * 括号匹配的最长子串，滑动一个窗口，
 * ( 加1
 * ) 减1
 * 括号匹配，则最终“子串和”为0;—— )(的场景？
 * 和为负数
 */
public class LongestValidParentheses_32 {

    /**
     * "(())" 输出4
     * <p>
     * "()(()" 期望是2
     * <p>
     * 栈只能统计出，正确的()对数
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {

        if (s == null || "".equals(s)) return 0;

        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        int ans = 0;
        int max = 0;
        int pre = 0;

        int idx = -1;
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];

            if (stack.isEmpty() && aChar == ')') {
                idx = i > 0 ? i : -1;
                pre = ans;
                ans = 0;
                continue;
            }
            if (aChar == '(') {
                stack.push(aChar);
                continue;
            }

            if (stack.peek() == '(' && aChar == ')') {

                if (stack.size() == 1) {
                    ans += 2;
                    stack.pop();
                    max = Math.max(max, ans);
                } else {
                    // 出现多余括号的位置
                    if (idx == -1) {
                        pre = ans;
                        ans = 2;
                        idx = i;
                    }
                    if (idx != i) ans += 2;
                    stack.pop();
                    max = Math.max(max, ans);
                }
            }
        }
        if (stack.isEmpty()) ans += pre;
        return ans;
    }

    public int longestValidParentheses_slidingwindow(String s) {

        if (s == null || "".equals(s)) return 0;

        int ans = 0;
        int sum = 0;
        char[] chars = s.toCharArray();
        int left = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ')') sum--;
            if (sum < 0) {

            }
            if (chars[i] == '(') sum++;


        }


        return 0;
    }

    // (()())
    public static void main(String[] args) {
        LongestValidParentheses_32 validParentheses32 = new LongestValidParentheses_32();
        System.out.println(validParentheses32.longestValidParentheses("()(()"));
        System.out.println(validParentheses32.longestValidParentheses("()(()()"));
        System.out.println(validParentheses32.longestValidParentheses("()(()(())"));
        System.out.println(validParentheses32.longestValidParentheses("(()())"));
        System.out.println(validParentheses32.longestValidParentheses(")("));
        System.out.println(validParentheses32.longestValidParentheses("()(())"));
        System.out.println(validParentheses32.longestValidParentheses("()((()))"));
        System.out.println(validParentheses32.longestValidParentheses("(((())))"));
        System.out.println(validParentheses32.longestValidParentheses("(((())))(((())))"));
        System.out.println(validParentheses32.longestValidParentheses(")()())()()("));
    }
}
