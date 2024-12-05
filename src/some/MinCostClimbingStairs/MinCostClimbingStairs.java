package some.MinCostClimbingStairs;

import static java.lang.Math.min;

/**
 * 使用最小花费爬楼梯
 * <p>
 * dp[i]=min(dp[i-1]+cost[i-1] , dp[i-2]+cost[i-2])
 * 阶梯终点在 数组length+1处
 */
public class MinCostClimbingStairs {



    public static void main(String[] args) {
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        MinCostClimbingStairs climbingStairs = new MinCostClimbingStairs();
        System.out.println(climbingStairs.fun(cost.length , cost));
    }


    /**
     * 斐波那契
     * @param n
     * @return
     */
    int fibonacci(int n) {
        int f0 = 0;
        int f1 = 1;
        if (n > 2) {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
        return n;
    }

    int fun(int n, int[] cost) {
        if (n < 2) {
            return 0;
        }else {
            return Math.min(fun(n - 1, cost) + cost[n - 1], fun(n - 2, cost) + cost[n - 2]);
        }
    }


    /**
     * 最优解 O(n)
     * @param cost
     * @return
     */
    public static int solution(int[] cost){
        int jump0 = 0;
        int jump1 = 0;
        int minCost = 0;
        //楼梯终点所在位置，是cost数组末位置的后一个，即cost.length + 1
        int size = cost.length + 1;
        for(int i = 2; i < size; i++){
            minCost = Math.min(jump0 + cost[i-2], jump1 + cost[i-1]);
            jump0 = jump1;
            jump1 = minCost;
        }
        return jump1;
    }


}
