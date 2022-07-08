package leetcode.easy;

/**
 * 反转整数：需要考虑反转后溢出int
 * 正负情况：首位取余 已经带上了符号；
 * <p>
 * //正整数溢出情况例子：2147483647
 * //负整数溢出情况例子：-2147483648
 * Created by lijianhua04 on 2020/1/4.
 */
public class ReverseInteger_7 {

    public int reverse(int x) {
        if (x > -9 && x < 9) {
            return x;
        }
        long ans = 0;
        while (x != 0) {
            int last = x % 10;
            x /= 10;
            ans = ans * 10 + last;
        }
        if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) ans;
    }



    public static void main(String[] args) {
        int x = 1534236469;

        new ReverseInteger_7().reverse(-123);
    }

}
