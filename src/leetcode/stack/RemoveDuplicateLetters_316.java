package leetcode.stack;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by lijianhua04 on 2020/12/28.
 */
public class RemoveDuplicateLetters_316 {

    // cbabc

    public static String rm(String s) {

        char[] chars = s.toCharArray();

        LinkedHashMap<Character, Character> map = new LinkedHashMap<>();
        Stack<Character> stack = new Stack<>();

        for (char aChar : chars) {

            if (map.isEmpty()) {
                map.put(aChar, aChar);
                stack.push(aChar);
                continue;
            }
            if (stack.peek() <= aChar) {
                map.remove(aChar);
                map.put(aChar, aChar);
                stack.push(aChar);
            } else {
                if (map.containsKey(aChar)) {

                    // 如果是第一个，需要看是否逆序，bacdb
                    Object[] objects = map.keySet().toArray();

                    for (int i = 0; i < objects.length; i++) {
                        char ch = (char) objects[i];
                        if (ch == aChar && i + 1 < objects.length && ch > (char) objects[i + 1]) {
                            map.remove(aChar);
                            map.put(aChar, aChar);
                            stack.push(aChar);
                        }

                    }
                } else {
                    map.put(aChar, aChar);
                    stack.push(aChar);
                }
            }

        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Character character : map.keySet()) {
            stringBuilder.append(character.charValue());
        }

        return stringBuilder.toString();

    }


    // acdbc
    public String removeDuplicateLetters(String s) {
        char[] chars = s.toCharArray();
        LinkedList<Character> linkedList = new LinkedList<>();
        for (char aChar : chars) {

            if (linkedList.isEmpty()) {
                linkedList.add(aChar);
                continue;
            }
            if (linkedList.peek() <= aChar) {

                int i = linkedList.indexOf(aChar);
                if (i > 0 && aChar < linkedList.get(i + 1)) {
                    // skip current, eg: acdbc
                } else {
                    if (i > 0) linkedList.remove(i);
                    linkedList.add(aChar);
                }

            } else {
                if (linkedList.contains(aChar)) {

                    // 如果是第一个，需要看是否逆序，bacdb

                    int idx = -1;
                    while ((idx = linkedList.indexOf(aChar)) > 0) {
                        if (aChar > linkedList.get(idx + 1)) {
                            linkedList.remove(idx);
                            linkedList.add(aChar);
                        }
                    }
                } else {
                    linkedList.add(aChar);
                }
            }

        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Character character : linkedList) {
            stringBuilder.append(character.charValue());
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {

        System.out.println(rm("cbacdcbc"));

        LinkedHashMap<Character, Character> map = new LinkedHashMap<>();
        map.put('a', 'a');
        map.put('b', 'a');
        map.put('c', 'a');

        System.out.println(map.keySet());

        System.out.println(Arrays.toString(map.keySet().toArray()));


        LinkedList<Character> linkedList = new LinkedList<>();
        linkedList.add('a');
        linkedList.remove('b');

    }


}
