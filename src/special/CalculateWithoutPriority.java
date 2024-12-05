package special;

import leetcode.ArrayGenerator;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Stack;

/**
 * (不带括号)表达式计算
 *
 * @author lijianhua.
 */
@SuppressWarnings("Duplicates")
public class CalculateWithoutPriority {

    /**
     * 乘法和除法 优先级最高，遇到乘除直接计算
     *
     * @param s
     * @return
     */
    public static int calculate(String s) {

        s = s.replace(" ", "");
        char[] chars = s.toCharArray();

        Stack<String> stack = new Stack<>();

        int cur = 0;
        for (int i = 0; i <= chars.length; i++) {
            if (i < chars.length && chars[i] >= '0' && chars[i] <= '9') {
                cur = cur * 10 + chars[i] - '0';
            } else {
                // 最后一个边界也会来计算乘除法
                String operator = stack.isEmpty() ? "" : stack.peek();

                switch (operator) {
                    case "*":
                        stack.pop();
                        stack.push(String.valueOf(Integer.valueOf(stack.pop()) * cur));
                        break;
                    case "/":
                        stack.pop();
                        stack.push(String.valueOf(Integer.valueOf(stack.pop()) / cur));
                        break;
                    default:
                        stack.push(String.valueOf(cur));
                        break;
                }
                if (i < chars.length) stack.push(String.valueOf(chars[i]));
                cur = 0;
            }
        }

        int ans = 0;
        while (!stack.isEmpty()) {
            int prev = Integer.valueOf(stack.pop());
            String operator = stack.isEmpty() ? null : stack.pop();
            ans += (operator == null || "+".equals(operator) ? -prev : prev);
        }
        return -ans;
    }


    public static int calculateII(String s) {

        s = s.replace(" ", "");
        char[] chars = s.toCharArray();
        ComputableStack stack = new ComputableStack();

        int cur = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                cur = cur * 10 + chars[i] - '0';
                if (i == chars.length - 1) stack.pushAndCompute(cur);
            } else {
                stack.pushAndCompute(cur);
                stack.push(String.valueOf(chars[i]));
                cur = 0;
            }
        }
        return stack.compute();
    }

    public static void main(String[] args) throws ScriptException {

//        System.out.println(calculate("2*   30+1"));
//        System.out.println(calculate("2-30+1"));
//        System.out.println(calculate("2-6/1+6/7/3*4*4+8/4"));
//        System.out.println(calculateII("2-6*8/5/2+6*8+4+7*4"));



        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");


        for (int i = 0; i < 10000; i++) {
            String s = ExpressionGenerator.randomExpression(10, 1, 9);


            String result = String.valueOf(engine.eval(s));
            int ans = new BigDecimal(result).intValue();
            if (calculate(s) != calculateII(s)) {
                System.out.print(ans + " " + calculate(s) + " ");
                System.out.println("error, s=" + s);
            }

//            if (calculate(s) != ans) {
//                System.out.print(ans + " " + calculate(s) + " ");
//                System.out.println("error, s=" + s);
//            }
//            if (calculateII(s) != ans) {
//                System.out.print(ans + " " + calculateII(s) + " ");
//                System.out.println("errorII, s=" + s);
//            }
        }


    }

}
