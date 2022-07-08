package leetcode.math;

/**
 * 转置 矩阵
 * 对称轴
 */
public class FlipMatrix {


    /**
     * n*n的矩阵，返回转置矩阵
     *
     * @param matrix
     * @return
     */
    public static int[][] flip(int[][] matrix) {

        int n = matrix.length - 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                int axisI = n - j;
                int axisJ = n - i;
                int temp = matrix[i][j];
                matrix[i][j] = matrix[axisI][axisJ];
                matrix[axisI][axisJ] = temp;
            }
        }
        return matrix;
    }


    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0, 1, -1},
                {1, 0, -1},
                {1, 1, 1}};

        matrix = new int[][]{{0, 1, -1, 1},
                            {1, 0, -1, 1},
                            {1, 0, -1, 1},
                            {1, 1, 1, 1}};

        matrix = flip(matrix);
        System.out.println(matrix);

    }
}
