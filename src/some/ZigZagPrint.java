package some;

/**
 * Z 字形打印矩阵
 * 两个点拉一条直线，打印这条斜线
 */
public class ZigZagPrint {


    public static void zigZagPrint(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;
        int x1 = 0, y1 = 0;
        int x2 = 0, y2 = 0;
        boolean up = true;
        while (x1 < m && y2 < n) {
            printLine(matrix, x1, y1, x2, y2, up);
            if (y1 != n - 1) {
                x1 = x1;
                y1 = y1 + 1;
            } else {
                x1 = x1 + 1;
                y1 = y1;
            }

            if (x2 != m - 1) {
                x2 = x2 + 1;
                y2 = y2;
            } else {
                x2 = x2;
                y2 = y2 + 1;
            }
            up = !up;
        }
    }

    private static void printLine(int[][] matrix, int x1, int y1, int x2, int y2, boolean up) {

        if (up) {
            for (int i = x2; i >= x1; i--) {
                System.out.println(matrix[i][y2++]);
            }
        } else {
            for (int i = x1; i <= x2; i++) {
                System.out.println(matrix[i][y1--]);
            }
        }
    }


    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, -3},
                {4, 5, -6},
                {7, 8, 9}};

        matrix = new int[][]{{1, 2, -3, 4},
                             {5, 6, -7, 8},
                             {9, 10, 11, 12}};


        zigZagPrint(matrix);
    }

}
