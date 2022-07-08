package leetcode.ordinary;

/**
 * 和相同的二元子数组
 * https://leetcode-cn.com/problems/binary-subarrays-with-sum/solution/he-xiang-tong-de-er-yuan-zi-shu-zu-by-leetcode/
 */
public class BinarySubarraysWithSum_930 {


    /**
     * 双重for
     * @param A
     * @param S
     * @return
     */
    public int numSubarraysWithSum(int[] A, int S) {
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            int currentSum = 0;
            for (int i1 = i; i1 < A.length; i1++) {
                currentSum+=A[i1];
                if(currentSum == S){
                    res++;
                }
            }
        }
        return res;
    }


    /**
     * 前缀和
     * 这样就可以通过 P[j + 1] - P[i]  快速计算出 A[i..j] 的和
     */
    // TODO: 2020/1/12

}
