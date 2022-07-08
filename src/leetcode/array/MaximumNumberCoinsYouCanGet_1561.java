package leetcode.array;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximum-number-of-coins-you-can-get/submissions/
 */
public class MaximumNumberCoinsYouCanGet_1561 {


    public static int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int group = piles.length / 3;
        int i = group;
        int ans = 0;
        while (i < piles.length - 1) {
            ans += piles[i];
            i += 2;
        }
        return ans;
    }




    public static void main(String[] args) {
        System.out.println(maxCoins(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6}));


    }

}
