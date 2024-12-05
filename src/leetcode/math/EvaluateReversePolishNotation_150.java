package leetcode.math;


import java.util.Stack;

/**
 *
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/submissions/
 * @author lijianhua.
 */
public class EvaluateReversePolishNotation_150 {



    // AC
    public int evalRPN(String[] tokens) {

        if (tokens == null || tokens.length == 0) return 0;


        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    Integer first = stack.pop();
                    stack.push(stack.pop() - first);
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    first = stack.pop();
                    stack.push(stack.pop() / first);
                    break;
                default:
                    stack.push(Integer.valueOf(token));

            }
        }
        return stack.pop();
    }
}
