package leetcode.array;

import java.util.HashMap;

/**
 * 子数组和为k的倍数
 * Created by lijianhua04 on 2020/3/28.
 */
public class ContinuousSubarraySum_523 {



    /**
     * https://leetcode.com/problems/continuous-subarray-sum/submissions/
     * 判断0的case，(preSum[j + 1] - preSum[i]) == k
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum(int[] nums, int k) {

        int[] preSum = new int[nums.length + 1];
        preSum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((preSum[j + 1] - preSum[i]) == k) {
                    return true;
                }
                if (k != 0 && (preSum[j + 1] - preSum[i]) % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 如果preSum[i]和preSum[j]在intMap中映射到了同一余数，即表示这样的子数组存在
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum1(int[] nums, int k) {

        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);// [0,0] k=0的case
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) {
                sum = sum % k;
            }
            if (map.get(sum) != null) {
                if (i - map.get(sum) > 1) {
                    return true;
                }
            } else {
                map.put(sum, i);
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        new ContinuousSubarraySum_523().checkSubarraySum(new int[]{0,1,0},0);
        new ContinuousSubarraySum_523().checkSubarraySum(new int[]{23, 6, 9}, 6);
    }

}
