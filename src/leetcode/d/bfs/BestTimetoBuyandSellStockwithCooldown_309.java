package leetcode.d.bfs;

import leetcode.ArrayGenerator;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/submissions/
 * Created by lijianhua04 on 2020/8/23.
 */
public class BestTimetoBuyandSellStockwithCooldown_309 {


    public int maxProfit(int[] prices) {
        if (prices.length <= 1) return 0;

        return process(prices, 0, null, 0);
    }

    /**
     * 超时
     *
     * @param prices
     * @param i
     * @param buy
     * @param profit 到达i之前的收益
     * @return
     */
    private int process(int[] prices, int i, Integer buy, int profit) {
        if (i > prices.length - 1) return profit;

        if (i == 0 || buy == null) buy = prices[i++];

        while (i < prices.length - 1 && (prices[i] == buy || prices[i] < buy && prices[i - 1] == buy)) {
            buy = prices[i++];
        }

        if (i > prices.length - 1) return profit;

        if (prices[i] > buy) {
            return Math.max(process(prices, i + 2, null, profit + prices[i] - buy),
                    process(prices, i + 1, buy, profit));
        } else {
            //2,3,4,0
            return Math.max(process(prices, i + 1, null, profit + prices[i - 1] - buy),
                    process(prices, i + 1, prices[i], profit));
        }
    }


    public int maxProfit_dp(int[] prices) {
        int sell = 0, prev_sell = 0, buy = Integer.MIN_VALUE, prev_buy;
        for (int price : prices) {
            prev_buy = buy;
            buy = Math.max(prev_sell - price, prev_buy);
            prev_sell = sell;
            sell = Math.max(prev_buy + price, prev_sell);
        }
        return sell;
    }



    public static void main(String[] args) {
        BestTimetoBuyandSellStockwithCooldown_309 buyandSellStockwithCooldown309 = new BestTimetoBuyandSellStockwithCooldown_309();
        System.out.println(buyandSellStockwithCooldown309.maxProfit(new int[]{1, 2, 3, 0, 2}));
        System.out.println(buyandSellStockwithCooldown309.maxProfit(new int[]{5, 4, 3, 4, 1}));
        System.out.println(buyandSellStockwithCooldown309.maxProfit(new int[]{7, 7, 6, 7, 6}));
        System.out.println(buyandSellStockwithCooldown309.maxProfit(new int[]{2, 1, 0, 3, 4}));


        for (int i = 0; i < 600; i++) {

            int[] arr = ArrayGenerator.generateRandomArray(5, 0, 110);
            if (buyandSellStockwithCooldown309.maxProfit_dp(arr) != buyandSellStockwithCooldown309.maxProfit(arr)) {
                System.out.println(Arrays.toString(arr) + "=" + buyandSellStockwithCooldown309.maxProfit(arr));
            }

        }
    }
}
