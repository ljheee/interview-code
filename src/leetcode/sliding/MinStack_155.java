package leetcode.sliding;

import java.util.*;

/**
 * https://leetcode.com/problems/min-stack/
 */
public class MinStack_155 {


    Stack<Integer> stack = null;
    LinkedList<Integer> linkedList = null;

    public MinStack_155() {
        stack = new Stack<>();
        linkedList = new LinkedList<>();
    }

    public void push(int x) {
        stack.push(x);
        if (linkedList.isEmpty()) {
            linkedList.addLast(x);
        }else {
            linkedList.addLast(Math.min(x, linkedList.peekLast()));
        }

    }

    public void pop() {
        stack.pop();
        linkedList.removeLast();

    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return linkedList.peekLast();
    }


    public static void main(String[] args) {
        MinStack_155 minStack = new MinStack_155();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin(); // return -3
        minStack.pop();
        minStack.top();    // return 0
        minStack.getMin(); // return -2


        MinStack_155 minStack2 = new MinStack_155();

        minStack2.push(2);
        minStack2.push(0);
        minStack2.push(3);
        minStack2.push(0);

        System.out.println(minStack2.getMin());
        minStack2.pop();
        System.out.println(minStack2.getMin());
        minStack2.pop();




        MinStack_155 minStack3 = new MinStack_155();

        minStack3.push(1);
        minStack3.push(2);

        minStack3.top();
        System.out.println(minStack3.getMin());
        minStack3.pop();
        System.out.println(minStack3.getMin());
        minStack3.top();

    }
}
