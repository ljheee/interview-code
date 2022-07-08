package leetcode.dp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by lijianhua04 on 2020/7/5.
 */
public class RussianDollEnvelopes_354 {


    int[] dp = null;

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
        dp = new int[envelopes.length];
        dp[0] = 1;
        process(envelopes, w, h, 1, 1);
        return Arrays.stream(dp).max().getAsInt();
    }

    /**
     * @param envelopes
     * @param w
     * @param h
     * @param from
     * @param cur       上一步 达到的数量
     * @return
     */
    private int process(int[][] envelopes, int w, int h, int from, int cur) {
        if (from == envelopes.length) return cur + 0;
        int i = from;
        if (envelopes[i][0] > w && envelopes[i][1] > h) {
            w = envelopes[i][0];
            h = envelopes[i][1];
            dp[i] = cur + 1;
            return process(envelopes, w, h, ++i, cur + 1);
        } else {
            int preMax = 0;
            for (int j = from - 1; j >= 0; j--) {
                if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {
                    preMax = Math.max(preMax, dp[j]);
                }
            }
            dp[i] = cur;
            int a = process(envelopes, w, h, i + 1, cur);
            dp[i] = 1 + preMax;
            int b = process(envelopes, envelopes[i][0], envelopes[i][1], i + 1, 1 + preMax);
            return Math.max(a, b);
        }
    }


    public static void main(String[] args) {
        RussianDollEnvelopes_354 envelopes354 = new RussianDollEnvelopes_354();
//        System.out.println(envelopes354.maxEnvelopes(new int[][]{new int[]{1, 7}, new int[]{2, 3}, new int[]{2, 8}, new int[]{3, 9}}));
//        System.out.println(envelopes354.maxEnvelopes(new int[][]{new int[]{1, 7}, new int[]{2, 3}, new int[]{4, 8}, new int[]{5, 9}}));
//        System.out.println(envelopes354.maxEnvelopes(
//                new int[][]{new int[]{1, 7}, new int[]{2, 3}, new int[]{4, 8}, new int[]{5, 9}, new int[]{6, 1}, new int[]{7, 2}, new int[]{8, 3}, new int[]{9, 4}}));
//
//        System.out.println(envelopes354.maxEnvelopes(
//                new int[][]{new int[]{2, 3}, new int[]{4, 5}, new int[]{6, 1}, new int[]{7, 7}, new int[]{8, 8}, new int[]{9, 9}}));
        System.out.println(envelopes354.maxEnvelopes(
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


//        System.out.println(Arrays.toString(envelopes354.dp));
    }

}
