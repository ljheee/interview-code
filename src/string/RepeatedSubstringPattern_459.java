package string;

import java.util.Arrays;

/**
 * 找出叠词
 * 如abcabcabc,叠词部分abc
 * <p>
 * 利用KMP的next数组，找出前后相等的子串
 * Created by lijianhua04 on 2020/4/11.
 */
public class RepeatedSubstringPattern_459 {

    public boolean repeatedSubstringPattern0(String s) {

        if (s.length() == 3) {
            return false;
        }
        char c = s.charAt(0);

        int lastIdx = s.lastIndexOf(c);
        System.out.println(lastIdx);
        if (s.substring(lastIdx).equals(s.substring(0, s.length() - lastIdx))) {
            return true;
        }

        return false;
    }

    public boolean repeatedSubstringPattern(String s) {

        int last = getLastNext(s);
        if (last == 0) return false;
        if (last == 1) return s.charAt(0) == s.charAt(1);

        // 最小重复单元
        int minStep = s.length() - last;

        // case 不是很刁钻，理应验证 每个minStep单元是否相等
        // 此处直接用长度验证
        if (s.length() / minStep * minStep == s.length()) {
            return true;
        }

        return false;
    }


    /**
     * 返回 next 数组的最后一个值
     * https://leetcode-cn.com/problems/shortest-palindrome/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--44/
     *
     * @param s
     * @return
     */
    public static int getLastNext(String s) {
        int n = s.length();
        char[] c = s.toCharArray();
        int[] next = new int[n + 1];
        next[0] = -1;
        next[1] = 0;
        int k = 0;
        int i = 2;
        while (i <= n) {
            if (k == -1 || c[i - 1] == c[k]) {
                next[i] = k + 1;
                k++;
                i++;
            } else {
                k = next[k];
            }
        }
        System.out.println(Arrays.toString(next));
        return next[n];
    }


    /**
     * 巧妙解法
     * https://leetcode-cn.com/problems/repeated-substring-pattern/solution/c-findhan-shu-yong-fa-by-lo_e/
     * 如果一个非空字符串s可以由它的一个子串重复多次构成，可以理解为s中存在m个子串，那么当两个字符串结合起来变成ss时，字符串s在新字符串ss的第二次位置不等于s的长度（相当于前一个字符串s中有n个子串，在后一个字符串中有m-n个子串，所以此时的位置不等于s的长度）
     *
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern1(String s) {
        return (s + s).indexOf(s, 1) != s.length();
    }


    public int repeatedStringMatch(String A, String B) {
        if (A.equals(B) || A.contains(B)) {
            return 1;
        }
        if (B.indexOf(A) == -1) {
            return -1;
        }


        int ans = 1;
        StringBuffer stringBuffer = new StringBuffer(A);
        while (stringBuffer.indexOf(B) == -1) {
            stringBuffer.append(A);
            ans++;
            if (ans > 10000) return -1;
        }
        return ans;
    }

    /**
     * A 重复第三次，才能包含B(让B是A的子串)；
     * <p>
     * https://leetcode.com/problems/repeated-string-match/
     *
     * @param A
     * @param B
     * @return
     */
    public int repeatedStringMatch1(String A, String B) {

        int q = 1;
        StringBuffer s = new StringBuffer(A);
        for (; s.length() < B.length(); q++) {
            s.append(A);
        }

        if (s.indexOf(B) > 0) return q;
        if (s.append(A).indexOf(B) > 0) return q + 1;
        return -1;
    }


    public boolean repeatedSubstringPattern2(String s) {

        int[] hs = new int[s.length()];
        hs[0] = 0 + (s.charAt(0) - 'a' + 1);
        int segmentSize = -1;
        for (int i = 1; i < hs.length; i++) {
            hs[i] = hs[i - 1] + (s.charAt(i) - 'a' + 1);

            if (i % 2 == 1) {
                if (hs[i]/2 == hs[(i - 1) / 2]) {
                    segmentSize = (i - 1) / 2;
                }
            }
        }
        System.out.println(segmentSize);
        return true;
    }





    public static void main(String[] args) {
//        System.out.println(new RepeatedSubstringPattern_459().repeatedSubstringPattern("abcabc"));
//        System.out.println(new RepeatedSubstringPattern_459().repeatedSubstringPattern2("abcabc"));


        System.out.println(Arrays.toString(getInts("aabaab")));
        System.out.println(Arrays.toString(getInts("aababa")));


    }

    private static int[] getInts(String a) {
        int[] hs = new int[a.length()];
        for (int i = 0; i < a.length(); i++) {
            hs[i] = (a.charAt(i) - 'a' + 1) +i*i;
        }
        return hs;
    }

}
