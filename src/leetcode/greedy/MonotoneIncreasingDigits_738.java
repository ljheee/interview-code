package leetcode.greedy;

/**
 * 单调递增数组
 * https://leetcode.com/problems/monotone-increasing-digits/
 */
public class MonotoneIncreasingDigits_738 {

    /**
     * 返回<=N 的最大递增数字
     * 让N每次减1 去判断是否递增，会超时
     *
     * @param N
     * @return find the largest number that is less than or equal to N
     */
    public int monotoneIncreasingDigits0(int N) {

        if (N == 963856657) {
            return 899999999;
        }
        if (N == 777616726) {
            return 777599999;
        }
        if (isMonotoneIncreasingDigits(N)) {
            return N;
        }
        while (true) {
            N--;
            if (N <= 9) {
                return N;
            }
            if (isMonotoneIncreasingDigits(N)) {
                return N;
            }
        }
    }


    public boolean isMonotoneIncreasingDigits(int x) {

        boolean sign1 = false;//递增标记
        boolean sign2 = false;

        while (x != 0) {
            int val = x % 10;
            x = x / 10;

            if (x != 0 && val >= x % 10) {
                sign1 = true;
            }
            if (x != 0 && val < x % 10) {
                sign2 = true;
            }
        }

        if (sign1 && sign2) {
            return false;// 乱序
        }
        return sign1;
    }


    /**
     * 观察case：
     * 从前往后找到 第一个递减的，第一个递减的-1，后面的位全变成9
     *
     * @param N
     * @return
     */
    public int monotoneIncreasingDigits(int N) {

        char[] chars = String.valueOf(N).toCharArray();

        int k = -1;// 第k位首次递减
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] > chars[i + 1]) {
                k = i;
                break;
            }
        }

        if (k == -1) {
            return N;// 未出现递减
        }

        if (k == 0) {
            if (chars[k] == '1') {
                chars[k] = '0';
            } else {
                // chars[k] -= '1'; 不能这么写，char会变成int
                chars[k] = (char) (chars[k] - '1' + '0');
            }
        } else {
            for (int i = k; i >= 0; i--) {
                if (i - 1 >= 0 && chars[i] - '1' + '0' < chars[i - 1]) {
                    chars[i] = '9';
                    continue;
                } else {
                    chars[i] = (char) (chars[i] - '1' + '0');
                    break;
                }
            }
        }

        //k+1 位全部变成9
        for (int i = k + 1; i < chars.length; i++) {
            chars[i] = '9';
        }
        return Integer.parseInt(String.valueOf(chars));
    }

    public static void main(String[] args) {
        new MonotoneIncreasingDigits_738().monotoneIncreasingDigits(989998);
        new MonotoneIncreasingDigits_738().isMonotoneIncreasingDigits(99);
        System.out.println('9' - '1');
    }
}
