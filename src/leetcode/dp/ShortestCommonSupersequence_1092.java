package leetcode.dp;

/**
 * 最短公共超序列
 * https://leetcode-cn.com/problems/shortest-common-supersequence/
 * 输入：str1 = "abac", str2 = "cab"
 * 输出："cabac"
 * 解释：
 * str1 = "abac" 是 "cabac" 的一个子串，因为我们可以删去 "cabac" 的第一个 "c"得到 "abac"。
 * str2 = "cab" 是 "cabac" 的一个子串，因为我们可以删去 "cabac" 末尾的 "ac" 得到 "cab"。
 * 最终我们给出的答案是满足上述属性的最短字符串。
 */
public class ShortestCommonSupersequence_1092 {

    public String shortestCommonSupersequence(String str1, String str2) {

        if (str1.length() < str2.length()) {
            return shortestCommonSupersequence(str2, str1);
        }
        // TODO: 2020/6/7  


        return str1;
    }
}
