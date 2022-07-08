package leetcode.array;

/**
 * Created by lijianhua04 on 2020/2/26.
 */
public class SortArrayByParityII_922 {


    /**
     * https://leetcode.com/problems/maximum-average-subarray-i/submissions/
     *
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int maxSum = sum;

        for (int i = k; i < nums.length; i++) {
            sum += nums[i];
            sum -= nums[i - k];
            maxSum = Math.max(maxSum, sum);
        }
        double d = maxSum * 1D / k;
        return d;
    }

    /**
     * https://leetcode.com/problems/sort-array-by-parity-ii/
     * 偶数放在偶数下标，奇数放在奇数下标位置
     * <p>
     * 空间复杂度O(n)解法
     * 意思就是在找到一个偶数位是奇数的前提下,找奇数位上的偶数,找到之后在交换.
     * <p>
     * 648,831,560,986,192,424,997,829,897,843
     * 连续出现两个 evenIndex，却没有找到偶数位置来交换；也就是找到一方，必须立即找到另一方来交换
     *
     * @param A
     * @return
     */
    public int[] sortArrayByParityII0(int[] A) {

        int oddIndex = -1;
        int evenIndex = -1;
        for (int i = 0; i < A.length; i++) {
            if (i % 2 == 0 && A[i] % 2 != 0) {
                oddIndex = i;
            }
            if (i % 2 != 0 && A[i] % 2 == 0) {
                evenIndex = i;
            }

            if (oddIndex != -1 && evenIndex != -1) {
                int temp = A[oddIndex];
                A[oddIndex] = A[evenIndex];
                A[evenIndex] = temp;
                oddIndex = -1;
                evenIndex = -1;
            }

        }

        return A;
    }

    // https://leetcode-cn.com/problems/sort-array-by-parity-ii/solution/an-qi-ou-pai-xu-shu-zu-ii-by-leetcode/
    public int[] sortArrayByParityII(int[] A) {

        int j = 0;
        for (int i = 0; i < A.length; i += 2) {
            if (A[i] % 2 == 1) {
                while (A[j]%2==1)
                    j+=2;

                // swap；
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }

        return A;
    }

    public static void main(String[] args) {

        new SortArrayByParityII_922().sortArrayByParityII(new int[]{648, 831, 560, 986, 192, 424, 997, 829, 897, 843});
    }
}
