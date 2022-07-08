package leetcode.ordinary;

/**
 * https://leetcode.com/problems/broken-calculator/
 */
public class BrokenCalculator_991 {

    public static int brokenCalc(int X, int Y) {

        if (X >= Y) return X-Y;
        if (X * 2 == Y || X - 1 == Y) return 1;
        int a = 1;
        if (Y % 2 == 0) {
            // Y/2 *2 =Y 这一步是确定的
            a += brokenCalc(X, Y / 2);
//            return Math.min(1 + brokenCalc(X, Y + 1), a);
            return a;
        }
        return 1 + brokenCalc(X, Y + 1);
    }


    public static void main(String[] args) {
        System.out.println(brokenCalc(2, 3));
        System.out.println(brokenCalc(5, 8));
        System.out.println(brokenCalc(3, 10));
        System.out.println(brokenCalc(3,11));
        System.out.println(brokenCalc(1024,1));
    }
}
