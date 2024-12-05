package leetcode.greedy;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 移除k个字符，使剩下的数字最小
 * https://leetcode.com/problems/remove-k-digits/submissions/
 */
public class RemoveKdigits {
    /**
     * https://leetcode.com/problems/remove-k-digits/
     * 从左到右扫描，去除最大的k个?
     * <p>
     * 从后往前找，保留最小的len-k个；
     *
     * @param num 移除k个字符，使剩下的数字最小
     * @param k
     * @return
     */
    public String removeKdigits0(String num, int k) {

        if (k == 0) {
            return num;
        }
        if (num.length() == k) {
            return "0";
        }

        int t = k;
        char[] chars = num.toCharArray();
        int idx = 0;
        while (t > 0) {
            int flag = 1;
            for (int i = 0; i < chars.length - 1; i++) {
                if (chars[i] > chars[i + 1]) {
                    chars[i] = '.';
                    idx = i + 1;
                    t--;
                    flag = 0;
                    break;
                }
            }
            if (flag == 1 && t != 0) {//如果找了一圈没有任何移除，即flag为1，但k有余量时
                String temp = new String(chars).substring(0, chars.length - t);//递增的，截掉末尾的大数
                String res = temp.replaceAll("\\.", "").replaceFirst("^0*", "");
                return res.equals("") ? "0" : res;
            }
        }
        String res = new String(chars).replaceAll("\\.", "").replaceFirst("^0*", "");
        return res.equals("") ? "0" : res;
    }


    /**
     * 思路来源 https://leetcode-cn.com/problems/remove-k-digits/solution/20190715removekdigitsyi-chu-zhong-wei-shu-javatong/
     * * chars[i] > chars[i + 1]时，需要移除chars[i]
     * 重点是 char数组移除元素，是实际移除，也就是让char数组每次动态变化的。
     * <p>
     * 特殊case 1234567890 k=9
     * 5337 k=2
     *
     * @param num
     * @param k
     * @return
     */
    public static String removeKdigits1(String num, int k) {
        if (k == 0) {
            return num;
        }
        if (num.length() == k) {
            return "0";
        }
        int t = k;
        char[] chars = num.toCharArray();
        int len = chars.length;//用于 记录当前剩余字符数，是动态变化的
        while (t > 0) {
            int flag = 1;
            for (int i = 0; i < chars.length - 1; i++) {
                if (chars[i] > chars[i + 1]) {
                    // 移除char[i]
                    for (int j = i; j < chars.length - 1; j++) {//这个循环代表，删除数组中的一个元素。
                        chars[j] = chars[j + 1];
                    }
                    flag = 0;
                    t--;
                    len--;
                    break;

                }
            }
            if (flag == 1 && t != 0) {//如果找了一圈没有任何移除，即flag为1，但k有余量时
                len--;    //那说明最后一个>=倒数第二个
                t--;    //所以length--，k--.相当于去除了chars数组的最后一个
            }
        }
        String res = new String(chars).substring(0, len).replaceFirst("^0*", "");
        return res.equals("") ? "0" : res;
    }


    /**
     * 从左到右扫描，遇到递减的数，就移除前面那个
     *
     * @param num
     * @param k
     * @return
     */
    public static String removeKdigits(String num, int k) {

        if (k == 0) {
            return num;
        }
        if (num.length() == k) {
            return "0";
        }
        //用栈动态的移除
        Stack<Character> stack = new Stack<>();
        stack.push(num.charAt(0));

        // 即将入栈的元素
        char willEntry;
        for (int i = 1; i < num.length(); i++) {
            willEntry = num.charAt(i);
            while (!stack.isEmpty() && willEntry < stack.peek() && k > 0) {
                stack.pop();
                k--;
            }
            stack.push(willEntry);
        }

        StringBuffer buffer = new StringBuffer();
        while (!stack.isEmpty()) {
            buffer.append(stack.pop());
        }
        return buffer.reverse().toString().substring(0, buffer.length() - k);
    }


    public static String removeKdigits_last(String num, int k) {

        if (k == 0) {
            return num;
        }
        if (num.length() == k) {
            return "0";
        }
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < num.length(); i++) {
            while (!stack.isEmpty() && stack.peek() > num.charAt(i) && k > 0) {
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
        }


        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            stringBuilder.append(stack.pop());
        }
        return stringBuilder.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(removeKdigits("10200", 1));
        System.out.println(removeKdigits("1432219", 3));
        System.out.println(removeKdigits("1430219", 3));
        System.out.println(removeKdigits("112", 1));
        System.out.println(removeKdigits("1173", 2));
        System.out.println(removeKdigits("1234567890", 9));


        /**
         *
         200
         1219
         0219
         11
         11
         0
         */
    }


}
