package leetcode.medium;

import java.util.Arrays;

/**
 * 返回n内质数的个数
 * https://leetcode.com/problems/count-primes/
 */
public class CountPrimes_204 {


    /**
     * Runtime: 13 ms, faster than 78.13% of Java online submissions for Count Primes.
     * Memory Usage: 37.6 MB, less than 53.32% of Java online submissions for Count Primes.
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {

        boolean[] mask = new boolean[n];
        Arrays.fill(mask, true);

        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                for (int j = i * i; j < n; j += i) {
                    mask[j] = false;
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < mask.length; i++) {
            if (mask[i]) cnt++;
        }
        return cnt;
    }

    private boolean isPrime(int m) {
        if (m <= 3) return true;
        for (int i = 2; i < Math.sqrt(m); i++) {
            if (m % i == 0) return false;
        }
        return true;
    }
}
