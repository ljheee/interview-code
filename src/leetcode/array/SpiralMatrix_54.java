package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 顺时针 打印二维矩阵
 * https://leetcode.com/problems/spiral-matrix/submissions/
 */
public class SpiralMatrix_54 {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();

        int m = matrix.length;
        if (m == 0) {
            return list;
        }
        int n = matrix[0].length;
        for (int i = 0; i < (matrix.length + 1) / 2; i++) {
            int[] firstRow = matrix[i];
            int[] lastRow = matrix[m - 1 - i];

            int lastCol = n - 1 - i;
            int firstCol = i;

            if (lastCol < 0 || lastCol < firstCol) {
                break;
            }

            for (int j = i; j < firstRow.length - i; j++) {
                list.add(firstRow[j]);
            }
            for (int i1 = i + 1; i1 < matrix.length - i - 1; i1++) {
                list.add(matrix[i1][lastCol]);
            }

            if (lastRow[0] == firstRow[0] && lastRow[lastRow.length - 1] == firstRow[lastRow.length - 1]) {
                break;
            }
            for (int i1 = lastRow.length - 1 - i; i1 >= i; i1--) {
                list.add(lastRow[i1]);
            }

            if (lastCol == firstCol) {
                break;
            }
            for (int i1 = matrix.length - 2 - i; i1 >= i + 1; i1--) {
                list.add(matrix[i1][firstCol]);
            }

        }

        return list;
    }

    public static void main(String[] args) {
//        new SpiralMatrix_54().spiralOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
//        new SpiralMatrix_54().spiralOrder(new int[][]{{7},{9},{6}});
//        new SpiralMatrix_54().spiralOrder(new int[][]{{2, 5, 8}, {4, 0, -1}});
        new SpiralMatrix_54().spiralOrder(new int[][]{{1, 11}, {2, 12}, {3, 13}, {4, 14}, {5, 15}, {6, 16}, {7, 17}, {8, 18}, {9, 19}, {10, 20}});
    }
}