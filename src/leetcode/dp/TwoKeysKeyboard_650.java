package leetcode.dp;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/2-keys-keyboard/
 * 只有一个A
 * 有两种操作：复制全部、粘贴
 * 要得到n个A，最少需要多少次操作。
 * ------------------------
 * 找规律，n<=5时，return 5;
 * int[] dp = new int[n + 1];
 * dp[1] = 0;
 * dp[2] = 2;
 * dp[3] = 3;
 * dp[4] = 4;
 * dp[5] = 5;
 * <p>
 * 质数，只能单个复制；
 * 其他数，可以通过分解成a.b（正整数n，分解成两个数相乘，和最小），进行递归；
 */
public class TwoKeysKeyboard_650 {


    /**
     * 递归
     * AC
     *
     * @param n
     * @return
     */
    public int minSteps(int n) {
        if (n == 1) return 0;
        if (n <= 5) return n;

        // 前1000 质数表
        int[] arr = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997};
        if (Arrays.binarySearch(arr, n) > 0) return n;

        int[] two = devideTwoNum(n);
        if (two[0] == two[1]) return 2 * minSteps(two[0]);
        return minSteps(two[0]) + minSteps(two[1]);
    }

    public static int[] devideTwoNum(int n) {
        int sqrt = (int) Math.sqrt(n);
        int a = 1, b = n;

        for (int i = sqrt; i >= 1; i--) {
            if (n % i == 0) {
                a = i;
                b = n / i;
                break;
            }
        }
        return new int[]{a, b};
    }


    /**
     * // n分解成a*b，其中a,b一定小于n，已经填过
     * dp[n] = dp[a]+dp[b];
     *
     * @param n
     * @return
     */
    public int minSteps_dp(int n) {
        if (n == 1) return 0;
        if (n <= 5) return n;

        // 前1000 质数表
        int[] arr = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997};
        if (Arrays.binarySearch(arr, n) > 0) return n;

        int dp[] = new int[n + 1];
        dp[1] = 0;
        dp[2] = 2;
        dp[3] = 3;
        dp[4] = 4;
        dp[5] = 5;
        for (int i = 6; i <= n; i++) {
            if (Arrays.binarySearch(arr, i) > 0) dp[i] = i;
            int[] two = devideTwoNum(i);
            dp[i] = dp[two[0]] + dp[two[1]];
        }
        return dp[n];
    }




    public static void main(String[] args) {
        TwoKeysKeyboard_650 keysKeyboard650 = new TwoKeysKeyboard_650();

        System.out.println(keysKeyboard650.minSteps(188));
        System.out.println(keysKeyboard650.minSteps_dp(188));

        for (int i = 1; i < 1001; i++) {
            if (keysKeyboard650.minSteps(i) != keysKeyboard650.minSteps_dp(i)) {
                System.out.println("oops" + i);
            }
        }



    }
}
