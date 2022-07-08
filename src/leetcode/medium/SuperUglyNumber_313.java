package leetcode.medium;

import java.util.Arrays;

/**
 * 指定 质数列表，返回第n项丑数
 * https://leetcode.com/problems/super-ugly-number/
 * 和 UglyNumberII_264 思路一致
 */
public class SuperUglyNumber_313 {


    /**
     * Runtime: 13 ms, faster than 93.42% of Java online submissions for Super Ugly Number.
     * Memory Usage: 37.1 MB, less than 63.91% of Java online submissions for Super Ugly Number.
     *
     * @param n
     * @param primes
     * @return
     */
    public int nthSuperUglyNumber(int n, int[] primes) {

        if (n <= 1) return n;
        int[] arr = new int[n + 1];
        arr[1] = 1;


        int[] point = new int[primes.length];
        Arrays.fill(point, 1);

        for (int i = 2; i < arr.length; i++) {

            int min = arr[point[0]] * primes[0];
            for (int j = 1; j < point.length; j++) {
                min = Math.min(min, arr[point[j]] * primes[j]);
            }
            arr[i] = min;

            for (int j = 0; j < point.length; j++) {
                if (min == arr[point[j]] * primes[j]) point[j]++;
            }


        }
        return arr[n];
    }




}
