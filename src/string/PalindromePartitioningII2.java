package string;

/**
 * https://leetcode.com/problems/palindrome-partitioning-ii/
 */
public class PalindromePartitioningII2 {

    Integer count = 0;
    Integer reverseCount = 0;//反转

    public int minCut(String s) {
        char[] chars = s.toCharArray();
        minCut(chars, 0, s.length() - 1);

        char[] reverseChars = new char[chars.length];
        int j = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            reverseChars[j++] = chars[i];
        }
        minCutReverse(reverseChars, 0, s.length() - 1);

        return count < reverseCount ? count : reverseCount;
    }

    private void minCut(char[] chars, int fromIndex, int toIndex) {

        if (fromIndex == toIndex) {
            return;//只有一个 字符 就return，不用切割
        }
        String tf = "t";
        for (int j = fromIndex + 1; j <= toIndex; j++) {

            if (isPalindrome(chars, fromIndex, j)) {
                tf = tf + "t";
            } else {
                tf = tf + "f";
            }
        }
        int lastIndexOfT = tf.lastIndexOf("t") + fromIndex;
        if (lastIndexOfT == chars.length - 1) {
            if (count == 0) {
                count = 0;
            }
        } else {
            count++;
            minCut(chars, lastIndexOfT + 1, toIndex);
        }
    }

    private void minCutReverse(char[] chars, int fromIndex, int toIndex) {

        if (fromIndex == toIndex) {
            return;//只有一个 字符 就return，不用切割
        }
        String tf = "t";
        for (int j = fromIndex + 1; j <= toIndex; j++) {

            if (isPalindrome(chars, fromIndex, j)) {
                tf = tf + "t";
            } else {
                tf = tf + "f";
            }
        }
        int lastIndexOfT = tf.lastIndexOf("t") + fromIndex;
        if (lastIndexOfT == chars.length - 1) {
            if (reverseCount == 0) {
                reverseCount = 0;
            }
        } else {
            reverseCount++;
            minCutReverse(chars, lastIndexOfT + 1, toIndex);
        }
    }

    public static boolean isPalindrome(char[] chars, int fromIndex, int toIndex) {
        for (int i = fromIndex, j = toIndex; i < j; i++, j--) {
            if (chars[i] != chars[j]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new PalindromePartitioningII2().minCut("aabaabbcc"));//2
        System.out.println(new PalindromePartitioningII2().minCut("a"));//0
        System.out.println(new PalindromePartitioningII2().minCut("aab"));//1
        System.out.println(new PalindromePartitioningII2().minCut("aaabaa"));//1  正向切3次，反向切2次
    }
}
