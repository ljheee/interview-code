package string;

import java.util.Arrays;

/**
 * KMP 字符串匹配
 */
public class KMP {


    /**
     * 返回substring在s中首次出现的位置，，如果不存在，则返回-1
     *
     * @param s
     * @param substring
     * @return
     */
    public static int getIndexOf(String s, String substring) {
        if ("".equals(substring)) return 0;

        char[] str1 = s.toCharArray();
        char[] str2 = substring.toCharArray();
        int[] next = getNext(substring);

        // s的指针
        int i1 = 0;
        // substring的指针
        int i2 = 0;
        while (i1 < str1.length && i2 < str2.length) {
            if (str1[i1] == str2[i2]) {
                i1++;
                i2++;
            } else if (next[i2] >= 0) {
                // i2还能利用next[] 加速
                i2 = next[i2];
            } else {
                // substring已到开头，一直没匹配上
                i1++;
            }
        }
        return i2 == str2.length ? i1 - i2 : -1;
    }

    /**
     * 特殊case验证：bbabba
     *
     * @param s
     * @return
     */
    private static int[] getNext(String s) {
        if (s.length() == 1) return new int[]{-1};

        int[] next = new int[s.length()];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        int i = 2;
        while (i < s.length()) {
            if (s.charAt(i - 1) == s.charAt(cn)) {
                next[i] = ++cn;
                i++;
            } else if (next[cn] > 0) {// why not next[cn]>0?
                cn = next[cn];
            } else {
                next[i] = 0;
                i++;
            }
        }
        return next;
    }


    public static void main(String[] args) {

//        String s = "bbabba";
        String s = "aabaaac";
        System.out.println(Arrays.toString(s.toCharArray()));
        System.out.println(Arrays.toString(getNext(s)));


//        System.out.println(getIndexOf("abcdefg", "cde"));//2
//        System.out.println(getIndexOf("abclkdefg", "kd"));//4
//        System.out.println(getIndexOf("abclkdefg", "fg"));//7
//        System.out.println(getIndexOf("abclkdefg", "g"));//8
//        System.out.println(getIndexOf("abclkdefg", ""));//0
//        System.out.println(getIndexOf("", ""));//0
//        System.out.println(getIndexOf("abcd", "fe"));//-1

        /**
         * 下面几个都是特殊case，检测边界条件和bug
         * https://leetcode.com/submissions/detail/334239903/
         */
//        System.out.println(getIndexOf("mississippi", "issipi"));//-1
//        System.out.println(getIndexOf("bbababaaaababbaabbbabbbaaabbbaaababbabaabbaaaaabbaaabbbbaaabaabbaababbbaabaaababbaaabbbbbbaabbbbbaaabbababaaaaabaabbbababbaababaabbaa", "bbabba"));//-1
        System.out.println(getIndexOf("aabaaabaaac", "aabaaac"));//-1
    }

}