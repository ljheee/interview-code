package leetcode.dp;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 */
public class BestTimeToBuyAndSellStockIII_123 {


    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        //只交易一次，能获取的最大收益
        int[] doneOnceMax = new int[prices.length];
        doneOnceMax[0] = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(prices[i], min);
            doneOnceMax[i] = Math.max(prices[i] - min, doneOnceMax[i - 1]);
        }


        // 0~i完成一次交易，且再买入一笔，最大值
        int[] doneOnceBuyOnceMax = new int[prices.length];
        doneOnceBuyOnceMax[0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            doneOnceBuyOnceMax[i] = Math.max(doneOnceBuyOnceMax[i - 1], doneOnceMax[i] - prices[i]);
        }


        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans = Math.max(doneOnceBuyOnceMax[i] + prices[i], ans);
        }
        return ans;
    }



}