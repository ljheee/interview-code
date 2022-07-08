package num;

/**
 * 编程题：输入一个正整数，若该数能用几个连续正整数之和表示，则输出所有可能的正整数序列。
 * 例如，输入7，输出3 4
 *      输入9，输出2 3 4；4 5
 * <p>
 * https://www.nowcoder.com/questionTerminal/07c8309d45b44f269a2dd615d079917c
 */
public class Num {


    public static void main(String[] args) {


        int n = 9;
        for (int m = (int) Math.sqrt(2 * n); m >= 2; m--) {
            double fx = (double) (2 * n - m * (m - 1)) / (2 * m);
            int dx = (int) fx;

            if (dx == fx) {
                for (int i = dx; i < dx + m; i++) {
                    System.out.print(i + " ");
                }
                System.out.println();
            }
        }
    }
}
