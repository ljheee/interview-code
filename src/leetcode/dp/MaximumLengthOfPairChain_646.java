package leetcode.dp;

import java.util.*;

/**
 * https://leetcode.com/problems/maximum-length-of-pair-chain/submissions/
 */
public class MaximumLengthOfPairChain_646 {


    /**
     * 按最长递增子序列的思路，不行；
     * 不过的case：[[3,4],[2,3],[1,2]]
     * 得排序
     *
     * @param pairs
     * @return
     */
    public int findLongestChain(int[][] pairs) {
        if (pairs.length == 0) return 0;

        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        int[] dp = new int[pairs.length];
        dp[0] = 1;
        dp[1] = pairs[1][0] > pairs[0][1] ? 2 : 1;


        int maxPair = 1;
        for (int i = 2; i < dp.length; i++) {
            int[] pair = pairs[i];

            for (int j = i - 1; j >= 0; j--) {
                if (pairs[j][1] < pair[0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    maxPair = Math.max(maxPair, dp[i]);
                }
            }
        }
        return maxPair;
    }

    /**
     * https://leetcode-cn.com/problems/maximum-length-of-pair-chain/comments/100227
     * 将pair看成区间，问题转化为：删除某些区间，使得剩余的不相交区间最多。是不是很熟悉？435题。
     * AC
     * @param pairs
     * @return
     */
    public int findLongestChain_interval(int[][] pairs) {
        if (pairs.length == 0) return 0;
        int ans = 1;
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        int tail = pairs[0][1];
        for (int i = 1; i < pairs.length; i++) {
            if(pairs[i][0] > tail){// 不相交
                ans++;
                tail = pairs[i][1];
            }
        }
        return ans;
    }


    public int findLongestChain_re(int[][] pairs) {

        if (pairs.length == 0) return 0;
        int[] pair = pairs[0];
        return 1 + process(pairs, pair[0], pair[1], 1);
    }

    private int process(int[][] pairs, int start, int end, int index) {
        if (index == pairs.length) return 0;

        if (pairs[index][0] > end) {
            return 1 + process(pairs, start, pairs[index][1], ++index);
        } else if (pairs[index][1] < start) {
            return 1 + process(pairs, pairs[index][0], end, ++index);
        }

        if (index == 1) {
            return Math.max(process(pairs, start, end, index + 1),
                    process(pairs, pairs[index][0], pairs[index][1], index + 1));
        }

        return process(pairs, start, end, ++index);
    }


    class Pair {
        int start;
        int end;
        int num;

        public Pair(int start, int end, int num) {
            this.start = start;
            this.end = end;
            this.num = num;
        }
    }

    /**
     * 贪心，每次选择最长的链追加
     * @param pairs
     * @return
     */
    public int findLongestChain1(int[][] pairs) {

        if (pairs.length == 0) return 0;
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int[] pair0 = pairs[0];
        List<Pair> list = new ArrayList<>();
        list.add(new Pair(pair0[0], pair0[1], 1));

        int max = 1;
        for (int i = 1; i < pairs.length; i++) {
            int[] pair = pairs[i];

            Collections.sort(list, new Comparator<Pair>() {
                @Override
                public int compare(Pair o1, Pair o2) {
                    return o2.num - o1.num;
                }
            });
            boolean added = false;
            for (Pair p : list) {
                if (pair[0] > p.end) {
                    p.end = pair[1];
                    p.num += 1;
                    max = Math.max(max, p.num);
                    added = true;
                    break;
                } else if (pair[1] < p.start) {
                    p.start = pair[0];
                    p.num += 1;
                    max = Math.max(max, p.num);
                    added = true;
                    break;
                }
            }
            if (added == false) {
                list.add(new Pair(pair[0], pair[1], 1));
            }
        }
        return max;
    }

    public static void main(String[] args) {
        MaximumLengthOfPairChain_646 pairChain646 = new MaximumLengthOfPairChain_646();

        System.out.println(pairChain646.findLongestChain_re(new int[][]{new int[]{3, 4}, new int[]{2, 3}, new int[]{1, 2}}));
        System.out.println(pairChain646.findLongestChain_re(new int[][]{new int[]{3, 4}, new int[]{2, 4}, new int[]{2, 3}, new int[]{1, 2}}));
        System.out.println(pairChain646.findLongestChain_re(new int[][]{new int[]{1, 100}, new int[]{2, 4}, new int[]{2, 3}, new int[]{1, 2}}));
        System.out.println(pairChain646.findLongestChain_re(new int[][]{new int[]{1, 100}, new int[]{3, 4}, new int[]{2, 3}, new int[]{1, 2}}));
        System.out.println(pairChain646.findLongestChain1(new int[][]{new int[]{1, 100}, new int[]{2, 99}, new int[]{3, 4}, new int[]{1, 2}}));
    }
}
