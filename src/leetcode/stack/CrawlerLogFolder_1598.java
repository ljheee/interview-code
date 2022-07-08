package leetcode.stack;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/crawler-log-folder/
 * <p>
 * 回主目录，最少需要多少步？
 */
public class CrawlerLogFolder_1598 {


    public int minOperations(String[] logs) {

        if (logs.length == 0) return 0;
        Stack<String> stack = new Stack<>();

        for (String dir : logs) {
            if (dir.equals("./")) continue;
            if (dir.equals("../")) {
                if (!stack.isEmpty()) stack.pop();
            } else {
                stack.push(dir);
            }
        }
        return stack.size();
    }


    public boolean backspaceCompare(String S, String T) {
        if (S.length() != T.length()) return false;
        Stack<Character> stack = new Stack<>();

        char[] chars = S.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '#') {
                if (!stack.isEmpty()) stack.pop();
                continue;
            }
            stack.push(chars[i]);
        }

        System.out.println(stack);

        int idx = T.length() - 1;
        if (stack.isEmpty()) {
            while (T.charAt(idx) == '#') idx -= Math.min(idx, 2);
        }


        while (!stack.isEmpty()) {
            char pop = stack.pop();
            if (pop == T.charAt(idx)) {
                idx--;
            } else {
                while (T.charAt(idx) == '#') idx -= 2;
            }

        }


        return idx == 0;
    }





}
