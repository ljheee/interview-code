package string;

import java.util.Arrays;

/**
 * 不使用BigInteger 实现大数相加
 * <p>
 * 方法：转成字符数组，倒序开始相加；需要考虑长度不相等 有剩余的情况
 * https://leetcode.com/problems/add-strings/
 *
 * faster than 15.01%
 */
public class AddStrings {
    public String addStrings(String num1, String num2) {

        if (num1.equals("0") && num2.equals("0")) {
            return "0";
        }
        if (num1.equals("0") || num2.equals("0")) {
            return num1.equals("0") ? num2 : num1;
        }

        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();

        int minLen = 0;
        int maxLen = 0;

        if (chars1.length - chars2.length >= 0) {
            maxLen = chars1.length;
            minLen = chars2.length;
        } else {
            maxLen = chars2.length;
            minLen = chars1.length;
        }

        int[] arr = new int[maxLen + 1];// 结果
        int arrIndex = arr.length - 1;

        //进位
        int carry = 0;

        for (int i = chars1.length - 1,
             j = chars2.length - 1; i >= 0 && j >= 0; i--, j--) {
            int val = carry + chars1[i] + chars2[j] - '0' - '0';
            carry = val / 10;
            arr[arrIndex--] = val % 10;
        }

        // 两个数组 长度相等
        if (maxLen == minLen) {
            // 可以返回了
            if (carry != 0) {
                arr[arrIndex] = carry;
            }

            String s = Arrays.toString(arr);

            int subStringFrom = 1;// 检查首位，是0则去除
            if (arr[0] == 0) {
                subStringFrom = 2;
            }
            return s.substring(subStringFrom, s.length() - 1).replaceAll(", ", "");
        }

        // 只剩下 chars1
        if (chars1.length == maxLen) {
            for (int i = chars1.length - minLen - 1; i >= 0; i--) {
                int val = carry + chars1[i] - '0';
                carry = val / 10;
                arr[arrIndex--] = val % 10;
            }
        }

        // 只剩下 chars2
        if (chars2.length == maxLen) {
            for (int i = chars2.length - minLen - 1; i >= 0; i--) {
                int val = carry + chars2[i] - '0';
                carry = val / 10;
                arr[arrIndex--] = val % 10;
            }
        }

        if (carry != 0) {
            arr[arrIndex] = carry;
        }

        String s = Arrays.toString(arr);
        int subStringFrom = 1;// 检查首位，是0则去除
        if (arr[0] == 0) {
            subStringFrom = 2;
        }
        return s.substring(subStringFrom, s.length() - 1).replaceAll(", ", "");
    }


    public static void main(String[] args) {

        System.out.println(new AddStrings().addStrings("1", "1"));
    }
}
