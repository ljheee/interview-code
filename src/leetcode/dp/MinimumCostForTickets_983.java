package leetcode.dp;

import leetcode.ArrayGenerator;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by lijianhua04 on 2020/8/12.
 */
public class MinimumCostForTickets_983 {


    public static int mincostTickets0(int[] days, int[] costs) {

        if (days.length == 0) return 0;

        // 第i天，最小花费
        int[] dp = new int[days[days.length - 1] + 1];
        // 第i天，最小花费时，能到达的最后是哪天
        int[] deadlineDay = new int[days[days.length - 1] + 1];
        int[] daysInternal = {1, 7, 30};

        for (int i = 1; i < dp.length; i++) {
            // 还没超过上次花费的截止日期，就复用
            if (deadlineDay[i - 1] > i) {
                dp[i] = dp[i - 1];
                deadlineDay[i] = deadlineDay[i - 1];
                continue;
            }
            int minCost = Integer.MAX_VALUE;
            for (int j = 0; j < costs.length; j++) {
                int curCost = (i - daysInternal[j] >= 0 ? dp[i - daysInternal[j]] : 0) + costs[j];
                if (curCost < minCost) {
                    minCost = curCost;
                    deadlineDay[i] = i - daysInternal[j] >= 0 ? i : daysInternal[j];
                }
            }
            dp[i] = minCost;
        }

        return dp[days[days.length - 1]];
    }

    public static int mincostTickets1(int[] days, int[] costs) {

        if (days.length == 0) return 0;

        // 第i天，最小花费
        int[] dp = new int[days[days.length - 1] + 1];
        // 第i天，最小花费时，能到达的最后是哪天
        int[] deadlineDay = new int[days[days.length - 1] + 1];
        int[] daysInternal = {1, 7, 30};

        for (int i = 1; i < dp.length; i++) {
            // 还没超过上次花费的截止日期，就复用
            if (deadlineDay[i - 1] > i) {
                dp[i] = dp[i - 1];
                deadlineDay[i] = deadlineDay[i - 1];
                continue;
            }
            int minCost = Integer.MAX_VALUE;
            for (int j = 0; j < costs.length; j++) {
                //int curCost = (i - daysInternal[j] >= 0 ? dp[i - daysInternal[j]] : 0) + costs[j];
                int curCost = dp[days[i - 1]] + costs[j];
                if (curCost < minCost) {
                    minCost = curCost;
                    deadlineDay[i] = i - daysInternal[j] >= 0 ? i : daysInternal[j];
                }
            }
            dp[i] = minCost;
        }

        return dp[days[days.length - 1]];
    }

    //AC
    public static int mincostTickets(int[] days, int[] costs) {
        if (days.length == 0) return 0;

        // 到达位置i，最小花费: min cost for reaching position i
        int[] dp = new int[days.length];
        dp[0] = Arrays.stream(costs).min().getAsInt();

        for (int i = 1; i < dp.length; i++) {

            dp[i] = dp[i - 1] + costs[0];

            int idx = nearestIndex(days, days[i] - 7);
            dp[i] = Math.min(dp[i], (idx < 0 ? 0 : dp[idx]) + costs[1]);
            idx = nearestIndex(days, days[i] - 30);
            dp[i] = Math.min(dp[i], (idx < 0 ? 0 : dp[idx]) + costs[2]);
        }

        return dp[days.length - 1];
    }

    // <=target的左边界
    private static int nearestIndex(int[] arr, int target) {

        if (target <= 0) return -1;
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        if (left == 0 && target < arr[0]) return -1;
        return left;
    }


    public static int mincostTickets2(int[] days, int[] costs) {

        if (days.length == 0) return 0;

        int lastDay = days[days.length - 1];
        Integer[] momo = new Integer[lastDay + 1];

        HashSet<Integer> set = new HashSet<>();
        for (int day : days) {
            set.add(day);
        }

        return dp(lastDay, momo, set, costs);
    }

    private static int dp(int i, Integer[] momo, HashSet<Integer> set, int[] costs) {
        if (i <= 0) return 0;
        if (momo[i] != null) {
            return momo[i];
        }
        int ans = 0;
        if (set.contains(i)) {
            ans = dp(i - 1, momo, set, costs) + costs[0];
            ans = Math.min(ans, dp(i - 7, momo, set, costs) + costs[1]);
            ans = Math.min(ans, dp(i - 30, momo, set, costs) + costs[2]);
        } else {
            ans = dp(i - 1, momo, set, costs);
        }
        momo[i] = ans;
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(mincostTickets(new int[]{2, 4, 7, 11, 14, 20, 24, 25}, new int[]{4, 9, 27}));//26
        System.out.println(mincostTickets(new int[]{5, 6, 10, 11, 15, 16, 18, 21}, new int[]{3, 5, 18}));//10
        System.out.println(mincostTickets(new int[]{1, 4, 6, 7, 8, 20}, new int[]{2, 7, 15}));//11
        System.out.println(mincostTickets(new int[]{1, 4, 6, 7, 8, 20, 29}, new int[]{2, 7, 15}));//13
        System.out.println(mincostTickets(new int[]{1, 4, 6, 7, 8}, new int[]{2, 7, 15}));//9
        System.out.println(mincostTickets(new int[]{1, 3, 7}, new int[]{1, 4, 20}));//3
        System.out.println(mincostTickets(new int[]{1, 2, 3, 4, 6, 8, 9, 10, 13, 14, 16, 17, 19, 21, 24, 26, 27, 28, 29}, new int[]{3, 14, 50}));//3


        for (int i = 0; i < 1000; i++) {
            int[] days = ArrayGenerator.distinctSortedArray(8, 1, 30);
            int[] cost = ArrayGenerator.distinctSortedArray(3, 1, 30);

            if (mincostTickets(days, cost) != mincostTickets2(days, cost)) {
                System.out.println(String.format("param:%s,param:%s,result1:%d,result2:%d",
                        Arrays.toString(days),
                        Arrays.toString(cost),
                        mincostTickets(days, cost),
                        mincostTickets2(days, cost)));
            }
        }
    }

}
