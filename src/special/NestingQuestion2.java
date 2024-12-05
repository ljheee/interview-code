package special;

import java.util.Objects;
import java.util.Stack;

/**
 * @author lijianhua.
 * <p>
 * <p>
 * https://leetcode-cn.com/problems/basic-calculator/
 */
@SuppressWarnings("Duplicates")
public class NestingQuestion2 {


    public static void main(String[] args) {

        System.out.println(calculateWithPriority("(3+5-6*2*7)/(7/8)+(7)-(2)"));
//        System.out.println(calculateWithPriority("2-30+1"));
//        System.out.println(calculateWithPriority("2+30+1+1+1"));
//        System.out.println(calculateWithPriority("2+(30/10)+1+1"));
//        System.out.println(calculateWithPriority("2+(30/10)+(11-1)*2"));
//        System.out.println(calculateWithPriority("(((2+(30/10)+(11-1)*2)))"));

        System.out.println(calculateWithPriority("(3)-(1+6)-(6/4+4)-(8)*(4)/(4)"));


        for (int i = 0; i < 0; i++) {
            String s = ExpressionGenerator.priorityExpression(10, 1, 9);
            try {
                System.out.println(calculateWithPriority(s) + "=" + s);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error on:" + s);
            }

        }

    }


    public static int calculateWithPriority(String s) {
        s = s.replace(" ", "");
        int[] ansAndEndIndex = calculateWithPriority(s, 0);
        return ansAndEndIndex[0] + calculateWithPriority(s, ansAndEndIndex[1] + 1)[0];
    }

    private static int[] calculateWithPriority(String s, int from) {

        if (from > s.length() - 1) return new int[]{0, from};

        if (s.charAt(from) == '(') {
            return calculateWithPriority(s, from + 1);
        }

        Stack<String> stack = new Stack<>();

        int cur = 0;
        int i = from;
        for (; i <= s.length(); i++) {
            if (i < s.length() && s.charAt(i) == ')') {
                if (cur != 0) stack.push(String.valueOf(cur));
                cur = 0;
                break;
            }

            if (i < s.length() && s.charAt(i) == '(') {
                int[] priority = calculateWithPriority(s, i + 1);
                cur = priority[0];
                i = priority[1] + 1;
            }

            if (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                cur = cur * 10 + s.charAt(i) - '0';
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
                if (i < s.length()) stack.push(String.valueOf(s.charAt(i)));
                cur = 0;
            }
        }


//        int ans = Integer.valueOf(stack.pop());
        int ans = 0;
        while (!stack.isEmpty()) {
            int num = Integer.valueOf(stack.pop());
            String operator = stack.isEmpty() ? "" : stack.pop();

            switch (operator) {
                case "*":
                    ans = num;

                    //再取出一对
                    num = Integer.valueOf(stack.pop());
                    operator = stack.isEmpty() ? "" : stack.pop();
                    num = operator == null || "+".equals(operator) ? -num : num;
                    ans *= num;
                    break;
                case "/":
                    ans = num;

                    //再取出一对
                    num = Integer.valueOf(stack.pop());
                    operator = stack.isEmpty() ? "" : stack.pop();
                    num = "".equals(operator) || "+".equals(operator) ? -num : num;
                    ans = num / ans;
                    break;

                default:
                    num = "".equals(operator) || "+".equals(operator) ? -num : num;
                    ans += num;
                    break;
            }

        }


        return new int[]{-ans, i};
    }


}
