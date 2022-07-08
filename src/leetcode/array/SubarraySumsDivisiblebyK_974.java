package leetcode.array;

/**
 * Created by lijianhua04 on 2020/4/26.
 */
public class SubarraySumsDivisiblebyK_974 {


    /**
     * https://leetcode.com/problems/subarray-sums-divisible-by-k/
     * 会超时
     *
     * @param A
     * @param K
     * @return
     */
    public int subarraysDivByK(int[] A, int K) {

        int ans = 0;
        int[] sum = new int[A.length + 1];
        for (int i = 0; i < A.length; i++) {
            sum[i + 1] = sum[i] + A[i];
        }

        for (int i = 0; i < sum.length; i++) {
            for (int j = i + 1; j < sum.length; j++) {
                if ((sum[j] - sum[i]) % K == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }

    /**
     * 根据 同余定理
     * (P[j] - P[i-1])%K == 0，只要 P[j]%K == P[i-1]%K，就可以保证上面的式子成立。
     *
     * @param A
     * @param K
     * @return
     */
    public int subarraysDivByK1(int[] A, int K) {

        int ans = 0;
        int[] sum = new int[A.length + 1];
        for (int i = 0; i < A.length; i++) {
            sum[i + 1] = sum[i] + A[i];
        }

        int[] count = new int[K];
        for (int i = 0; i < sum.length; i++) {
            count[(sum[i] % K + K) % K]++;
        }

        for (int i = 0; i < count.length; i++) {
            ans += count[i] * (count[i] - 1) / 2;
        }
        return ans;
    }


    public int[] smallerNumbersThanCurrent(int[] nums) {

        int[] count = new int[101];
        for (int num : nums) {
            count[num]++;
        }

        int total = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                int thisC = count[i];
                count[i] = total;
                total += thisC;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = count[nums[i]];
        }
        return nums;
    }



    public static void main(String[] args) {
        System.out.println(new SubarraySumsDivisiblebyK_974().subarraysDivByK1(new int[]{0, -5}, 10));
        System.out.println(new SubarraySumsDivisiblebyK_974().subarraysDivByK1(new int[]{4, 5, 0, -2, -3, 1}, 5));

        double a = Math.pow(1000D, 150000D);
        System.out.println(a);
    }

}
