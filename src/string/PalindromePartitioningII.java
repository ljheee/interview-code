package string;

/**
 * https://leetcode.com/problems/palindrome-partitioning-ii/
 * <p>
 * 单个字符 可构成回文
 * 思路：从左到右扫描，得出第一个最长回文，递归后面的子串，递归次数即为最小切割次数；
 * <p>
 * <p>
 * <p>
 * Integer 不是引用传递，而是值传递！
 * 基本类型的包装类型和String都不能直接当纯引用类型来用，比如Integer,Double等都是int与double的包装类型，但是你不能像普通引用类型那样直接对他的值做改变，因为在他们里面封装的原始int与double都用了final进行声明。所以你就算重新赋值了原始的int与double都不会改变。
 */
public class PalindromePartitioningII {


    public int minCut(String s) {


        Integer count = new Integer(0);
        Integer reverseCount = new Integer(0);//反转

        char[] chars = s.toCharArray();
        minCut(chars, 0, s.length() - 1, count);

        char[] reverseChars = new char[chars.length];
        int j = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            reverseChars[j++] = chars[i];
        }
        minCut(reverseChars, 0, s.length() - 1, reverseCount);

        return count < reverseCount ? count : reverseCount;
    }

    private void minCut(char[] chars, int fromIndex, int toIndex, Integer count) {

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
            minCut(chars, lastIndexOfT + 1, toIndex, count);
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
//        System.out.println(new PalindromePartitioningII().minCut("aabaabbcc"));//2
//        System.out.println(new PalindromePartitioningII().minCut("a"));//0
        System.out.println(new PalindromePartitioningII().minCut("aab"));//1
    }
}
