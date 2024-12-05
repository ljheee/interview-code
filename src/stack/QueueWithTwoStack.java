package stack;

import java.util.Stack;

/**
 * 两个栈 实现队列
 */
public class QueueWithTwoStack {


    static Stack<Object> stack1 = new Stack<>();
    static Stack<Object> stack2 = new Stack<>();


    public static void enq(Object entry) {

        // 两个栈 都为空
        if (stack1.size() == 0 && stack2.size() == 0) {
            stack1.push(entry);
            return;
        }

        // 否则 放入非空的那个栈，空栈 用于出队
        if (stack1.size() != 0) {
            stack1.push(entry);
            return;
        }
        if (stack2.size() != 0) {
            stack2.push(entry);
            return;
        }
    }


    public static Object deq() {

        Stack<Object> emptyStack;
        Stack<Object> unEmptyStack;
        if (stack1.size() == 0) {
            emptyStack = stack1;
            unEmptyStack = stack2;
        } else {
            emptyStack = stack2;
            unEmptyStack = stack1;
        }

        // 把 装入数据的stack，倒入emptyStack
        while (unEmptyStack.size() > 0) {
            emptyStack.push(unEmptyStack.pop());
        }
        // emptyStack 栈顶 就是待出元素
        Object result = emptyStack.pop();

        // 再把 emptyStack的数据倒入 另一个栈，使emptyStack是个空栈
        while (emptyStack.size() > 0) {
            unEmptyStack.push(emptyStack.pop());
        }
        return result;
    }


    public static void main(String[] args) {

        enq(1);
        enq(2);
        enq(3);

        System.out.println(deq());
        System.out.println(deq());
        System.out.println(deq());
    }
}
