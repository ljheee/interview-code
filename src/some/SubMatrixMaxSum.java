package some;

/**
 * N*M
 * N^2 * M
 * <p>
 * <p>
 * <p>
 * Created by lijianhua04 on 2020/9/1.
 */
public class SubMatrixMaxSum {


    public static int maxSum(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int[] nums = matrix[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i - 1] > 0 ? dp[i - 1] + nums[i] : nums[i];
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                dp[j] += matrix[i][j];
            }
        }
        return maxSubArray(dp) - 1;
    }

    public static int maxSubArray(int[] nums) {

        int max = 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i - 1] > 0 ? dp[i - 1] + nums[i] : nums[i];
            max = Math.max(max, dp[i]);
        }
        return max;
    }


    public int subMatrixMaxSum(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        int ans = matrix[0][0];
        int[] clone = matrix[0].clone();
        for (int i = 0; i < m; i++) {
            ans = Math.max(ans, subArrayMaxSum(matrix[i]));
            clone = matrix[i];
            for (int j = i + 1; j < m; j++) {

                for (int k = 0; k < n; k++) {
                    clone[k] += matrix[j][k];
                }
                ans = Math.max(ans, subArrayMaxSum(clone));
            }
        }
        return ans;
    }


    /**
     * AC
     * @param arr
     * @return
     */
    public int subArrayMaxSum(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        int ans = Integer.MIN_VALUE;
        int curSum = 0;
        int max = arr[0];

        for (int num : arr) {
            max = Math.max(max, num);
            curSum += num;
            ans = Math.max(curSum,ans);
            if (curSum < 0) {
                curSum = 0;
            }
        }
        return Math.max(ans, max);
    }


    public static void main(String[] args) {
        int matrix[][] = new int[][]{{1, -2, -1},
                                     {1, 1, 1},
                                     {0, 1, -9}};


        SubMatrixMaxSum subMatrixMaxSum = new SubMatrixMaxSum();
        System.out.println(subMatrixMaxSum.subMatrixMaxSum(matrix));
//        System.out.println(maxSum(matrix));
    }
}
