package some;

import leetcode.StringGenerator;

import java.util.Objects;
import java.util.Random;
import java.util.Stack;

/**
 * 字典序最大的子序列
 * 给定一个字符串str，和一个正数k
 * 返回长度为k的所有子序列中，字典序最大的子序列;    单调栈O(N)
 * <p>
 * 单调递减栈，
 * 当栈里元素和（遍历字符串）剩余元素 等于k时，，直接返回；
 * 遍历结束，返回栈里最后的k个；
 *
 * @author lijianhua.
 */
public class MaxSubSequence {


    public static String maxSubSequence(String s, int k) {
        if (k == 0) return null;
        if (s == null || s.length() <= k) return "";

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty() || s.charAt(i) <= stack.peek()) {
                stack.push(s.charAt(i));
                continue;
            }
            while (!stack.isEmpty() && s.charAt(i) > stack.peek()) {
                if (stack.size() + s.length() - i == k) break;
                stack.pop();
            }
            if (stack.size() + s.length() - i == k) break;
            stack.push(s.charAt(i));
        }

        while (stack.size() > k) stack.pop();

        String maxSubSequence = String.valueOf(stack.pop());
        while (!stack.isEmpty()) {
            maxSubSequence = stack.pop() + maxSubSequence;
        }
        return maxSubSequence;
    }

    /**
     * https://github.com/algorithmzuo/publicclass2020/blob/master/src/class58/Code02_MaxKLenSequence.java
     *
     * @param s
     * @param k
     * @return
     */
    public static String maxString(String s, int k) {
        if (k <= 0 || s.length() < k) {
            return "";
        }
        char[] str = s.toCharArray();
        int n = str.length;
        char[] stack = new char[n];
        int size = 0;
        for (int i = 0; i < n; i++) {
            while (size > 0 && stack[size - 1] < str[i] && size + n - i > k) {
                size--;
            }
            if (size + n - i == k) {
                return String.valueOf(stack, 0, size) + s.substring(i);
            }
            stack[size++] = str[i];
        }
        return String.valueOf(stack, 0, k);
    }


    public static void main(String[] args) {

        System.out.println(maxSubSequence("qfsatlhxawfdlbn", 1952827902));
        System.out.println(maxString("qfsatlhxawfdlbn", 1952827902));

        for (int i = 0; i < 10000; i++) {
            String s = StringGenerator.generateRandomLowerCase(15);
            int k = Math.abs(new Random().nextInt());
            if (!Objects.equals(maxString(s, k), maxSubSequence(s, k))) {
                System.out.println(s + "  " + k);
            }

        }
    }


}
