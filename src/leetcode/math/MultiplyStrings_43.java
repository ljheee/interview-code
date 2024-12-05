package leetcode.math;

import java.util.Arrays;

/**
 * 字符串乘法
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * https://leetcode.com/problems/multiply-strings/
 */
public class MultiplyStrings_43 {

/*
	123
	123
	-------
	369
   246
  123
------------
  15129
*/

    /**
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {

        if (num1.length() > num2.length()) {
            return multiply(num2, num1);
        }
        String res = "";
        char[] chars = num1.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            int n = chars[i] - '0';
            StringBuilder temp = new StringBuilder();
            for (int j = 0; j < chars.length - i - 1; j++) {
                temp.append(0);
            }

            // n * num2
            int carry = 0;
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int val = n * n2 + carry;
                temp.append(val % 10);
                carry = val / 10;
            }
            if (carry > 0) {
                temp.append(carry);
            }
            res = add(res, temp.reverse().toString());
        }

        return res;
    }

    public String add(String num1, String num2) {

        if (num1.length() == 0) {
            return num2;
        }

        StringBuilder temp = new StringBuilder();

        int i = num1.length() - 1;
        int j = num2.length() - 1;

        int carry = 0;
        while (i >= 0 || j >= 0) {

            int val = carry;
            if (i >= 0) {
                val += +num1.charAt(i) - '0';
            }
            if (j >= 0) {
                val += +num2.charAt(j) - '0';
            }

            temp.append(val % 10);
            carry = val / 10;
            i--;
            j--;
        }
        if (carry > 0) {
            temp.append(carry);
        }
        return temp.reverse().toString();
    }



    public static void main(String[] args) {
        String multiply = new MultiplyStrings_43().multiply("123", "456");
        System.out.println(multiply);

    }
}
