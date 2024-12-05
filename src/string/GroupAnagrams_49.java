package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 字母异位词分组
 * 同分异构词 分组
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * <p>
 * 只能每个单词排序？
 * 官方题解：使用数组映射26个字母出现的频率，拼接后作为key（用作分组标识）;
 * <p>
 * 最终目标：就是找出当前strs[i]应该分到哪个组，也就是需要用一种办法找出 同分异构词 相同的映射；
 * https://leetcode-cn.com/problems/group-anagrams/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--16/
 * 提出的新解法：质数累乘，作为映射key；
 */
public class GroupAnagrams_49 {

    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<String, List<String>> stringToIndexMap = new HashMap<>();


        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            char[] chars = str.toCharArray();
            Arrays.sort(chars);

            String key = String.valueOf(chars);
            if (stringToIndexMap.get(key) == null) {
                List<String> list = new ArrayList<>();
                list.add(str);
                stringToIndexMap.put(key, list);
            } else {
                stringToIndexMap.get(key).add(str);
            }
        }
        return stringToIndexMap.values().stream().collect(Collectors.toList());
    }


    /**
     * Group Shifted Strings - CSDN博客
     * https://blog.csdn.net/u013325815/article/details/51887225
     */


    /**
     * 判断 同字母异序
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int a = 0;
        int c = 0;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i) - 'a';
            a += c * c * c;
        }

        int b = 0;
        for (int i = 0; i < t.length(); i++) {
            c = t.charAt(i) - 'a';
            b += c * c * c;
        }

        return a == b;
    }

    /**
     * 质数相乘解法
     *
     * s和t非常长，累乘超出double范围
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};
        double a = 1;
        for (int i = 0; i < s.length(); i++) {
            a *= prime[s.charAt(i) - 'a'];
        }
        double b = 1;
        for (int i = 0; i < t.length(); i++) {
            b *= prime[t.charAt(i) - 'a'];
        }

        return a == b;
    }
}
