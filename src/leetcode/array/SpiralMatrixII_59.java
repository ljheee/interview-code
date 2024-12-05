package leetcode.array;

import java.util.Stack;

/**
 * 生成 顺时针 二维矩阵
 * https://leetcode.com/problems/spiral-matrix-ii/
 */
public class SpiralMatrixII_59 {

    /**
     * [ 1, 2, 3 ],
     * [ 8, 9, 4 ],
     * [ 7, 6, 5 ]
     *
     * 4*4 原本的矩阵
     * [[ 1  2  3  4]
     *  [ 5  6  7  8]
     *  [ 9 10 11 12]
     *  [13 14 15 16]]
     *
     */

    /**
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {

        int v = n * n;
        Stack<Integer> stack = new Stack<>();
        while (v > 0) {
            stack.push(v--);
        }

        int[][] ans = new int[n][n];

        int firstRow = 0;
        int lastRow = n - 1;
        int lastCol = n - 1;
        int firstCol = 0;

        // 让行填完，列缺失

        while (firstRow <= lastRow) {

            for (int i = firstRow; i < n - firstRow && !stack.empty(); i++) {
                ans[firstRow][i] = stack.pop();
            }

            for (int i = firstRow + 1; i < n - firstRow - 1 && !stack.empty(); i++) {
                ans[i][lastCol] = stack.pop();
            }

            for (int i = ans[lastRow].length - 1 - firstRow; i >= firstRow && !stack.empty(); i--) {
                ans[lastRow][i] = stack.pop();
            }

            for (int i = n - 1 - firstRow - 1; i >= firstRow + 1 && !stack.empty(); i--) {
                ans[i][firstCol] = stack.pop();
            }

            firstRow++;
            lastRow--;
            lastCol--;
            firstCol++;

        }
        return ans;
    }


    public static void main(String[] args) {
        new SpiralMatrixII_59().generateMatrix(3);
    }
}