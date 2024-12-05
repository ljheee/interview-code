package atcoder;

/**
 * stones[]存储了每块石头的高度
 * 青蛙跳过石头，消耗的能量为 两块石头的高度差；
 * 每次只能跳1步或2步
 * https://atcoder.jp/contests/dp/tasks/dp_a
 */
public class Frog1 {


    public static int minCost(int[] stones) {

        if (stones.length == 0 || stones.length == 1) return 0;
        int[] dp = new int[stones.length];

        dp[0] = 0;
        dp[1] = Math.abs(stones[0] - stones[1]);
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.min(dp[i - 1] + Math.abs(stones[i] - stones[i - 1]), dp[i - 2] + Math.abs(stones[i] - stones[i - 2]));
        }
        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(minCost(new int[]{10, 30, 40, 20}));
        System.out.println(minCost(new int[]{10, 10}));
        System.out.println(minCost(new int[]{30, 10, 60, 10, 60, 50}));//40
    }
}
