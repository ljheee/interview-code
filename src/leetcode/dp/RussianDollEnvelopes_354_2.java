package leetcode.dp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/russian-doll-envelopes/submissions/
 *
 * 官方题解
 * 总结：把 w,h 的 2 维递增问题转化为了h 的1维递增问题。 为此要，按照先 w 递增，w 相同时 h 递减的规则排序 排序后，求 h 的 1 维递增时不会重复选择相同的 w（根据排序规则可知 当 i < j 且 w[i]==w[j] 时 h[i] >= h[j] ）
 * https://leetcode-cn.com/problems/russian-doll-envelopes/solution/e-luo-si-tao-wa-xin-feng-wen-ti-by-leetcode/
 */
public class RussianDollEnvelopes_354_2 {


    /**
     * AC
     * 最长子序列的思想；
     * dp[i] 往前查找 最长前缀
     * @param envelopes
     * @return
     */
    public int maxEnvelopes1(int[][] envelopes) {

        if (envelopes.length <= 1) return envelopes.length;

        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
        });

        int dp[] = new int[envelopes.length];
        Arrays.fill(dp, 1);

        for (int i = 1; i < envelopes.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }


    public int maxEnvelopes(int[][] envelopes) {

        if (envelopes.length <= 1) return envelopes.length;

        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
        });
        Arrays.stream(envelopes).forEach(e -> System.out.print(Arrays.toString(e)));
        int w = envelopes[0][0];
        int h = envelopes[0][1];
        int dp[] = new int[envelopes.length];
        dp[0] = 1;
        process(envelopes, w, h, 1, dp);
        return Arrays.stream(dp).max().getAsInt();
    }

    /**
     * @param envelopes
     * @param w
     * @param h
     * @param from
     * @param dp        [from-1]       上一步 达到的数量
     * @return
     */
    private int process(int[][] envelopes, int w, int h, int from, int[] dp) {
        if (from == envelopes.length) return dp[from - 1];
        int i = from;
        if (envelopes[i][0] > w && envelopes[i][1] > h) {
            w = envelopes[i][0];
            h = envelopes[i][1];
            dp[i] = dp[from - 1] + 1;
            return process(envelopes, w, h, ++i, dp);
        } else {
            int preMax = 0;
            for (int j = from - 1; j >= 0; j--) {
                if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {
                    preMax = Math.max(preMax, dp[j]);
                }
            }
            dp[i] = dp[from - 1];
            int a = process(envelopes, w, h, i + 1, dp);
            dp[i] = 1 + preMax;
            int b = process(envelopes, envelopes[i][0], envelopes[i][1], i + 1, dp);
            return Math.max(a, b);
        }
    }


    public static void main(String[] args) {
        RussianDollEnvelopes_354_2 envelopes354 = new RussianDollEnvelopes_354_2();
        System.out.println(envelopes354.maxEnvelopes1(new int[][]{new int[]{1, 7}, new int[]{2, 3}, new int[]{2, 8}, new int[]{3, 9}}));
        System.out.println(envelopes354.maxEnvelopes1(new int[][]{new int[]{1, 7}, new int[]{2, 3}, new int[]{4, 8}, new int[]{5, 9}}));
        System.out.println(envelopes354.maxEnvelopes1(
                new int[][]{new int[]{1, 7}, new int[]{2, 3}, new int[]{4, 8}, new int[]{5, 9}, new int[]{6, 1}, new int[]{7, 2}, new int[]{8, 3}, new int[]{9, 4}}));

        System.out.println(envelopes354.maxEnvelopes1(
                new int[][]{new int[]{2, 3}, new int[]{4, 5}, new int[]{6, 1}, new int[]{7, 7}, new int[]{8, 8}, new int[]{9, 9}}));
        System.out.println(envelopes354.maxEnvelopes1(
                new int[][]{new int[]{4, 14}, new int[]{8, 18}, new int[]{16, 1}, new int[]{9, 11}, new int[]{14, 15}, new int[]{12, 19}
                        , new int[]{2, 15}
                        , new int[]{4, 4}
                        , new int[]{18, 3}
                        , new int[]{20, 8}
                        , new int[]{19, 18}
                        , new int[]{18, 2}
                        , new int[]{1, 10}
                        , new int[]{12, 1}
                        , new int[]{10, 16}
                        , new int[]{1, 1}
                        , new int[]{3, 19}
                }));


    }

}
