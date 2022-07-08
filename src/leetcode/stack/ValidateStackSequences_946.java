package leetcode.stack;

import java.util.Stack;

/**
 * 给定 pushed 和 popped 两个序列，每个序列中的 值都不重复，只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；否则，返回 false 。
 * 用栈模拟
 * https://leetcode.com/problems/validate-stack-sequences/submissions/
 */
public class ValidateStackSequences_946 {


    /**
     * AC
     *
     * @param pushed
     * @param popped
     * @return
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {

        if (pushed.length != popped.length) return false;
        Stack<Integer> stack = new Stack<>();


        int pushIdx = 0;
        for (int i = 0; i < popped.length; i++) {
            if (!stack.isEmpty() && stack.peek() == popped[i]) {
                stack.pop();
                continue;
            }

            while (pushIdx < popped.length && popped[i] != pushed[pushIdx]) {
                stack.push(pushed[pushIdx++]);
            }

            if (pushIdx < popped.length && popped[i] == pushed[pushIdx]) {
                pushIdx++;
                continue;
            }
            // 已经全入栈，但当前要出栈的没匹配到
            if (pushIdx == pushed.length - 1) return false;
        }


        return stack.isEmpty();
    }


    public boolean validateStackSequences2(int[] pushed, int[] popped) {
        if (pushed.length != popped.length) return false;
        Stack<Integer> stack = new Stack<>();

        int popIdx = 0;
        for (int push : pushed) {
            stack.push(push);
            while (!stack.isEmpty() && stack.peek() == popped[popIdx]) {
                stack.pop();
                popIdx++;
            }
        }
        return stack.isEmpty() && popIdx == popped.length;
    }

}
