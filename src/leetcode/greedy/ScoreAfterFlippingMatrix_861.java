package leetcode.greedy;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 翻转矩阵后的得分
 * 最大20*20的矩阵，全为0或1
 * 变换后，每行按二进制转化，和最大。
 * <p>
 * 就是尽可能的，更多的0变成1？
 * <p>
 * 首位，也就是高位必须是1，其他位尽可能多1；
 * <p>
 * https://leetcode-cn.com/problems/score-after-flipping-matrix/
 */
public class ScoreAfterFlippingMatrix_861 {


    public static int matrixScore0(int[][] A) {

        int m = A.length;
        int n = A[0].length;


        for (int i = 0; i < m; i++) {
            int zeroCount = 0;
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 0) {
                    zeroCount++;
                    if (zeroCount > n / 2) {
                        for (int k = 0; k < n; k++) {
                            A[i][k] = A[i][k] == 0 ? 1 : 0;
                        }
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            int zeroCount = 0;
            for (int j = 0; j < m; j++) {
                if (A[j][i] == 0) {
                    zeroCount++;
                    if (zeroCount > m / 2) {
                        for (int k = 0; k < m; k++) {
                            A[k][i] = A[k][i] == 0 ? 1 : 0;
                        }
                        break;
                    }
                }
            }
        }


        int res = 0;
        for (int j = 0; j < m; j++) {
            StringBuffer sb = new StringBuffer();
            for (int a : A[j]) {
                sb.append(a);
            }
            res += Integer.valueOf(sb.toString(), 2);
        }

        return res;
    }


    /**
     * 思想来源：https://leetcode-cn.com/problems/score-after-flipping-matrix/solution/javascript-ti-jie-by-danranvm/
     * 第一列必须为 1
     * 遍历行，如果第一个元素不为1，就翻转行：可以通过 A[r][c] ^ A[r][0] 来实现
     * 然后从第二列开始，统计 1 的数量，如果 1 的数量小于 0 的数量就翻转列
     *
     * @param A
     * @return
     */
    public static int matrixScore(int[][] A) {

        int m = A.length;
        int n = A[0].length;


        for (int i = 0; i < m; i++) {
            int[] row = A[i];

            if (row[0] == 0) {
                // 翻转行
                for (int k = 0; k < n; k++) {
                    A[i][k] = A[i][k] == 0 ? 1 : 0;
                }
            }

            for (int i1 = 1; i1 < n; i1++) {
                int zeroCount = 0; // 这个一列 0 的个数
                for (int j = 0; j < m; j++) {
                    if (A[j][i1] == 0) {
                        zeroCount++;
                        if (zeroCount > m / 2) {
                            for (int k = 0; k < m; k++) {
                                A[k][i1] = A[k][i1] == 0 ? 1 : 0;// 优化点：不用真实翻转，直接按翻转计算该行的值
                            }
                            break;
                        }
                    }
                }
            }

        }


        int res = 0;
        for (int j = 0; j < m; j++) {
            StringBuffer sb = new StringBuffer();
            for (int a : A[j]) {
                sb.append(a);
            }
            res += Integer.valueOf(sb.toString(), 2);
        }

        return res;
    }



    /**
     * 0和1 互相转化
     * https://blog.csdn.net/qq874455953/article/details/83214213
     *
     * @param oneOrZero
     * @return
     */
    public int zereOneTransformor(int oneOrZero) {
        return oneOrZero ^ 1;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(matrixScore(new int[][]{new int[]{0, 0, 1, 1}, new int[]{1, 0, 1, 0}, new int[]{1, 1, 0, 0}}));
        System.in.read(new byte[1]);
    }
}