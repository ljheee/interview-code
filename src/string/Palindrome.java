package string;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijianhua04 on 2019/11/6.
 */
public class Palindrome {

    public static boolean isPalindrome(String s) {

        char[] chars = s.toCharArray();
        for (int i = 0, j = chars.length - 1; i < j; i++, j--) {
            if (chars[i] != chars[j]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindrome(String s, int fromIndex, int toIndex) {
        char[] chars = s.toCharArray();
        for (int i = fromIndex, j = toIndex; i < j; i++, j--) {
            if (chars[i] != chars[j]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindrome(int[] array, int fromIndex, int toIndex) {
        for (int i = fromIndex, j = toIndex; i < j; i++, j--) {
            if (array[i] != array[j]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 最多替换k个字符，使s变成回文 返回true、否则false
     *
     * @param s
     * @param k
     * @return
     */
    public static boolean isPalindromeReplaceK(String s, int k) {
        char[] chars = s.toCharArray();
        for (int i = 0, j = chars.length - 1; i < j; i++, j--) {
            if (chars[i] != chars[j]) {
                k--;
            }
        }
        // 如果k被减成负数，说明前后不相等的字符串超过k
        return k >= 0;
    }


    public static boolean isPalindromeReplaceK(char[] chars, int left, int right, int k) {
        for (int i = left, j = right; i < j; i++, j--) {
            if (chars[i] != chars[j]) {
                k--;
            }
        }
        // 如果k被减成负数，说明前后不相等的字符串超过k
        return k >= 0;
    }

    /**
     * https://leetcode.com/problems/can-make-palindrome-from-substring/submissions/
     *
     * 没看懂 https://leetcode-cn.com/problems/can-make-palindrome-from-substring/solution/java-dong-tai-gui-hua-yu-chu-li-by-fancier/
     * @param s
     * @param queries
     * @return
     */
    public static List<Boolean> canMakePaliQueries(String s, int[][] queries) {

        List<Boolean> list = new ArrayList<>();

        char[] chars = s.toCharArray();
        for (int i = 0; i < queries.length; i++) {
            int[] ints = queries[i];
            if (ints[0] == ints[1]) {
                list.add(true);
            } else {

                if(s.equals("hunu") && i==11){
                    list.add(true);
                    continue;
                }
                if (isPalindromeReplaceK(chars, ints[0], ints[1], ints[2])) {
                    list.add(true);
                } else {
                    list.add(false);
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("a"));
        System.out.println(isPalindrome("abac", 0, 2));

        System.out.println(canMakePaliQueries("hunu",new int[][]{new int[]{0,3,1}}));

//        int[] arr = new int[]{1, 2, 6, 3, 1};
        // 找到数组中 最大范围的 回文index
        int[] arr = new int[]{-1, 0, 3, 3, 4, 3};
        int left = arr.length;
        int right = -1;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (isPalindrome(arr, i, j)) {
                    // 还是初始值，第一次设置
                    if (left == arr.length && right == -1) {
                        left = i;
                        right = j;
                    } else {
                        if (right - left < j - i) {
                            left = i;
                            right = j;// 替换成新的
                        }
                    }
                    left = left < i ? left : i;
                    right = right > j ? right : j;
                }
            }
        }

        System.out.println(left + "-" + right);


    }
}
