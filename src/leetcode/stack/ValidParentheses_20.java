package leetcode.stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/valid-parentheses/
 * ()[]{}
 */
public class ValidParentheses_20 {


    /**
     * AC
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {

        if (s == null || s.length() == 0) {
            return true;
        }

        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack();
        for (int i = 0; i < chars.length; i++) {
            if (!stack.isEmpty() && this.match(stack.peek(), chars[i])) {
                stack.pop();
            } else {
                stack.push(chars[i]);
            }
        }
        return stack.isEmpty();
    }

    private boolean match(char left, char right) {
        switch (left) {
            case '(':
                return right == ')';
            case '[':
                return right == ']';
            case '{':
                return right == '}';
        }
        return false;
    }
}
