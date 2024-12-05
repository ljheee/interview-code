package leetcode.sliding;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/get-equal-substrings-within-budget/
 */
public class GetEqualSubstringsWithinBudget_1208 {

    /**
     * 题意要求 变更连续子串
     *
     * @param s
     * @param t
     * @param maxCost
     * @return
     */
    public static int equalSubstringX(String s, String t, int maxCost) {

        int dist[] = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            dist[i] = s.charAt(i) - t.charAt(i);
            if (dist[i] < 0) {
                dist[i] = -dist[i];
            }
        }
        Arrays.sort(dist);
        int ans = 0;
        for (int i = 0; i < dist.length; i++) {
            if (maxCost - dist[i] > 0) {
                ans++;
                maxCost -= dist[i];
            } else {
                break;
            }
        }
        return ans;
    }


    /**
     * 滑动窗口解法
     *
     * @param s
     * @param t
     * @param maxCost
     * @return
     */
    public static int equalSubstring(String s, String t, int maxCost) {

        int dist[] = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            dist[i] = s.charAt(i) - t.charAt(i);
            if (dist[i] < 0) {
                dist[i] = -dist[i];
            }
        }
        int ans = 0;

        int count = 0;
        int cost = 0;
        int left = 0;
        int r = 0;
        for (r = 0; r < dist.length; r++) {
            cost += dist[r];
            count++;
            while (cost > maxCost) {
                cost -= dist[left];
                left++;
                count--;
                ans = Math.max(ans, count);
            }
        }
        ans = Math.max(ans, count);
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/longest-turbulent-subarray/solution/zui-chang-tuan-liu-zi-shu-zu-by-leetcode/
     *
     * @param A
     * @return
     */
    public int maxTurbulenceSize(int[] A) {

        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] < A[i + 1]) {
                A[i] = -1;
            } else if (A[i] == A[i + 1]) {
                A[i] = 0;
            } else {
                A[i] = 1;
            }
        }

        int ans = 1;
        int anchor = 0;

        for (int i = 1; i < A.length; i++) {
            int c = A[i - 1];
            if (i == A.length - 1 || A[i] == 0 || A[i] * c != -1) {
                if (c != 0) ans = Math.max(ans, i - anchor + 1);
                anchor = i;
            }
            continue;
        }
        return ans;
    }



}
