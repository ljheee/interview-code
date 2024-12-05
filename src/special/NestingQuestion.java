package special;

import java.util.Objects;
import java.util.Stack;

/**
 * @author lijianhua.
 *
 *
 * https://leetcode-cn.com/problems/basic-calculator/
 */
@SuppressWarnings("Duplicates")
public class NestingQuestion {


    public static void main(String[] args) {

        System.out.println(calculateWithPriority("2-30+1"));
        System.out.println(calculateWithPriority("2+30+1+1+1"));
        System.out.println(calculateWithPriority("2+(30/10)+1+1"));
        System.out.println(calculateWithPriority("2+(30/10)+(11-1)*2"));
        System.out.println(calculateWithPriority("(((2+(30/10)+(11-1)*2)))"));


    }


    public static int calculateWithPriority(String s) {
        s = s.replace(" ", "");
        int[] ansAndEndIndex = calculateWithPriority(s, 0);
        return ansAndEndIndex[0] + calculateWithPriority(s, ansAndEndIndex[1])[0];
    }

    private static int[] calculateWithPriority(String s, int from) {

        if (from > s.length() - 1) return new int[]{0, from};

        if (s.charAt(from) == '(') {
            return calculateWithPriority(s, from + 1);
        }

        Stack<String> stack = new Stack<>();

        int cur = 0;
        int i = from;
        for (; i < s.length(); i++) {
            if (s.charAt(i) == ')') break;

            if (s.charAt(i) == '(') {
                int[] priority = calculateWithPriority(s, i + 1);
                cur = priority[0];
                i = priority[1] + 1;
            }

            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                cur = cur * 10 + c - '0';
            } else {
                if (!stack.isEmpty() &&
                        (Objects.equals(stack.peek(), "*") || Objects.equals(stack.peek(), "/"))) {
                    switch (stack.peek()) {
                        case "*":
                            stack.pop();
                            stack.push(String.valueOf(Integer.valueOf(stack.pop()) * cur));
                            break;
                        case "/":
                            stack.pop();
                            stack.push(String.valueOf(Integer.valueOf(stack.pop()) / cur));
                            break;
                        default:
                            //ignore
                    }
                } else {
                    stack.push(String.valueOf(cur));
                }
                stack.push(String.valueOf(c));
                cur = 0;
            }
        }


        int ans = cur;
        while (!stack.isEmpty()) {
            switch (stack.peek()) {
                case "+":
                    stack.pop();
                    ans += Integer.valueOf(stack.pop());

                    break;
                case "-":
                    stack.pop();
                    ans = -ans;
                    ans += Integer.valueOf(stack.pop());
                    break;
                case "*":
                    stack.pop();
                    ans = Integer.valueOf(stack.pop()) * cur;
                    break;
                case "/":
                    stack.pop();
                    ans = Integer.valueOf(stack.pop()) / cur;
                    break;
                default:
                    //ignore
            }
        }


        return new int[]{ans, i};
    }


}
