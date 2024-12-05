package leetcode.dp;

import java.util.Arrays;

/**
 * 每次合并k个石头，成本是stones[i]之和；
 * 合并完成后最小的成本
 * https://leetcode.com/problems/minimum-cost-to-merge-stones/
 *
 */
public class MinimumCostMergeStones_1000 {

    /**
     * 贪心法，每次合并最小的k个，只能达到局部最优。
     * <p>
     * 不过case：
     * [3,2,4,1,3,2,4,1,3,2,4,1]
     * 2
     *
     * @param stones
     * @param K
     * @return
     */
    public int mergeStones(int[] stones, int K) {

        if (stones.length == K) return Arrays.stream(stones).sum();
        int minCost = Integer.MAX_VALUE;
        int idx = 0;
        for (int i = 0; i < stones.length; i++) {
            int mc = 0;
            if (i + K > stones.length) break;
            for (int j = 0; j < K; j++) {
                mc += stones[i + j];
            }
            if (mc < minCost) {
                minCost = mc;
                idx = i;
            }
        }

        if (minCost == Integer.MAX_VALUE) return -1;

        int[] newstones = new int[stones.length - K + 1];
        for (int i = 0; i < idx; i++) {
            newstones[i] = stones[i];
        }
        newstones[idx] = minCost;
        for (int i = idx + 1; i < newstones.length; i++) {
            newstones[i] = stones[i + K - 1];
        }
        System.out.println(minCost);
        System.out.println(Arrays.toString(newstones));

        int res = minCost + mergeStones(newstones, K);
        return res;
    }


    // TODO: 2020/5/17 dp


    public static void main(String[] args) {
        System.out.println(new MinimumCostMergeStones_1000().mergeStones(new int[]{5, 4, 1}, 2));
        System.out.println(new MinimumCostMergeStones_1000().mergeStones(new int[]{3, 2, 4, 1}, 2));
        System.out.println(new MinimumCostMergeStones_1000().mergeStones(new int[]{3, 2, 4, 1}, 2));


    }
}
