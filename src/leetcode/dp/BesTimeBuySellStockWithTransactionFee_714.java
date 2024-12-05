package leetcode.dp;

import leetcode.ArrayGenerator;

import java.util.Arrays;

/**
 * Created by lijianhua04 on 2020/5/23.
 */
public class BesTimeBuySellStockWithTransactionFee_714 {


    /**
     * 第i天最大收益dp[i]，第j天最大收益dp[j]，
     * 第j天最大收益:第i天的收益，加上i~j能得到的收益
     * <p>
     * 超时：
     * 需要O(n)的时间复杂度
     *
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit0(int[] prices, int fee) {

        if (prices.length <= 1) return 0;

        // dp[i] 表示：第i天得到的最大收益
        int[] dp = new int[prices.length + 1];
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = Math.max(0, prices[1] - prices[0] - fee);

        for (int i = 3; i < dp.length; i++) {
            int curMax = 0;
            for (int j = 0; j < i - 1; j++) {
                curMax = Math.max(curMax, dp[j] + prices[i - 1] - prices[j] - fee);
            }
            dp[i] = Math.max(dp[i - 1], curMax);
        }
        return dp[prices.length];
    }


    /**
     * 要达到最大收益，“见好就收”
     * 所有递增段，都可能得到收益；
     * <p>
     * “见好就收”失败的case： new int[]{1, 3, 7, 5, 10, 3}, 3));//6
     * <p>
     * i位置能得到的最大收益：left的位置 or 上一次见好就收的位置
     *
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit1(int[] prices, int fee) {

        if (prices.length <= 1) return 0;

        int ans = 0;
        int left = 0;
        int right = 1;
        for (; right < prices.length; right++) {
            if (prices[right] >= prices[right - 1]) {
                if (right == prices.length - 1) ans += Math.max(0, prices[right] - prices[left] - fee);
                continue;
            } else {
                if (prices[right - 1] - prices[left] - fee > 0) {
                    ans += prices[right - 1] - prices[left] - fee;
                    left = right;
                }
            }
        }
        return ans;
    }

    public int maxProfit2(int[] prices, int fee) {

        if (prices.length <= 1) return 0;

        int ans = 0;
        int left = 0;
        int right = 1;
        int preRangeMin = prices[0];
        int preRangeMax = prices[0];
        int curRangeMin = prices[0];
        for (; right < prices.length; right++) {
            curRangeMin = Math.min(curRangeMin, prices[right - 1]);

            if (prices[right] >= prices[right - 1]) {
                if (right == prices.length - 1) ans += Math.max(0, prices[right] - prices[left] - fee);
                continue;
            } else {
                // 开头第一个数是高峰的情况，类似[10,1,3,7],要让left前进
                if (right - 1 == left) {
                    left = right;
                    continue;
                }
                if (prices[right - 1] - prices[left] - fee > 0) {
                    if (prices[right - 1] < preRangeMax){
                        ans += prices[right - 1] - prices[left] - fee;
                    }else {
                        ans += Math.max(prices[right - 1] - prices[left] - fee, prices[right - 1] - preRangeMin - fee);
                    }

                    preRangeMax = prices[right - 1];
                    left = right;
                    preRangeMin = curRangeMin;
                    curRangeMin = prices[left];
                } else {
                    if (prices[right] < prices[left]) {
                        preRangeMax = prices[right - 1];
                        left = right;
                        preRangeMin = curRangeMin;
                        curRangeMin = prices[left];
                    }
                }
            }
        }
        return ans;
    }

    public int maxProfit(int[] prices, int fee) {

        if (prices.length <= 1) return 0;

        int firstGood = 0;
        int ans = 0;
        int left = 0;
        int right = 1;
        int firstMin = Math.min(prices[0], prices[1]);
        for (; right < prices.length; right++) {
            firstMin = Math.min(prices[right - 1], firstMin);

            if (prices[right] >= prices[right - 1]) {
                if (right == prices.length - 1) ans += Math.max(0, prices[right] - prices[left] - fee);
                continue;
            } else {
                // 开头第一个数是高峰的情况，类似[10,1,3,7],要让left前进
                if (right - 1 == left) {
                    left = right;
                    firstGood = left;
                    continue;
                }
                if (prices[right - 1] - prices[left] - fee > 0) {
                    ans += prices[right - 1] - prices[left] - fee;
                    ans = Math.max(ans, prices[right - 1] - prices[firstGood] - fee);
                    left = right;
                } else {

                    ans = Math.max(ans, prices[right - 1] - firstMin - fee);
                    if (prices[right] < prices[left]) {
                        left = right;
                    }
                }
            }

        }
        return ans;
    }

    public static void main(String[] args) {
        BesTimeBuySellStockWithTransactionFee_714 transactionFee714 = new BesTimeBuySellStockWithTransactionFee_714();

//        System.out.println(transactionFee714.maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));//8
//        System.out.println(transactionFee714.maxProfit(new int[]{1, 3, 7, 5, 10, 3}, 3));//6
//        System.out.println(transactionFee714.maxProfit(new int[]{1}, 1));//0
//        System.out.println(transactionFee714.maxProfit(new int[]{1, 3}, 1));//1
//        System.out.println(transactionFee714.maxProfit(new int[]{5, 4, 3, 2, 1}, 1));//0
//        System.out.println(transactionFee714.maxProfit(new int[]{1, 2, 1, 5, 3, 5, 5, 4, 1, 5}, 0));//11
//        System.out.println(transactionFee714.maxProfit(new int[]{10, 5, 2, 5, 10, 11, 10, 13, 1, 7}, 7));//4
//        System.out.println(transactionFee714.maxProfit(new int[]{7, 9, 3, 3, 5, 11, 8, 14, 5, 5}, 7));//4
//        System.out.println(transactionFee714.maxProfit(new int[]{1, 12, 3, 4, 2, 9, 11, 4, 5, 4}, 7));//6
//        System.out.println(transactionFee714.maxProfit(new int[]{2, 9, 13, 5, 5, 7, 1, 11, 1, 1, 14, 8, 4, 12, 11, 1, 2, 11, 6, 12, 9, 14, 10, 8, 12}, 6));//25
//        System.out.println(transactionFee714.maxProfit(new int[]{1, 2, 11, 6, 12, 9, 14}, 6));//7
        System.out.println(transactionFee714.maxProfit2(new int[]{2, 9, 13, 5, 5, 7, 1, 11, 1, 1, 14, 8, 4, 12, 11, 1, 2, 11, 6, 12, 9, 14}, 6));//25
        System.out.println(transactionFee714.maxProfit0(new int[]{2, 9, 13, 5, 5, 7, 1, 11, 1, 1, 14, 8, 4, 12, 11, 1, 2, 11, 6, 12, 9, 14}, 6));//25


//        int[] array = ArrayGenerator.generateRandomArray(25, 1, 15);
//        System.out.println(Arrays.toString(array));
//        System.out.println(transactionFee714.maxProfit0(array, 6));
//        System.out.println(transactionFee714.maxProfit(array, 6));

    }
}