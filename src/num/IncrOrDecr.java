package num;

/**
 * 判断 数字n 是依次递增还是依次递减、还是乱序
 * <p>
 * 112233 为递增
 * 554400 为递减
 *
 * 思想：从后往前遍历，将当前位与前一位比较，来判断递增或递减；如果最后递增和递减的标记都是true，说明又有增又有减，是乱序；
 */
public class IncrOrDecr {

    public static void main(String args[]) {
        int x = 123444333;
        boolean sign1 = false;//递增
        boolean sign2 = false;//递减
        while (x != 0) {
            int val = x % 10;
            x = x / 10;
            if (x != 0 && val > x % 10) {
                sign1 = true;
            }
            if (x != 0 && val < x % 10) {
                sign2 = true;
            }
        }
        System.out.println(x);

        System.out.println(sign1);
        System.out.println(sign2);
    }
}
