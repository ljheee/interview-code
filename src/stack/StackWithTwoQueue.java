package stack;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 两个队列实现一个栈
 *
 *      queue1              queue2
 * |---------------  ---------------|
 * |---------------  ---------------|
 * push                             pop
 */
public class StackWithTwoQueue {


    static Queue<Object> queue1 = new ArrayDeque<>();
    static Queue<Object> queue2 = new ArrayDeque<>();


    public static void push(Object entry) {

        // 两个栈 都为空
        if (queue1.size() == 0 && queue2.size() == 0) {
            queue1.add(entry);
            return;
        }

        // 否则 放入非空的那个栈，空栈 用于出队
        if (queue1.size() != 0) {
            queue1.add(entry);
            return;
        }
        if (queue2.size() != 0) {
            queue2.add(entry);
            return;
        }
    }


    public static Object pop() {

        Queue<Object> emptyQueue;
        Queue<Object> unEmptyQueue;
        if (queue1.size() == 0) {
            emptyQueue = queue1;
            unEmptyQueue = queue2;
        } else {
            emptyQueue = queue2;
            unEmptyQueue = queue1;
        }

        Object result = null;
        // 把 装入数据的Queue，emptyQueue
        while (unEmptyQueue.size() > 0) {
            if (unEmptyQueue.size() == 1) {
                // unEmptyQueue 最后一个 就是待出元素
                result = unEmptyQueue.remove();
                break;
            }
            emptyQueue.add(unEmptyQueue.remove());
        }

        return result;
    }


    public static void main(String[] args) {

        push(1);
        push(2);
        push(3);

        System.out.println(pop());
        System.out.println(pop());
        System.out.println(pop());
    }
}
