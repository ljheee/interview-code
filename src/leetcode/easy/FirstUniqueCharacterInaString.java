package leetcode.easy;

import java.util.*;

/**
 * https://leetcode.com/problems/first-unique-character-in-a-string/
 * 返回下标索引，不存在返回-1
 * <p>
 * 1、LinkedHashmap put时判断，存在就移除，遍历完取peek()，剩下的头就是firstChar
 * 空间复杂度O(n)
 * 2、排序，arr[i]!=arr[j]的，----但是排序就乱序了
 * <p>
 * 解法二：用map保存char出现的频率，然后遍历一般：从map中获取频率，频率1 直接返回
 * https://leetcode-cn.com/problems/first-unique-character-in-a-string/solution/zi-fu-chuan-zhong-de-di-yi-ge-wei-yi-zi-fu-by-leet/
 */
public class FirstUniqueCharacterInaString {

    // 思路还OK，不过超时
    public int firstUniqChar(String s) {
        char[] chars = s.toCharArray();

        HashSet<Character> set = new HashSet<>();
        LinkedHashMap<Character, Character> map = new LinkedHashMap<>();
        for (int i = 0; i < chars.length; i++) {
            Character character = map.get(chars[i]);
            if (character == null && set.add(chars[i])) {
                map.put(chars[i], chars[i]);
            } else {
                map.remove(chars[i]);
            }
        }

        if (map.size() == 0) {
            return -1;
        } else {
            Character first = map.keySet().iterator().next();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == first) {
                    return i;
                }
            }
        }

        return -1;
    }

    /**
     * 亲密字符串
     * <p>
     * 给定两个由小写字母构成的字符串 A 和 B ，只要我们可以通过交换 A 中的两个字母得到与 B 相等的结果，就返回 true ；否则返回 false 。
     * <p>
     * https://leetcode-cn.com/problems/buddy-strings/solution/qin-mi-zi-fu-chuan-by-leetcode/
     * 遍历一遍的技巧：遍历A的时候的下标i，可以直接获取到B对应的字符:唯一找到两个下标 交换后相等
     * <p>
     * ----------------------------------------------------
     * 0 1 2 3
     * a a b c
     * a a c b
     * ii=2
     * jj=3
     * charsA[ii] == charsB[jj] && charsA[jj] == charsB[ii]
     * ----------------------------------------------------
     *
     * @param A
     * @param B
     * @return
     */

    public boolean buddyStrings(String A, String B) {
        if (A.length() != B.length()) {
            return false;
        }

        // 输入为aa,aa;ab,ab的case
        if (A.equals(B)) {
            int[] count = new int[26];
            for (int i = 0; i < A.length(); ++i)
                count[A.charAt(i) - 'a']++;

            for (int c : count)
                if (c > 1) return true;
            return false;
        } else {
            // A,B不相等，遍历一遍A，同时也能用索引拿到B的字符，找到唯一两个索引ii,jj，交换后相等
            char[] charsA = A.toCharArray();
            char[] charsB = B.toCharArray();
            int ii = -1, jj = -1;
            for (int i = 0; i < charsA.length; i++) {
                if (charsA[i] != charsB[i]) {
                    if (ii == -1) {
                        ii = i;
                        continue;
                    } else if (jj == -1) {
                        jj = i;
                    } else {
                        return false;//输入abcd,badc的case，扫描完ab不能立即判断
                    }
                }
            }
            if (charsA[ii] == charsB[jj] && charsA[jj] == charsB[ii]) {
                //交叉交换后相等
                return true;
            }
        }
        return false;
    }




}
