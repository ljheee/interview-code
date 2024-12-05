package leetcode.medium;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/ugly-number-ii/
 */
public class UglyNumberII_264 {

    public int nthUglyNumber(int n) {
        if (n <= 6) {
            return n;
        }
        int[] ugly = new int[n + 1];
        ugly[1] = 1;

        int i2 = 1;// 乘2指针，位置在1
        int i3 = 1;// 乘3指针，位置在1
        int i5 = 1;// 乘5指针，位置在1

        for (int i = 2; i < ugly.length; i++) {
            int a = ugly[i2] * 2;
            int b = ugly[i3] * 3;
            int c = ugly[i5] * 5;

            ugly[i] = Math.min(Math.min(a, b), c);

            if (ugly[i] == ugly[i2] * 2) i2++;
            if (ugly[i] == ugly[i3] * 3) i3++;
            if (ugly[i] == ugly[i5] * 5) i5++;

        }
        return ugly[n];
    }
}
