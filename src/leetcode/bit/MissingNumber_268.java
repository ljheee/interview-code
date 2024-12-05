package leetcode.bit;

/**
 * O(n)找出1~n中丢失的 数字
 * https://leetcode.com/problems/missing-number/
 */
public class MissingNumber_268 {

    public int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = n*(n+1)/2;
        for(int i : nums){
            sum-=i;
        }
        return sum;
    }

    // 位运算解法
    public int missingNumber1(int[] nums) {
        int res = 0;
        for(int i : nums){
            res^=i;
        }
        return res;
    }

}
