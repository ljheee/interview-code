package leetcode.dp;

/**
 * https://leetcode.com/problems/maximal-rectangle/submissions/
 * 找出0，1矩阵中的最大矩形
 * Created by lijianhua04 on 2020/10/3.
 */
public class MaximalRectangle_85 {


    /**
     * 不过case
     * [["1","0","1","0"],
     * ["1","0","1","1"],
     * ["1","0","1","1"],
     * ["1","1","1","1"]]
     * <p>
     * <p>
     * [["0","1","1","0","1"],
     * ["1","1","0","1","0"],
     * ["0","1","1","1","0"],
     * ["1","1","1","1","0"],
     * ["1","1","1","1","1"],
     * ["0","0","0","0","0"]]
     * <p>
     * 只看，位置i,j 的扩展 不行
     *
     * @param matrix
     * @return
     */
    public int maximalRectangle0(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        /**
         * left[i][j] 位置i,j元素，左侧能扩展的1个数，包含它自身
         * up[i][j]   位置i,j元素，向上能扩展的1个数，包含它自身
         */
        int[][] left = new int[matrix.length + 1][matrix[0].length + 1];
        int[][] up = new int[matrix.length + 1][matrix[0].length + 1];
        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];

        int ans = 0;
        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 1; j <= matrix[0].length; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    left[i][j] = left[i][j - 1] + 1;
                    up[i][j] = up[i - 1][j] + 1;
                    ans = Math.max(left[i][j], up[i][j]);
                }
            }
        }
        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 1; j <= matrix[0].length; j++) {
                int w = Math.min(left[i][j] - 1, left[i - 1][j - 1]) + 1;
                int h = Math.min(up[i][j] - 1, up[i - 1][j - 1]) + 1;
                ans = Math.max(ans, w * h);

//                if (left[i][j] >= left[i - 1][j - 1] + 1 &&
//                        up[i][j] >= up[i - 1][j - 1] + 1) {
//                    dp[i][j] = (left[i - 1][j - 1] + 1) * (up[i - 1][j - 1] + 1);
//                    ans = Math.max(ans, dp[i][j]);
//                }
            }
        }


        return ans;
    }


    /**
     * AC
     * 位置i,j处，固定高h，去看h内水平方向，向左能扩展的最小值；
     * 位置i,j处，固定宽w，去看w内垂直方向，向上能扩展的最小值；
     *
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0) return 0;

        /**
         * left[i][j] 位置i,j元素，左侧能扩展的1个数，包含它自身
         * up[i][j]   位置i,j元素，向上能扩展的1个数，包含它自身
         */
        int[][] left = new int[matrix.length + 1][matrix[0].length + 1];
        int[][] up = new int[matrix.length + 1][matrix[0].length + 1];

        int ans = 0;
        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 1; j <= matrix[0].length; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    left[i][j] = left[i][j - 1] + 1;
                    up[i][j] = up[i - 1][j] + 1;
                    ans = Math.max(ans, Math.max(left[i][j], up[i][j]));
                }
            }
        }

        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 1; j <= matrix[0].length; j++) {
                int w = Math.min(left[i][j] - 1, left[i - 1][j - 1]) + 1;
                int h = Math.min(up[i][j] - 1, up[i - 1][j - 1]) + 1;

                // 固定高h，看h内水平方向，向左能扩展的最小值；
                int temp = h - 1;
                for (int k = i; temp >= 0; k--, temp--) {
                    w = Math.min(left[k][j], w);
                }
                ans = Math.max(ans, w * h);

                // 固定宽w，看w内垂直方向，向上能扩展的最小值；
                w = Math.min(left[i][j] - 1, left[i - 1][j - 1]) + 1;
                h = Math.min(up[i][j] - 1, up[i - 1][j - 1]) + 1;
                temp = w - 1;
                for (int k = j; temp >= 0; k--, temp--) {
                    h = Math.min(up[i][k], h);
                }
                ans = Math.max(ans, w * h);
            }
        }
        return ans;
    }


}
