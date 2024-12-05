package leetcode.dp;

/**
 * Created by lijianhua04 on 2020/5/28.
 */
public class ShortestCommonSupersequence {



    /**
     * 输入：str1 = "abac", str2 = "cab"
     * 输出："cabac"
     * 解释：
     * str1 = "abac" 是 "cabac" 的一个子串，因为我们可以删去 "cabac" 的第一个 "c"得到 "abac"。
     * str2 = "cab" 是 "cabac" 的一个子串，因为我们可以删去 "cabac" 末尾的 "ac" 得到 "cab"。
     * 最终我们给出的答案是满足上述属性的最短字符串。
     *
     * @param str1
     * @param str2
     * @return
     */
    public String shortestCommonSupersequence(String str1, String str2) {

        if (str1.equals(str2)) return str1;
        if (str2.contains(str1)) return str2;
        if (str1.contains(str2)) return str1;


        if (str1.length() < str2.length()) {
            return shortestCommonSupersequence(str2, str1);
        }

        int i = 0, j = 0;
        while (i < str1.length() && j < str2.length()) {
            if (str1.charAt(i) == str2.charAt(j)) {
                i++;
                j++;
            } else {
//                if (str1.indexOf(str2.charAt(j), i) > 0 &&) {
//
//                }
            }
        }


        return "";
    }
}
