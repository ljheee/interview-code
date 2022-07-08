package leetcode.array;

import java.util.HashMap;

/**
 * 找出 中心索引
 * 使得左右两边 序列和 相等
 */
public class FindPivotIndex_724 {


    /**
     * https://leetcode.com/problems/find-pivot-index/
     * 数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
     * <p>
     * 两边同时开始 累加
     * 特殊case：[-1,-1,-1,-1,-1,0]
     *
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {

        int i = 0;
        int j = nums.length - 1;
        int left = 0;
        int right = 0;

        while (i < j) {
            if (left == right && j - i == 0) {
                return i;
            }
            if (left < right) {
                left += nums[i++];
            } else {
                right += nums[j--];
            }
        }
        return -1;
    }

    /**
     * 前缀和
     * We can precompute prefix sums
     * P[i] = nums[0] + nums[1] + ... + nums[i-1].
     * Then for each index, the left sum is P[i], and the right sum is P[P.length - 1] - P[i] - nums[i].
     *
     * @param nums
     * @return
     */
    public int pivotIndex1(int[] nums) {
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        //特殊处理0索引
        if (sum[nums.length - 1] - sum[0] == 0) {
            return 0;
        }

        for (int i = 1; i < nums.length; i++) {
            if (sum[i - 1] == sum[nums.length - 1] - sum[i] - nums[i]) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 不使用额外空间的 前缀和
     * https://segmentfault.com/a/1190000016093599
     *
     * @param nums
     * @return
     */
    public int pivotIndex0(int[] nums) {

        int left = 0;
        int right = 0;
        for (int num : nums) {
            right+=num;
        }

        for (int i = 0; i < nums.length; i++) {
            right = right-nums[i];
            if(left==right){
                return i;
            }
            left+=nums[i];
        }
        return -1;
    }

    public boolean checkIfExist(int[] arr) {
        HashMap<Double,Integer> map = new HashMap<>();
        for (int i : arr) {
            double d = Double.valueOf(i);
            map.put(d,map.getOrDefault(d,0)+1);
        }

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == 0 && map.get(0D) >1){
                return true;
            }
            if(arr[i] == 0){
                continue;
            }
            if(map.containsKey(arr[i] *2)|| map.containsKey(arr[i] /2d)){
                return true;
            }
        }

        return false;
    }


    public static void main(String[] args) {

//        new FindPivotIndex_724().checkIfExist(new int[]{3,1,7,11});
//        new FindPivotIndex_724().checkIfExist(new int[]{-2,0,10,-19,4,6,-8});
        new FindPivotIndex_724().checkIfExist(new int[]{0,0});
        HashMap<Long,Integer> map = new HashMap<>();
        map.put(Long.valueOf(5),1);

        long v = 10/2L;
        System.out.println(map.get(10/2L));
        System.out.println(3/2d);

        System.out.println(5/2*2);
    }

}
