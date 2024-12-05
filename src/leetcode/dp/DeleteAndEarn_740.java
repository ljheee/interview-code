package leetcode.dp;

import leetcode.ArrayGenerator;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/delete-and-earn/submissions/
 * <p>
 * 相似题 https://leetcode.com/problems/house-robber/ 相似，相邻的不能取
 * 数值相邻的，不能同时取；
 * 如果取num，num有n个全会取走；则会得到num*n;
 */
public class DeleteAndEarn_740 {


    public int deleteAndEarn(int[] nums) {

        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        // key=num，value=num出现的总和
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            Integer value = map.getOrDefault(num, 0);
            map.put(num, value + num);
        }

        /**
         * 如果keySet 是[2,3,4] 则不能相邻取；
         * 如果keySet 是[3,5,7] 则可以相邻取；
         * 如何判断？
         */
        int maxElement = map.lastKey();
        return rob(maxElement + 1, map);
    }

    //0~nums 个数，不能相邻着取
    // i 对应的总和，从map取。
    public int rob(int nums, TreeMap<Integer, Integer> map) {

        // dp[i]:表示要偷窃i，能够得到的最大值
        int[] dp = new int[nums];
        dp[0] = 0;
        dp[1] = map.getOrDefault(1, 0);
        int max = 0;

        for (int i = 2; i < dp.length; i++) {
            dp[i] = max + map.getOrDefault(i, 0);
            max = Math.max(max, dp[i - 1]);
        }
        return Arrays.stream(dp).max().getAsInt();
    }


    public static void main(String[] args) {
        System.out.println(new DeleteAndEarn_740().deleteAndEarn(new int[]{3, 5, 7, 3, 5, 7}));//30
        System.out.println(new DeleteAndEarn_740().deleteAndEarn(new int[]{2, 2, 3, 3, 3, 4}));//9
        System.out.println(new DeleteAndEarn_740().deleteAndEarn(new int[]{}));//0
        System.out.println(new DeleteAndEarn_740().deleteAndEarn(new int[]{1, 1}));//2
        System.out.println(new DeleteAndEarn_740().deleteAndEarn(new int[]{1, 2}));//2


        int[] nums = ArrayGenerator.generateRandomArray(20, 1, 50);
        System.out.println(Arrays.toString(nums));
        System.out.println(new DeleteAndEarn_740().deleteAndEarn(nums));

    }

}

