package leetcode.dp;

import java.util.HashMap;

/**
 * 最长等差 子序列；
 * 子序列中 相邻元素的差为different
 * https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference/
 *
 * 一维数组dp https://leetcode-cn.com/problems/longest-arithmetic-subsequence-of-given-difference/solution/jian-dan-de-dp-by-97wgl/
 *
 * <p>
 * Created by lijianhua04 on 2020/2/9.
 */
public class LongestSubsequence_1218 {

    public int longestSubsequence(int[] arr, int difference) {

        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            int val = map.getOrDefault(i - difference, 0) + 1;
            map.put(i, val);
            ans = Math.max(ans, val);
        }

        return ans;
    }


}
