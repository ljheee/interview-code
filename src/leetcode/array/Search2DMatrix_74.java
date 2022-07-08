package leetcode.array;

/**
 * https://leetcode.com/problems/search-a-2d-matrix/
 * <p>
 * Input:
 * matrix = [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * target = 3
 * Output: true
 */
public class Search2DMatrix_74 {


    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        if (matrix[0].length == 0) return false;
        int col = matrix[0].length;

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][col - 1] >= target) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] == target) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     * <p>
     * <p>
     * [
     * [1,   4,  7, 11, 15],
     * [2,   5,  8, 12, 19],
     * [3,   6,  9, 16, 22],
     * [10, 13, 14, 17, 24],
     * [18, 21, 23, 26, 30]
     * ]
     * 给定 target = 5，返回 true。
     * 给定 target = 20，返回 false。
     * 先从左上角问下，然后往左，再问下
     * <p>
     * 不能return的case
     * [3,3,8,13,13,18],
     * [4,5,11,13,18,20],
     * [9,9,14,15,23,23],
     * [13,18,22,22,25,27],
     * [18,22,23,28,30,33],
     * [21,25,28,30,35,35],
     * [24,25,33,36,37,40]]
     * 21
     * <p>
     * 更优的方案，从左下角开始
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrixII(int[][] matrix, int target) {

        if (matrix.length == 0) return false;
        if (matrix[0].length == 0) return false;
        int col = matrix[0].length;

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][col - 1] >= target) {
                for (int j = matrix[i].length - 1; j >= 0; j--) {
                    if (matrix[i][j] <= target) {
                        for (int i1 = i; i1 < matrix.length; i1++) {
                            if (matrix[i1][j] == target) {
                                return true;
                            }
                        }
                        //return false;辞出不能return
                    }
                }
            }
        }
        return false;
    }


}
