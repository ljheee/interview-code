package leetcode.math;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static jdk.nashorn.internal.objects.NativeArray.reverse;

/**
 * Created by lijianhua04 on 2020/3/11.
 */
public class ValidPerfectSquare_367 {


    /**
     * 求num的平方根
     * https://blog.csdn.net/ycf74514/article/details/48996383
     * <p>
     * 二分法，和牛顿迭代
     * https://leetcode-cn.com/problems/valid-perfect-square/solution/you-xiao-de-wan-quan-ping-fang-shu-by-leetcode/
     *
     * @param num
     * @return
     */
    double sqrtNewton(int num) {
        double diff = 0.00000000000001d;
        double x = num / 2D;

        double pow = 0;
        while ((pow = x * x) > num && pow - num > diff) {
            x = (x + num / x) / 2;
        }
        return x;
    }

//    public static void main(String[] args) {
//        System.out.println(new ValidPerfectSquare_367().sqrtNewton(25));
//    }

    public int mySqrt0(int n) {
        if (n < 2) {
            return n;
        }
        long x = n / 2;
        while (x * x > n) {
            x = (x + n / x) / 2;
        }

        return (int) x;
    }

    public int mySqrt(int n) {

        if (n < 2) {
            return n;
        }
        int left = 2;
        int right = n / 2;
        while (left <= right) {
            int x = (left + right) / 2;
            int gess = x * x;

            if (gess == n) {
                return x;
            }
            if (gess > n) {
                right--;
            } else {
                left++;
            }
        }
        return 0;
    }

    /**
     * https://leetcode-cn.com/problems/valid-perfect-square/solution/zhi-xing-yong-shi-0-ms-zai-suo-you-c-ti-jiao-zh-44/
     * 1 4=1+3 9=1+3+5 16=1+3+5+7以此类推，模仿它可以使用一个while循环，不断减去一个从1开始不断增大的奇数，若最终减成了0，说明是完全平方数，否则，不是。
     * <p>
     * 为什么 平方根会和 1 3 5 7等差数列求和公式有关联？
     * <p>
     * 设末项x
     * 则项数(x+1)/2
     * 求和公式：(首项+末项)*项数/2
     * (1+x)*(x+1)/4=100
     * x=19
     * <p>
     * 试着数学证明下: 假设n>=2 则: (n+1)^2 - n^2=(n+1-n)(n+n+1) = 2n+1
     */
    public boolean isPerfectSquare(int num) {

        int even = 1;
        while (num > 0) {
            num -= even;
            even += 2;
        }
        return num == 0;
    }


}
