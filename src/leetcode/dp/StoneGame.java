package leetcode.dp;

import leetcode.ArrayGenerator;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/stone-game/submissions/
 * 石头游戏：先手必赢
 * Created by lijianhua04 on 2020/1/4.
 */
public class StoneGame {

    /**
     * 贪心解法
     * 两个玩家，每次都取最大的(让自己得分最大)
     * <p>
     * 存在的问题，贪心只能保证当前最优，不能提前感知“未来”；
     * 只关注了眼前，没关注“未来回报”
     * 比如 53454545，先手拿了5，第二次应该拿3；
     * <p>
     * <p>
     * 正确的做法：动态规划
     *
     * @param piles
     * @return
     */
    public boolean stoneGame_greedy(int[] piles) {
        int firstRoleScore = 0;
        int secondRoleScore = 0;

        int left = 0;
        int right = piles.length - 1;

        while (left < right) {
            if (piles[left] >= piles[right]) {
                firstRoleScore += piles[left++];
            } else {
                firstRoleScore += piles[right--];
            }

            if (piles[left] >= piles[right]) {
                secondRoleScore += piles[left++];
            } else {
                secondRoleScore += piles[right--];
            }
        }

        return firstRoleScore > secondRoleScore;
    }


    /**
     * 递归：case超时
     * @param piles
     * @return
     */
    public static boolean stoneGame_re(int[] piles) {

        int sum = Arrays.stream(piles).sum();

        int left = 0;
        int right = piles.length - 1;
        int myStone = select(piles, left, right, 0);
        return myStone > sum - myStone;
    }

    private static int select(int[] piles, int left, int right, int ans) {

        if (left == right || left > right) {
            return ans;
        }

        int ll = left;
        int rr = right;
        int newAns = ans;

        newAns += piles[ll];
        if (piles[ll + 1] > piles[rr]) {
            ll += 1;
        } else {
            rr--;
        }
        int a = select(piles, ll + 1, rr, newAns);

        ll = left;
        rr = right;
        newAns = ans;
        newAns += piles[rr];
        if (piles[ll] > piles[rr - 1]) {
            ll += 1;
        } else {
            rr--;
        }
        int b = select(piles, ll, rr - 1, newAns);

        return Math.max(a, b);
    }


    public static void main(String[] args) {
        System.out.println(stoneGame_re(new int[]{5, 3, 4, 5, 4, 5, 4, 5}));


        for (int i = 0; i < 1000000; i++) {

            int[] array = ArrayGenerator.generateRandomArray(4, 1, 100);
            if (Arrays.stream(array).sum()%2 ==0) {
                continue;
            }
            if(stoneGame_re(array) ==false){
                System.out.println(Arrays.toString(array));
            }

        }
    }


}
