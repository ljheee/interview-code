package leetcode.dp;

import leetcode.ArrayGenerator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * https://leetcode.com/problems/ones-and-zeroes/
 * 使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。
 */
public class OnesAndZeroes_474 {


    /**
     * 暴力法：按长度，每次先拼出最短的；
     * <p>
     * 不过的case：[ "1", "01", "00", "001", "11111", "01101", "100110", "0001111", "1101011", "11110001"]
     * 20
     * 10
     * strs[i] length短，可能不选。
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm_bf(String[] strs, int m, int n) {

        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });

        int ones[] = new int[strs.length];
        int zeros[] = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            for (char c : str.toCharArray()) {
                if (c == '1') {
                    ones[i]++;
                } else {
                    zeros[i]++;
                }
            }
        }

        int cnt = 0;
        int i = 0;
        while (i < strs.length && m + n >= strs[i].length()) {
            if (m >= zeros[i] && n >= ones[i]) {
                m -= zeros[i];
                n -= ones[i];
                cnt++;
            }
            i++;
        }

        return cnt;
    }

    /**
     * 背包问题
     * 背包里能装下 m 个 0 和 n 个 1 ，
     * 求出 能从strs中选出多少个物品装下。
     * <p>
     * 相似解法 https://leetcode-cn.com/problems/ones-and-zeroes/solution/dong-tai-gui-hua-zhuan-huan-wei-0-1-bei-bao-wen-ti/
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm_dp(String[] strs, int m, int n) {

        //先统计每个 str需要消耗的0、1数
        int ones[] = new int[strs.length];
        int zeros[] = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            for (char c : str.toCharArray()) {
                if (c == '1') {
                    ones[i]++;
                } else {
                    zeros[i]++;
                }
            }

        }

        // dp[i[mi][ni]代表：使用mi个0 ni个1 的背包容量，装到第i个物品时，装载的最大个数
        int[][][] dp = new int[strs.length + 1][m + 1][n + 1];

        for (int i = 1; i <= strs.length; i++) {
            for (int mi = 0; mi < m + 1; mi++) {
                for (int ni = 0; ni < n + 1; ni++) {
                    int idx = i - 1;// zeros、ones下标从0开始
                    if (zeros[idx] <= mi && ones[idx] <= ni) {
                        dp[i][mi][ni] = Math.max(dp[idx][mi][ni], dp[i - 1][mi - zeros[idx]][ni - ones[idx]] + 1);
                    } else {
                        dp[i][mi][ni] = dp[i - 1][mi][ni];
                    }
                }
            }
        }

        return dp[strs.length][m][n];
    }


    /**
     * 观察动态转移方程，我们发现dp[i][][] 只和dp[i-1][][] 有关，所以可以去掉第一维，只用一个二维数组保存上一次计算的结果
     * https://leetcode-cn.com/problems/ones-and-zeroes/solution/zi-ding-xiang-xia-ji-yi-sou-suo-zi-di-xiang-shang-/
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {

        // dp[i][j]代表：使用i个0 j个1，能拼凑的最大个数
        int[][] dp = new int[m + 1][n + 1];

        int ones[] = new int[strs.length];
        int zeros[] = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            for (char c : str.toCharArray()) {
                if (c == '1') {
                    ones[i]++;
                } else {
                    zeros[i]++;
                }
            }
            if (ones[i] == 1 && zeros[i] == 0) dp[0][1] = 1;
            if (ones[i] == 0 && zeros[i] == 1) dp[1][0] = 1;

        }

        return 0;
    }


    public static void main(String[] args) {

        OnesAndZeroes_474 onesAndZeroes474 = new OnesAndZeroes_474();

//        System.out.println(onesAndZeroes474.findMaxForm_bf(new String[]{"10", "0", "1"}, 1, 1));
//        System.out.println(onesAndZeroes474.findMaxForm_bf(new String[]{"10", "10", "11"}, 1, 3));


//        System.out.println(onesAndZeroes474.findMaxForm_dp(new String[]{"1", "01", "00", "001", "11111", "01101", "100110", "0001111", "1101011", "11110001"}, 20, 10));//expect 6


        String[] strs = new String[ThreadLocalRandom.current().nextInt(10, 15)];
        for (int i = 0; i < strs.length; i++) {

            int[] array = ArrayGenerator.generateBinaryArray(ThreadLocalRandom.current().nextInt(1, 10));
            strs[i] = Arrays.toString(array)
                    .replace("[", "")
                    .replace("]", "")
                    .replace(" ", "")
                    .replace(",", "");
        }

        System.out.println(onesAndZeroes474.findMaxForm_dp(strs, 20, 10));

        System.out.println(Arrays.toString(Arrays.stream(strs).map(e -> "\"" + e + "\"").toArray()));
        System.out.println(onesAndZeroes474.findMaxForm_dp(new String[]{"10", "0", "1"}, 1, 1));
    }
}
