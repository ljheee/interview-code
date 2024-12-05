package leetcode.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * 划分字母区间
 * 切分字符串：使“同一个字母只会出现在其中的一个片段”
 * https://leetcode.com/problems/partition-labels/
 * <p>
 * 假设第一个字母是 'a'，那么第一个区间一定包含最后一次出现的 'a'。
 * 但第一个出现的 'a' 和最后一个出现的 'a' 之间可能还有其他字母，这些字母会让区间变大。
 * 举个例子，在 "abccaddbeffe" 字符串中，第一个最小的区间是 "abccaddb"。
 * 贪心：就是让该区间尽可能的小
 */
public class PartitionLabels_763 {

    public List<Integer> partitionLabels(String S) {
        List<Integer> list = new ArrayList<>();
        char[] chars = S.toCharArray();
        int[] lastIndexs = new int[chars.length];

        for (int i = 0; i < chars.length; i++) {
            lastIndexs[i] = S.lastIndexOf(chars[i]);
        }

        for (int i = 0; i < chars.length; ) {
            int lastIndex = lastIndexs[i];

            for (int j = i; j <= lastIndex; j++) {
                if (lastIndexs[j] > lastIndex) {
                    lastIndex = lastIndexs[j];
                }
            }
            list.add(S.substring(i, lastIndex + 1).length());// 记录子串长度，直接：lastIndex + 1-i
            i = lastIndex + 1;
        }

        return list;
    }


    /**
     * https://leetcode-cn.com/problems/partition-labels/solution/hua-fen-zi-mu-qu-jian-by-leetcode/
     * 第一个for循环通过相同的字符作为下标来覆盖，从而得到最后一个字符的位置，好奇妙啊
     *
     * @param S
     * @return
     */
    public List<Integer> partitionLabels1(String S) {
        int[] last = new int[26];
        for (int i = 0; i < S.length(); ++i)
            last[S.charAt(i) - 'a'] = i;

        int j = 0, anchor = 0;
        List<Integer> ans = new ArrayList();
        for (int i = 0; i < S.length(); ++i) {
            j = Math.max(j, last[S.charAt(i) - 'a']);
            if (i == j) {
                ans.add(i - anchor + 1);
                anchor = i + 1;
            }
        }
        return ans;
    }

}
