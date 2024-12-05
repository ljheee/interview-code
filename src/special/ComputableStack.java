package special;

import java.util.Stack;

/**
 *
 * 可计算的栈
 * 只支持加减乘除
 * 乘除法优先级更高，在操作数入栈时 进行判断，先执行乘除；
 * @author lijianhua.
 */
@SuppressWarnings("Duplicates")
public class ComputableStack extends Stack<String> {


    public ComputableStack() {
        super();
    }


    /**
     * 加入item前，先看栈顶是否是乘除法运算符
     * 乘法和除法 优先级最高，遇到乘除直接计算
     *
     * @param item
     */
    public void pushAndCompute(Integer item) {
        if (isEmpty()) {
            push(String.valueOf(item));
            return;
        }

        final Stack<String> stack = this;
        switch (stack.peek()) {
            case "*":
                stack.pop();
                stack.push(String.valueOf(Integer.valueOf(stack.pop()) * item));
                break;
            case "/":
                stack.pop();
                stack.push(String.valueOf(Integer.valueOf(stack.pop()) / item));
                break;
            default:
                // 不是乘除法运算符，就直接入栈
                stack.push(String.valueOf(item));
        }
    }


    /**
     * 入栈顺序 [7,+,0,+,4,+,8,-,1]
     * 出栈后，栈顶运算符如果是+，将操作数改成负数，变成操作数的符号；
     * 计算结果时，使用带符号的运算符，作加法；
     *
     * @return
     */
    public Integer popAndFlip() {
        final Stack<String> stack = this;
        if (stack.size() == 1) {
            return -Integer.valueOf(stack.pop());
        }
        Integer num = Integer.valueOf(stack.pop());
        return !stack.isEmpty() && "+".equals(stack.peek()) ? -num : num;
    }


    public int compute() {
        if (isEmpty()) return 0;
        final Stack<String> stack = this;

        int ans = 0;
        while (!stack.isEmpty()) {
            int prev = popAndFlip();
            String operator = isEmpty() ? null : stack.pop();
            ans += prev;
        }
        return -ans;
    }


    public Integer popAndFlip0() {
        final Stack<String> stack = this;
        if (stack.size() == 1) {
            return -Integer.valueOf(stack.pop());
        }
        return Integer.valueOf(stack.pop());
    }

    public int compute0() {
        if (isEmpty()) return 0;

        final Stack<String> stack = this;

        int ans = Integer.valueOf(stack.pop());

        while (!stack.isEmpty()) {
            String operator = stack.pop();
            int prev = popAndFlip();
            if (prev == 0 && !stack.isEmpty() && "-".equals(stack.peek())) {
                stack.setElementAt("+", stack.size() - 1);
                continue;
            }

            switch (operator) {
                case "+":
                    ans = prev - ans;
                    break;
                case "-":
                    ans += prev;
                    break;
                default:
                    //ignore
            }
        }
        return -ans;
    }

}
