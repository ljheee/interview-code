package leetcode.dp;

import leetcode.ArrayGenerator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 */
public class BestTimeToBuyAndSellStock_121 {


    /**
     * 选择最好的股票交易时间， 低买高卖
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
     * 遍历数组时，可以记录前面出现的最小值，用当前值减去最小值，就是当前的maxProfit；
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {

        int minPrice = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
            if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }

    // dp解法
    public int maxProfit_dp(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        // dp[i]表示：第i天卖出的最大收益
        int[] dp = new int[prices.length];
        dp[0] = 0;
        int minPrice = prices[0];
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) minPrice = prices[i];

            dp[i] = prices[i] - minPrice;
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    /**
     * 股票交易次数，不受限制，低买高卖。
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
     *
     * @param prices
     * @return
     */
    public int maxProfitII(int[] prices) {

        int minPrice = Integer.MAX_VALUE;
        int curMax = 0;
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
                // 3 4 1,遇到更低的买点
                if (curMax > 0) {
                    max += curMax;
                    curMax = 0;
                }
            }
            if (prices[i] - minPrice > curMax) {
                curMax = prices[i] - minPrice;
            } else if (curMax > 0) {
                // 1 5 2, 开启新的买点
                max += curMax;
                curMax = 0;
                minPrice = prices[i];
            }

        }
        // 把最后一次的curMax加上
        max += curMax;
        return max;
    }

    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
     * maxProfit1(new int[]{1,3,7,5,10,3},3);// 直接10-1赚的更多
     * 需要用DP
     *
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {

        int income = 0;

        int minPrice = prices[0];
        int curMax = prices[0];

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                if (curMax - minPrice - fee > 0) {
                    income += (curMax - minPrice - fee);
                }
                curMax = prices[i];
                minPrice = prices[i];
            }

            if (prices[i] >= curMax) {
                curMax = prices[i];
            } else {
                if (curMax - minPrice - fee > 0) {
                    income += (curMax - minPrice - fee);
                    minPrice = prices[i];
                    curMax = prices[i];
                }
            }

        }
        if (curMax - minPrice - fee > 0) {
            income += (curMax - minPrice - fee);
        }
        return income;
    }



    public static void main(String[] args) {
        BestTimeToBuyAndSellStock_121 stock121 = new BestTimeToBuyAndSellStock_121();
        int[] arr = ArrayGenerator.generateRandomArray(11, 1, 40);
        System.out.println(stock121.maxProfit(arr) + "===" + stock121.maxProfit_dp(arr));


//        new BestTimeToBuyAndSellStock_121().maxProfitII(new int[]{7, 1, 5, 3, 6, 4});
//        new BestTimeToBuyAndSellStock_121().maxProfitII(new int[]{1, 2, 3, 4, 5});
//        new BestTimeToBuyAndSellStock_121().maxProfitII(new int[]{6, 1, 3, 2, 4, 7});
//        new BestTimeToBuyAndSellStock_121().maxProfit1(new int[]{1,3,2,8,4,9},2);
//        new BestTimeToBuyAndSellStock_121().maxProfit(new int[]{1, 3, 7, 5, 10, 3}, 3);// 直接10-1赚的更多
    }
}