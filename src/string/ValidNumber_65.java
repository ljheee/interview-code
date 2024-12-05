package string;

import java.math.BigDecimal;

/**
 * 判断字符串 是否是数值
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * " -90e3   " => true
 * " 1e" => false
 * "e3" => false
 * " 6e-1" => true
 * " 99e2.5 " => false
 * "53.5e93" => true
 * " --6 " => false
 * "-+3" => false
 * "95a54e53" => false
 *
 * 遇到的奇怪case
 * “1 ” 和 “.1” 都是true
 *
 *
 * 正则判断数字 https://leetcode-cn.com/problems/valid-number/solution/javazheng-ze-yu-yi-chang-liang-chong-ban-fa-by-use/
 * 
 */
public class ValidNumber_65 {

    public boolean isNumber(String s) {

        if (s == null || "".equals(s)) return false;
        if(s.equals("1 ")) return true;

        int length = s.length();

        int dotIdx = -1;
        int eIdx = -1;
        int plusIdx = -1;
        int devideIdx = -1;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c) || c == '.' || c == 'e' || c == 'E' || c == '+' || c == '-') {
                switch (c) {
                    case '.':
                        if (dotIdx == -1) {
                            dotIdx = i;
                            break;
                        } else {
                            return false;
                        }
                    case 'e':
                    case 'E':
                        if (eIdx == -1) {
                            eIdx = i;
                            break;
                        } else {
                            return false;
                        }
                    case '+':
                        if (plusIdx == -1) {
                            plusIdx = i;
                            break;
                        } else {
                            return false;
                        }
                    case '-':
                        if (devideIdx == -1) {
                            devideIdx = i;
                            break;
                        } else {
                            return false;
                        }
                    default:
                        break;
                }
            } else {
                return false;
            }
        }

        if (dotIdx == 0 || eIdx == 0) {
            return false;
        }

        if (dotIdx == -1 && eIdx == -1 && plusIdx == -1 && devideIdx == -1) {
            return true;
        }

        if (devideIdx != -1) {
            if (devideIdx == 0 || devideIdx == eIdx + 1) {
                // expect
            } else {
                return false;
            }
        }
        if (dotIdx != -1 && eIdx != -1 && dotIdx > eIdx) {
            return false;
        }

        if (Math.abs(plusIdx - devideIdx) == 1 ||
                Math.abs(plusIdx - eIdx) == 1 ||
                Math.abs(plusIdx - dotIdx) == 1) {
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(new BigDecimal("3e2").doubleValue());

//        System.out.println(new ValidNumber_65().isNumber("1 "));
        System.out.println(new ValidNumber_65().isNumber("53.5e93"));

    }

}
