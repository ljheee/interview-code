package leetcode.greedy;

/**
 * 切分字符串：使得每个子串L和R 字符数目相等；
 * 返回最大 切割数量
 * https://leetcode.com/problems/split-a-string-in-balanced-strings/submissions/
 */
public class SplitBalancedStrings_1221 {


    public int balancedStringSplit(String s) {
        char[] chars = s.toCharArray();
        int count = 0;

        int left = 0;
        int right = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'L') {
                left++;
            } else {
                right++;
            }
            if (left > 0 && right > 0 && left == right) {
                count++;
                left = 0;
                right = 0;
            }

        }
        return count;
    }



}
