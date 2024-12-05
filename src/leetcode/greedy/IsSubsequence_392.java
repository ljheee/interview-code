package leetcode.greedy;

import java.util.*;
import java.util.stream.Collectors;

/**
 * s是否为t的子序列
 * 子序列是从t中移除任意个字符；
 * https://leetcode.com/problems/is-subsequence/submissions/
 */
public class IsSubsequence_392 {


    public boolean isSubsequence(String s, String t) {
        char[] chars = s.toCharArray();
        char[] tChars = t.toCharArray();
        int idx = -1;

        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            found = false;
            for (int i1 = idx + 1; i1 < tChars.length; i1++) {
                if (chars[i] == tChars[i1]) {
                    idx = i1;
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    /**
     * 另一种解法：遍历长串t，看遍历到的字符 是否依次是子序列
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence2(String s, String t) {
        char[] tChars = t.toCharArray();
        int idx = 0;

        for (int i = 0; i < tChars.length && idx < s.length(); i++) {
            if (tChars[i] == s.charAt(idx)) {
                idx++;
            }
        }
        return idx == s.length();
    }

    // TODO: 2020/2/10 IsSubsequence_392 贪心、DP解法

    public static void main(String[] args) {
        new IsSubsequence_392().isSubsequence("abc", "ahbgdc");


        new IsSubsequence_392().numMatchingSubseq("abcde", new String[]{"a", "bb", "acd", "ace"});


        LinkedList lList = new LinkedList();
        lList.add("1");
        lList.add("2");
        lList.add("3");
        lList.add("4");
        lList.add("5");

        System.out.println("LinkedList contains : " + lList);
        boolean isRemoved = lList.remove("2");
        System.out.println("LinkedList contains : " + lList);

        for (int i = 0; i < lList.size(); i++) {
            if (i == 2) {
                lList.remove(i);
            }
            System.out.println(lList.get(i));
        }
        System.out.println("LinkedList contains : " + lList);





    }


    /**
     * 匹配字符串数组：返回words中S子序列的个数
     * https://leetcode.com/problems/number-of-matching-subsequences/submissions/
     * 暴力循环会超时 https://leetcode-cn.com/problems/number-of-matching-subsequences/solution/pi-pei-zi-xu-lie-de-dan-ci-shu-by-leetcode/
     * <p>
     * 只将S遍历一遍:
     * 遍历S每一个字符时，将words数组 首位相同的字符去除，最终 还留下的是“非子序列”
     */
    public int numMatchingSubseq(String S, String[] words) {

        // words去重，避免超时
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        words = map.keySet().toArray(new String[0]);
        String[] clone = words.clone();

        char[] chars = S.toCharArray();

        int ans = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            for (int j = 0; j < words.length; j++) {
                if ("".equals(words[j])) {
                    continue;
                }
                if (words[j].charAt(0) == c) {
                    if (words[j].length() == 1) {
                        words[j] = "";
                        ans += map.get(clone[j]);
                    } else {
                        words[j] = words[j].substring(1);
                    }
                }
            }
        }
        return ans;
    }

    /**
     * https://leetcode.com/problems/smallest-string-with-swaps/
     *
     * @param s
     * @param pairs
     * @return
     */
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {

        Collections.sort(pairs, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(0) - o2.get(0);
            }
        });
        return s;
    }

}


