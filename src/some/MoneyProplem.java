package some;

import java.util.Arrays;

/**
 * Created by lijianhua04 on 2020/7/1.
 */
public class MoneyProplem {


    public static int minCost(int[] ability, int[] money) {
        return process(ability, money, 0, 0);
    }

    private static int process(int[] ability, int[] money, int hp, int index) {
        if (index == ability.length - 1) {
            return 0;
        }
        if (hp < ability[index]) {
            // 能力不足，必须贿赂
            return money[index] + process(ability, money, hp + ability[index], index + 1);
        } else {
            // 可选择贿赂
            return Math.min(money[index] + process(ability, money, hp + ability[index], index + 1),
                    process(ability, money, hp, index + 1));
        }
    }


    public static int minCost_dp(int[] ability, int[] money) {

        int n = ability.length;
        int sum = Arrays.stream(ability).sum();


        // i 就是 怪兽编号，j是当前的能力
        // dp[i][j] 通过i怪兽，能力为j 需要花的最少钱数
        int[][] dp = new int[n + 1][sum + 1];

        // dp[n][..]=0;
        for (int i = dp.length - 1; i >= 0; i--) {
            for (int j = 0; j <= sum; j++) {
                if (j + ability[i] > sum) {
                    continue;
                }

                if (j < ability[i]) {
                    dp[i][j] = money[i] + dp[i + 1][j + ability[i]];
                } else {
                    dp[i][j] = Math.min(money[i] + dp[i + 1][j + ability[i]],
                            dp[i + 1][j]);
                }
            }
        }
        return dp[0][0];
    }


    /**
     * curHP+bility[i] 能达到更远的地方，贿赂这样的怪兽。
     *
     * @param bility
     * @param money
     * @return
     */
    public int minMoney(int bility[], int money[]) {
        int curHP = bility[0];
        int cost = money[0];


        int i = 1;

        int next = curHP + bility[i];
        while (i < money.length) {

            next = Math.max(next, curHP + bility[i]);


        }


        return cost;
    }

}
